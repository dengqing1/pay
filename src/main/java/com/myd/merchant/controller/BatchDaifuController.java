package com.myd.merchant.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.myd.config.DynamicDataSource;
import com.myd.controller.BaseController;
import com.myd.entity.BatchDaifu;
import com.myd.entity.Gather;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.Statement;
import com.myd.entity.User;
import com.myd.manager.service.GatherService;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.manager.service.StatementService;
import com.myd.service.BatchDaifuService;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.util.Base64CodeUtil;
import com.myd.util.DateUtil;
import com.myd.util.FileUtils;
import com.myd.util.OfTime;
import com.myd.util.POIUtil;
import com.myd.util.PageInfo;
import com.myd.util.PhoneUtil;
import com.myd.util.Query;
import com.myd.util.R;

/**
 *批量代付
 */

@Controller
public class BatchDaifuController extends BaseController {
	private static Logger logger  = Logger.getLogger(BaseController.class);
	@Autowired
	private BatchDaifuService batchDaifuService;
	
	@Autowired
	private NpayOrderService npayOrderService;
	
	@Autowired
	private GatherService gatherService;
	
	@Autowired
	private NpayMerchantBalanceService npayMerchantBalanceService;
	
	@Autowired
	private StatementService statementService;
	
	
	@RequestMapping("daifu")
	public String order(HttpServletRequest request,Integer page,
			Query query,Model model,@RequestParam Map<String,Object> params){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);
//		params.put("offset", query.getPageNum());
//		params.put("limit", query.getPageSize());
//		query.setPages(gatherService.count(params));
//		PageInfo<Gather> pageInfo = new PageInfo<>(gatherService.list(params));		
//		model.addAttribute("list", query.getDataTable(pageInfo));
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		List<Gather> list = gatherService.list(params);
		PageInfo<Gather> pageInfo = new PageInfo<>(list);	
		model.addAttribute("pageInfo", pageInfo);
		
		
		model.addAttribute("user", user);
		model.addAttribute("banks", npayOrderService.selectByBank());
		return "merchant/daifu";
	}
	
	@RequestMapping("daifu/search")
	@ResponseBody
	Map<String, Object> search(HttpServletRequest request,Query query, @RequestParam Map<String, Object> params)
			throws Exception {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);
		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<Gather> pageInfo = new PageInfo<>(gatherService.list(params));
		return query.getDataTable(pageInfo);
	}
	

	@RequestMapping("daifu/code")
	@ResponseBody
	public R code(NpayOrder npayOrder,HttpServletRequest request, HttpServletResponse response) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		String e_code = request.getParameter("emailCode");
		String p_code = request.getParameter("phoneCode");
		String emailCode = (String) request.getSession().getAttribute("emailCode");
		String phoneCode = (String) request.getSession().getAttribute("phoneCode");
		if (!StringUtils.isBlank(e_code)) {
			if (StringUtils.isBlank(emailCode) ) {
				return R.error("邮箱验证码获取失败！");
			}
			if (!emailCode.equals(e_code)) {
				return R.error("邮箱验证码校验失败！");
			}
		}else if(!StringUtils.isBlank(p_code)){
			if (StringUtils.isBlank(phoneCode) ) {
				return R.error("短信验证码获取失败！");
			}
			if (!phoneCode.equals(p_code)) {
				return R.error("短信验证码校验失败！");
			}
		}else {
			return R.error("验证码获取失败！");
		}
		return R.ok();
	}
	
	
	@RequestMapping("daifu/addDaifu")
	@ResponseBody
	public R addDaifu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		//NpayOrder npayOrder = new NpayOrder();
		Gather gather = new Gather();
		User user = super.getUser(request);
		
	/*	String merorderid = request.getParameter("gatherMerOrderId");
		if (StringUtils.isBlank(merorderid)) {
			return R.error("商品订单号不能为空！");
		}
				
		if (POIUtil.isInteger(merorderid)) {
    		NpayOrder orderId = npayOrderService.getMerorderid(merorderid);
    		if (orderId != null) {
    			return R.error("商品订单号重复,订单号为:"+merorderid);
			}
    	}else {
    		return  R.error("商品订单号格式有误,请输入数字。");
		}*/
		
		//npayOrder.setMerorderid(merorderid);
		gather.setGatherMerchantId(user.getMerchantid());
		
		String txnAmt = request.getParameter("gatherTxnAmt");
		if (txnAmt == null) {
			return R.error("交易金额不能为空！");
		}
		if (!POIUtil.isInteger(txnAmt)) {
			return  R.error("交易金额有误,请输入数字。");
		}
		
