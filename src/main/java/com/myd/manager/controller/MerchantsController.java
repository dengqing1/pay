package com.myd.manager.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myd.config.DynamicDataSource;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayTf56Bank;
import com.myd.entity.Statement;
import com.myd.entity.User;
import com.myd.manager.service.NpayMerchantBalance2018Service;
import com.myd.manager.service.NpayOrderService;
import com.myd.manager.service.StatementService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.UserService;
import com.myd.util.Md5Util;
import com.myd.util.Msg;
import com.myd.util.OfTime;
import com.myd.util.R;
import com.myd.util.UUIDUtils;

@Controller
@RequestMapping("/merchants")
public class MerchantsController {
	
	@Autowired
	private NpayMerInfoService npayMerInfoService;
	
	@Autowired
	private NpayOrderService npayOrderService;
	
	@Autowired
	private NpayMerchantBalance2018Service npayMerchantBalance2018Service;
	
	@Autowired
	private StatementService statementService;
	
	@Autowired
	private UserService userService;
	
	
	
	/**查看商户秘钥
	 * @return
	 */
	@RequestMapping(value = "/details", method ={ RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public R details(String merId,HttpServletRequest request){
		if(merId!=null){
			NpayMerInfo mer = npayMerInfoService.selectById(merId);
			return  R.ok(mer.getMerSecretKey());
		}

		return R.error("数据错误");
	}
	
	
	
	/**商户编辑
	 * @param merId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String merId,HttpServletRequest request){
		if(merId!=null){
			NpayMerInfo mer = npayMerInfoService.selectById(merId);
			request.setAttribute("mer", mer);
		}
		
		//页面显示所有银行
		NpayTf56Bank npayTf56Bank = new NpayTf56Bank();
		List<NpayTf56Bank> Banklist = npayOrderService.selectByBank(npayTf56Bank);
		request.setAttribute("Banklist", Banklist);
		
		return "merchants";
		
	}
	
	/**
	 * 商户保存
	 * @param npayMerInfo
	 * @return
	 */
	@RequestMapping(value = "/saveMerchants", method = {RequestMethod.POST})
	@ResponseBody
	public Msg saveMerchants(String merId ,NpayMerInfo npayMerInfo){
		if(merId==""){
			//添加商户id
			NpayMerInfo Npayinfo = npayMerInfoService.selectByEntity(npayMerInfo);
			if(Npayinfo == null)
				npayMerInfo.setMerId("600000000000003");
			else
				npayMerInfo.setMerId(Long.parseLong(Npayinfo.getMerId()) + Integer.parseInt("1") + "");
			//npayMerInfo.setMerName("内部专用测试账号1");
			npayMerInfo.setMerRegNo("91110105786191530r");
			npayMerInfo.setOpenGateways("bank,daifu");
			npayMerInfo.setMerInfoCreateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerInfo.setMerInfoUpdateTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerInfo.setMerCheckStatus(1);
			npayMerInfo.setMerInfoCheckTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerInfo.setMerOpenStatus(1);
			npayMerInfo.setMerSecretKey(UUIDUtils.getUUID());
			npayMerInfo.setMerIpWhitelist("113.105.88.378");
			npayMerInfo.setMerNeedCheckIpWhitelist(0);
			npayMerInfo.setMerOpenTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerInfo.setMerOpenMailSentTime(new Long(System.currentTimeMillis() / 1000).intValue());
			npayMerInfo.setDaifuChannelId("100");
			npayMerInfo.setDaifuChannelMerAbbr("0");
			npayMerInfo.setDaifuChannelMerId("0");
			npayMerInfo.setDaifuChannelChannelId("0");
			npayMerInfo.setDaifuChannelT1(0);
			npayMerInfo.setLimitInMoneyDaily(0);
			npayMerInfo.setLimitInMoneySingle(0);
			npayMerInfo.setLimitOutMoneySingle(0);
			npayMerInfo.setLimitOutMoneyCardDaily(0);
			npayMerInfo.setT1(1);
			npayMerInfo.setKftMerId("1");
			npayMerInfo.setKftSceMerIds("1");
			npayMerInfo.setIsDelete(0);
			int merchants = npayMerInfoService.insertSelective(npayMerInfo);
			//给余额表添加一条数据
			NpayMerchantBalance2018 balance2018 = new NpayMerchantBalance2018();
			balance2018.setMerchantid(npayMerInfo.getMerId());
			balance2018.setMerchantname(npayMerInfo.getMerShortName());
			balance2018.setStatus(0);
			balance2018.setBalance(0L);
			balance2018.setBalanceAvailable(0L);
			balance2018.setFreezeBalance(0L);
			balance2018.setBlockBalance(0L);
			balance2018.setLastId(0);
			npayMerchantBalance2018Service.insertSelective(balance2018);
			
		}else{
			npayMerInfoService.updateByExampleSelective(npayMerInfo);
		}
		return Msg.success();
	}
	
	/**
	 * 商户账户保存
	 * @return
	 */
	@RequestMapping(value = "/saveMerUser", method = {RequestMethod.POST})
	@ResponseBody
	public Msg saveMerUser(User user){
		user.setCreateTime(System.currentTimeMillis() / 1000);
		user.setUpdateTime(System.currentTimeMillis() / 1000);
		user.setPassword(Md5Util.encryption(user.getPassword()+"@P#W$R%D^"));
		userService.insertSelective(user);
		return Msg.success();
	}
	
	/**
	 * 商户账号更新
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateMerUser", method = {RequestMethod.POST})
	@ResponseBody
	public Msg updateMerUser(User user){
		user.setPassword(Md5Util.encryption(user.getPassword()+"@P#W$R%D^"));
		userService.updateByExampleSelective(user);
		return Msg.success();
		
	}
	
	
	/**
	 *商户开通服务保存
	 * @param npayMerInfo
	 * @return
	 */
	@RequestMapping(value = "/saveUser", method = {RequestMethod.POST})
	@ResponseBody
	public Msg saveUser(String merId,NpayMerInfo npayMerInfo){
		npayMerInfoService.updateByExampleSelective(npayMerInfo);
		return Msg.success();
		
	}
	
	
	/**
	 * 商户用户
	 * @param merId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String editUser(Integer id,HttpServletRequest request){
		if(id!=null){
			User user = userService.findById(id);
			request.setAttribute("user", user);
		}
		return "merchants_user_edit";
		
	}
	
	
	/**
	 * 列表分页
	 * @param merId
	 * @param merShortName
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findAll", method = {RequestMethod.POST,RequestMethod.GET})
	public String findAll(String merId,String merShortName,HttpServletRequest request,HttpServletResponse response,Integer page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("merId", request.getParameter("merId"));
		map.put("merShortName", request.getParameter("merShortName"));
		List<Map<String,Object>> list = npayMerInfoService.selectByExampleMer(map,page);
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("merId", merId);
		request.setAttribute("merShortName", merShortName);
		return "merchants_list";
		
	}
	
	
	/**
	 * 删除
	 * @param merId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	@ResponseBody
	public Msg delete(Integer id){
		User user = new User();
		user.setId(id);
		user.setIsHide(1);
		userService.updateByExampleSelective(user);
		return Msg.success();
		
	}
	
	
	/**
	 * 删除
	 * @param merId
	 * @return
	 */
	@RequestMapping(value = "/deleteNpayMerInfo", method = {RequestMethod.POST})
	@ResponseBody
	public Msg delete(String merId){
		NpayMerInfo npayMerInfo = new NpayMerInfo();
		npayMerInfo.setMerId(merId);
		npayMerInfo.setIsHide(1);
		int mer =  npayMerInfoService.updateByIsHide(npayMerInfo);
		return Msg.success();
		
	}
	
	
	/**
	 * 商户服务修改
	 * @param merId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editService", method = RequestMethod.GET)
	public String editService(String merId,HttpServletRequest request){
		if(merId!=null){
			NpayMerInfo mer = npayMerInfoService.selectById(merId);
			request.setAttribute("mer", mer);
		}
		return "merchants_serve_edit";
		
	}
	
	
	/**
	 * 余额列表分页
	 * @param merId
	 * @param merShortName
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findAllByAccount", method = {RequestMethod.POST,RequestMethod.GET})
	public String findAllByAccount(String merId,String merShortName,HttpServletRequest request,HttpServletResponse response,Integer page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("merId", request.getParameter("merId"));
		map.put("merShortName", request.getParameter("merShortName"));
		map.put("data", OfTime.getShortTime());
//		余额表数据
		List<Map<String,Object>> list = npayMerInfoService.selectByExampleAccount(map,page);
		
		
		List<Map<String,Object>> m = npayMerInfoService.selectBy();//当日入金
		List<Map<String,Object>> ma = npayMerInfoService.selectBydaifu(); // 支付失败
		List<Map<String,Object>> mb = npayMerInfoService.selectBydaizhifu();// 待支付
		List<Map<String,Object>> mc = npayMerInfoService.selectBychujin();//当日出金
		
		
		List<Map<String,Object>> md = npayMerInfoService.selectByshijije();
//		List<Map<String,Object>> me = npayMerInfoService.selectBychujinze();
//		
		List<Map<String,Object>> mf = npayMerInfoService.selectByrujinsum();  //入金总额
		List<Map<String,Object>> mj = npayMerInfoService.selectBychujinsum();// 出金总额
		

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> c = list.get(i);
			
			//拼接  单日入金   
			for (int j = 0; j < m.size(); j++) {
				Map<String, Object> channels = m.get(j);
				
				
				if(c.get("merchantId").equals( channels.get("merchantId") )){
					if(channels.get("txnAmt")==null)
						c.put("sum11", "0");
					else
						c.put("sum11", channels.get("txnAmt"));
					
					
				}
			}	
				
//				支付失败
			for (int k = 0; k < ma.size(); k++) {
				Map<String, Object> shibai = ma.get(k);
				
				
				if(c.get("merchantId").equals( shibai.get("merchantId") )){
					if(shibai.get("a")==null)
						c.put("sum12", "0");
					else
						c.put("sum12", shibai.get("a"));
					
				}
				
				
			}
			
			
//			待支付
		for (int n = 0; n < mb.size(); n++) {
			Map<String, Object> daizhifu = mb.get(n);
			
			
			if(c.get("merchantId").equals( daizhifu.get("merchantId") )){
				if(daizhifu.get("daizhifu")==null)
					c.put("daizhifu", "0");
				else
					c.put("daizhifu", daizhifu.get("daizhifu"));
				
			}
			
			
			
		}
			
		
		
//		出金
			for (int a = 0; a < mc.size(); a++) {
				Map<String, Object> chujin = mc.get(a);
				
				
				if(c.get("merchantId").equals( chujin.get("merchantId") )){
					if(chujin.get("chujin")==null)
						c.put("chujin", "0");
					else
						c.put("chujin", chujin.get("chujin"));
					
				}
				
				
			}
			
			
			//入金总额
			for (int a = 0; a < mf.size(); a++) {
				Map<String, Object> txnAmt = mf.get(a);
				
				
				if(c.get("merchantId").equals( txnAmt.get("merchantId") )){
					if(txnAmt.get("txnAmt")==null)
						c.put("rujinsum", "0");
					else
						c.put("rujinsum", txnAmt.get("txnAmt"));
					
					
					
				}
				
				
			}
			
			
			//出金总额
			for (int a = 0; a < mj.size(); a++) {
				Map<String, Object> txnAmt = mj.get(a);
				
				
				if(c.get("merchantId").equals( txnAmt.get("merchantId") )){
					if(txnAmt.get("txnAmt")==null)
						c.put("chujinsum", "0");
					else
						c.put("chujinsum", txnAmt.get("txnAmt"));
					
				}
				
				
			}
			
			
				
//			手续费    上下游
			for(int d= 0; d < md.size(); d++){
				Map<String, Object> fee = md.get(d);
				
				if(c.get("merchantId").equals( fee.get("merchantId") )){
					if(fee.get("infee")==null)
						c.put("infee", "0");
					else
						c.put("infee", fee.get("infee"));
					
					if(fee.get("outfee")==null)
						c.put("outfee", "0");
					else
						c.put("outfee", fee.get("outfee"));
					
					
//					算实际金额  = 余额 + 下游-上游
					
					BigDecimal yue = new BigDecimal(c.get("balance").toString());
					
					BigDecimal infee = new BigDecimal(fee.get("infee").toString());
					
					BigDecimal outfee = new BigDecimal(fee.get("outfee").toString());
					
//					实际金额
					double doubleValue = yue.add(infee.subtract(outfee)).doubleValue();
					
					c.put("shiji", doubleValue);
					
				}
				
				
				
				
			}
			
			
			

				
		}
		
		
		request.setAttribute("list", list);
		
		
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("merId", merId);
		request.setAttribute("merShortName", merShortName);
		return "merchants_account_list";
		
	}
	
	

	/**
	 * 日期对账单
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/statementList", method = {RequestMethod.POST,RequestMethod.GET})
	public String statementList(HttpServletRequest request,Integer page,String date){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if(page==null){
			PageHelper.startPage(1, 10);
		}else{
			PageHelper.startPage(page, 10);
		}
		if(date == null || "".equals(date)){
			//前一天ca.add(Calendar.DATE, -1)
			
			Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
			ca.setTime(new Date()); //设置时间为当前时间 
			ca.add(Calendar.DATE, -1); //天数减1 
			 Date time = ca.getTime(); //结果
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
			date = df.format(time);
			
		}
//		默认查询前一天的日期
		List<Statement> list = statementService.selectByList(date);
		request.setAttribute("list", list);
		request.setAttribute("date", date);
		PageInfo<Statement> pageInfo = new PageInfo<Statement>(list);
		request.setAttribute("pageInfo", pageInfo);
		
		return "merchants_statement";
	}
	
	@RequestMapping(value = "/statementAdd", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Msg statementAdd(String date,HttpServletRequest request) throws UnsupportedEncodingException{
		
//		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		if("".equals(date))
			return Msg.error("日期格式错误");
		
		String time = OfTime.getShortTime();
		if(time.equals(date)){
			return Msg.error("不能添加当天时间！");
		}
		
		
		List<Statement> str = statementService.selectCheckDate(date);
		if(str.size() != 0){
			return Msg.error("当天数据已添加，请勿重复添加！");
		}
		
		
		
//		订单查出来的金额是  分
		 List<Map<String,Object>> txnAmt = statementService.selectOrder(date);
		 if(txnAmt.size() == 0)
				return Msg.error("当天没有交易数据！");
		 
			for(int d= 0; d < txnAmt.size(); d++){
				Map<String, Object> order = txnAmt.get(d);
		 
		 
		 		Statement s =new Statement();
				s.setDate(date);
				s.setStaMerchantId(order.get("merchantId") == null ? "0" : order.get("merchantId").toString());
				s.setGolden(order.get("rujin") == null ? "0" : order.get("rujin").toString());
				s.setWithdraw(order.get("chujin") == null ? "0" : order.get("chujin").toString());
				s.setUnpaid(order.get("daizhifu") == null ? "0" : order.get("daizhifu").toString());
				s.setNothing(order.get("shibai") == null ? "0" : order.get("shibai").toString());
				
				statementService.insert(s);
			}
		 
		 
		
		return Msg.success();
		
		
		
	}
	
	
	 
	
	
	
	
	
	
	
	/**
	 * 商户服务开通列表分页
	 * @param merId
	 * @param merShortName
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findAllByServe", method = {RequestMethod.POST,RequestMethod.GET})
	public String findAllByServe(String merId,String merShortName,HttpServletRequest request,HttpServletResponse response,Integer page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("merId", request.getParameter("merId"));
		map.put("merShortName", request.getParameter("merShortName"));
		List<Map<String,Object>> list = npayMerInfoService.selectByExampleMer(map,page);
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("merId", merId);
		request.setAttribute("merShortName", merShortName);
		return "merchants_serve_list";
		
	}
	
	
	/**
	 * 用户账户列表分页
	 * @param merId
	 * @param merShortName
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findAllByUser", method = {RequestMethod.POST,RequestMethod.GET})
	public String findAllByUser(String parameter,HttpServletRequest request,HttpServletResponse response,Integer page) throws UnsupportedEncodingException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parameter", request.getParameter("parameter"));
		List<Map<String,Object>> list = userService.selectByExampleUser(map,page);
		request.setAttribute("list", list);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("parameter", parameter);
		return "merchants_user_list";
		
	}
	
	
	/**
	 * 
	 * 商户导出按钮
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/export", method = {RequestMethod.POST,RequestMethod.GET})
	public void export(HttpServletRequest request, HttpServletResponse response){
		try {
			npayMerInfoService.export(response);
   		} catch (Exception e) {
   			e.printStackTrace();
   		} 
	}
	
	
	/**
	 * 商户开通服务导出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcle", method = {RequestMethod.POST,RequestMethod.GET})
	public void exportExcle(HttpServletRequest request, HttpServletResponse response){
		try {
			npayMerInfoService.exportExcle(response);
   		} catch (Exception e) {
   			e.printStackTrace();
   		} 
	}
	

}