//		单笔限额   500 - 50000  元
		if(Integer.parseInt(txnAmt) < Integer.parseInt("50000")){
			return R.error("代付请求失败,金额最小为  500 元");
		}
		
		
		if(Integer.parseInt(txnAmt) > Integer.parseInt("5000000")){
			return R.error("代付请求失败,金额最大为 50000 元");
		}
		
		
		int txnmon = Integer.parseInt(txnAmt) + 500;
		NpayMerchantBalance2018 balance = npayMerchantBalanceService.getBanlaceById(user.getMerchantid());
		logger.info("代付金额为>>>>>>>"+txnmon);
		logger.info("余额为>>>>>>>"+balance.getBalanceAvailable());
		if (balance == null || (balance.getBalanceAvailable() < txnmon)) {
			return R.error("余额不足!");

		}
		
		
		
		
		//npayOrder.setTxnamt(Integer.parseInt(txnAmt));
		gather.setGatherTxnAmt(txnAmt);
		String accno = request.getParameter("gatherAccno");
		if (StringUtils.isBlank(accno)) {
			return R.error("银行卡号不能为空！");
		}
		if (!POIUtil.isInteger(accno)) {
			return  R.error("银行卡号有误,请输入数字。");
		}
		
		//npayOrder.setAccno(accno);
		gather.setGatherAccno(accno);
		String ppflag = request.getParameter("gatherPpflag");
		if (StringUtils.isBlank(ppflag)) {
			return R.error("对公对私标志不能为空！");
		}
		
		if (!("00".equals(ppflag) || "01".equals(ppflag))) {
			return R.error("对公对私标志有误");
		}
		//npayOrder.setPpflag(ppflag);
		gather.setGatherPpflag(ppflag);
		String customerNm = request.getParameter("gatherCustomerName");
		if (StringUtils.isBlank(customerNm)) {
			return R.error("持卡人姓名不能为空！");
		}
		JSONObject json = new JSONObject();
		json.put("customerNm", customerNm);
		//npayOrder.setCustomerinfo(json.toJSONString());
		gather.setGatherCustomerName(customerNm);
		
		String bankName = request.getParameter("bankName");
		if (StringUtils.isBlank(bankName)) {
			return R.error("开户银行不能为空！");
		}
		NpayTf56Bank bank = npayOrderService.getBank(bankName);
		if (bank == null) {
			return  R.error("暂未开通此银行！");
		}
		//npayOrder.setBankid(bank.getBankId());
		gather.setGatherBankId(bank.getBankId());
		
		
		//npayOrder.setUserid(user.getId()+"");
		
		
		gather.setGatherMerOrderId(DateUtil.getOrderId(new Date()));
		gather.setGatherTime(OfTime.getLongTime());//创建时间
		gather.setGatherUpdateTime(OfTime.getLongTime());//审核时间
		gather.setGatherState("1");//状态
		gather.setGatherKey("admin");
		gather.setGatherPhoneNo(request.getParameter("gatherPhoneNo"));
		
		gatherService.insert(gather);
		
		
		
//		给指定手机发送信息
		String code = String.valueOf((int)((Math.random()*9+1)*1000));
//		String code = "有新的DF订单";
		System.out.println("手机验证码为:"+code);
		boolean sendPhone = PhoneUtil.sendPhone("18721167150", code);
		if (sendPhone) {
			request.getSession().setAttribute("phoneCode", code);
			return R.ok("短信发送成功！");
		}

/**
 * 		添加代付订单后      把该金额修改为在途金额
 */
/*		
//		查询原来的金额
		 NpayMerchantBalance2018 balance2018 = npayMerchantBalance2018Service.selectByPrimaryKey(user.getMerchantid());
		
//		 txnAmt    txnmon
//			余额    - 本次交易金额    
		 String yue = new BigDecimal(balance2018.getBalance()).subtract(new BigDecimal(txnAmt)).stripTrailingZeros().toPlainString();

//		 在途余额    +  本次交易金额  
		 String ztyue = new BigDecimal(balance2018.getBalanceAvailable()).add(new BigDecimal(txnAmt)).stripTrailingZeros().toPlainString();
		 
			NpayMerchantBalance2018   merchant2018=new NpayMerchantBalance2018();
			merchant2018.setMerchantid(user.getMerchantid());
			merchant2018.setBalance(Long.parseLong(yue));
			merchant2018.setBalanceAvailable(Long.parseLong(ztyue));
			
//			修改账户余额
			npayMerchantBalance2018Service.updateByPrimaryKeySelective(merchant2018);
			
	*/	 
		 
		return R.ok();
			
		
	}
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping("daifu/upload")
	@ResponseBody
	public R upload(HttpServletRequest request, HttpServletResponse response) {
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
  
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/javascript;charset=UTF-8");
			response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
			String msg = "";
			try {
				
			/*	String e_code = request.getParameter("emailCode");
				String p_code = request.getParameter("phoneCode");
				String emailCode = (String) request.getSession().getAttribute("emailCode");
				String phoneCode = (String) request.getSession().getAttribute("phoneCode");
				if (!StringUtils.isBlank(e_code)) {
					if (StringUtils.isBlank(emailCode) ) {
						return R.error("邮箱验证码获取失败！");
					}
					if (!emailCode.equals(e_code)) {
						return R.error("邮箱验证码校验失败！");
					}
				}else if(!StringUtils.isBlank(p_code)){
					if (StringUtils.isBlank(phoneCode) ) {
						return R.error("短信验证码获取失败！");
					}
					if (!phoneCode.equals(p_code)) {
						return R.error("短信验证码校验失败！");
					}
				}else {
					return R.error("验证码获取失败！");
				}
				*/
				
				
				String merchantid = request.getParameter("merchantid");
				if (StringUtils.isBlank(merchantid)) {
					return R.error("上传失败！");
				}
				
				// 获取Tomcat服务器所在的路径
				String tomcat_path = request.getSession().
						getServletContext().getRealPath("").replaceAll("\\\\", "/");
				String bin_path = tomcat_path.substring(0, tomcat_path.lastIndexOf("/"));
				bin_path = bin_path.substring(0, bin_path.lastIndexOf("/") + 1);
				bin_path += "pic_file/";
				MultipartFile upfile = multiRequest.getFile("upfile");
				if(upfile != null && StringUtils.isNotBlank(upfile.getOriginalFilename())){
					String fileName = upfile.getOriginalFilename();
					
					// 文件格式只能是 产品名-时间.xls
					String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
					if (!"xls".equals(suffix) && !"xlsx".equals(suffix)) {
						return R.error("请上传xls或者xlsx类型的文件");
					}
					//文件名+商户号和+时间，时间精确到秒
					
					fileName = fileName.substring(
							0,fileName.lastIndexOf(".")) +"_"+ merchantid +"_" + 
							new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+suffix;
					File file  = FileUtils.creatFolder(fileName,bin_path);
					//文件上传
					upfile.transferTo(file);
					
					List<Gather> list = new ArrayList<>();
					JSONObject json = new JSONObject();
					
					
					NpayMerchantBalance2018 balance = npayMerchantBalanceService.getBanlaceById(merchantid);
					logger.info("余额为>>>>>>>"+balance.getBalanceAvailable());
					
					
					String data = POIUtil.getList(list, file.getPath(),json,merchantid,balance.getBalanceAvailable()+"");
					if (!"".equals(data)) {
						file.delete();//删除上传的文件
						return R.error(data);
					}
					
					//开始执行保存数据库操作
					if (list == null || list.size() == 0) {
						file.delete();//删除上传的文件
						return R.error("上传的文件无数据!");
					}
					for (int i = 0; i < list.size(); i++) {
//						npayOrderService.insert(list.get(i));
						gatherService.insert(list.get(i));
					}
					
					
					User user = super.getUser(request);
					BatchDaifu batchDaifu = new BatchDaifu();
					batchDaifu.setFileName(Base64CodeUtil.Base64Encoding(fileName));
					batchDaifu.setExtra(json.toJSONString());
					batchDaifu.setUserId(Long.parseLong(user.getId()+""));
					batchDaifu.setStatus(-1);
					batchDaifu.setMerchId(merchantid);
					batchDaifu.setDesc("");
					batchDaifu.setVerifiedBy(user.getEmail());
					batchDaifu.setCreateTimeDate(System.currentTimeMillis()/1000);
					batchDaifu.setUpdateTimeDate(System.currentTimeMillis()/1000);
					batchDaifuService.insert(batchDaifu);
				/*	if (!StringUtils.isBlank(emailCode)) {
						request.getSession().removeAttribute("emailCode");
					}
					if (!StringUtils.isBlank(phoneCode)) {
						request.getSession().removeAttribute("phoneCode");
					}*/
					
					
//					给指定手机发送信息
					String code = String.valueOf((int)((Math.random()*9+1)*1000));
					System.out.println("手机验证码为:"+code);
					boolean sendPhone = PhoneUtil.sendPhone("18721167150", code);
					if (sendPhone) {
						request.getSession().setAttribute("phoneCode", code);
						return R.ok("短信发送成功！");
					}
					
					
					
					return R.ok();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
		
		return R.error("上传失败!");
	}

	
	
	@RequestMapping("daifu/download")
	public void download(HttpServletRequest request, 
			HttpServletResponse response,BatchDaifu batchDaifu){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);

		try {
			User user = super.getUser(request);
			if (user != null) {
				BatchDaifu info = batchDaifuService.get(batchDaifu);
				if (info != null) {
					String fileNames = info.getFileNames();
					// 获取Tomcat服务器所在的路径
					String tomcat_path = request.getSession().
							getServletContext().getRealPath("").replaceAll("\\\\", "/");
					String bin_path = tomcat_path.substring(0, tomcat_path.lastIndexOf("/"));
					bin_path = bin_path.substring(0, bin_path.lastIndexOf("/") + 1);
					bin_path += "pic_file/" + fileNames;
					File file = new File(bin_path);
					//得到要下载的文件
					//文件存在
					if(file.exists()){
						download(response,file,info);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	 /**
     * @param resp
     * @param name         文件真实名字
     * @param downloadName 文件下载时名字
     */
    public static void download(HttpServletResponse resp,File file, BatchDaifu batchDaifu) {
    	DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
        String fileName = null;
        try {
            fileName = new String(batchDaifu.getFileNames().getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    
    
    
    
    @RequestMapping("daifuStatement")
    public String Statement(HttpServletRequest request,Integer page,Query query){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
    	User user = super.getUser(request);
    	
    	if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		List<Statement> list = statementService.selectOneMerchant(user.getMerchantid());
		PageInfo<Statement> pageInfo = new PageInfo<Statement>(list);
		request.setAttribute("pageInfo", pageInfo);
    	
    	
    	return "merchant/statement";
    }
    
    
    
    
    
	
}
