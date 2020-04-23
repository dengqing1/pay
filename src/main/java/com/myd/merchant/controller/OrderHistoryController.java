package com.myd.merchant.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myd.config.DynamicDataSource;
import com.myd.controller.BaseController;
import com.myd.entity.NpayOrder;
import com.myd.entity.User;
import com.myd.service.NpayOrderService;
import com.myd.util.PageInfo;
import com.myd.util.Query;


/**
 * 账务 --> 交易查询
 */
@Controller
public class OrderHistoryController extends BaseController {

	@Autowired
	private NpayOrderService npayOrderService;
	
	
	@RequestMapping("orderHistory")
	public String order(HttpServletRequest request ,
			Query query,Model model,@RequestParam Map<String,Object> params){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);
		List<NpayOrder> list = npayOrderService.list(params);
		this.calculate(model,list);
		
		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));		
		model.addAttribute("list", query.getDataTable(pageInfo));
		model.addAttribute("banks", npayOrderService.selectByBank());
		return "merchant/orderHistory";
	}
	
	@RequestMapping("orderHistory/search")
	@ResponseBody
	Map<String, Object> search(HttpServletRequest request,
			Query query, @RequestParam Map<String, Object> params)
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
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));
		return query.getDataTable(pageInfo);
	}
		
    @RequestMapping(value = "orderHistory/export", method = {RequestMethod.POST,RequestMethod.GET})
   	public void export(HttpServletResponse response,@RequestParam Map<String, Object> params){	
    	DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
       	try {
       		npayOrderService.export(response,params);
   		} catch (Exception e) {
   			e.printStackTrace();
   		} 
   	}
	
	
	@SuppressWarnings("unused")
	private void calculate(Model model,List<NpayOrder> list){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		Double inFeeAll = 0.00;//手续费用(全部)
		Double txnAmtAll = 0.00;//交易金额(全部)
		
		Double inFee_init = 0.00;//(初始状态)
		Double txnAmt_init = 0.00;
		Integer count_init = 0;
		
		Double inFee_success = 0.00;//(成功状态)
		Double txnAmt_success = 0.00;
		Integer count_success = 0;
		
		Double inFee_error = 0.00;//(失败状态)
		Double txnAmt_error = 0.00;
		Integer count_error = 0;
		
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				NpayOrder order = list.get(i);
				Double inFee = Double.parseDouble(order.getInFee());
				Double txnAmt = Double.parseDouble(order.getTxnAmts());
				Integer status = order.getStatus();
				if (status != null) {
					if (status == 1000) {
						inFee_init += inFee;
						txnAmt_init += txnAmt;
						++ count_init ;
					}else if(status == 1001){
						inFee_success += inFee;
						txnAmt_success += txnAmt;
						++ count_success ;
					}else if(status == 1002){
						inFee_error += inFee;
						txnAmt_error += txnAmt;
						++ count_error ;
					}
				}
				inFeeAll += inFee;
				txnAmtAll += txnAmt;
			}
		}
		
		NpayOrder all = new NpayOrder();
		all.setInFee(decimalFormat.format(inFeeAll)+"");
		all.setTxnAmts(decimalFormat.format(txnAmtAll) + "");
		all.setCount(list.size());
		
		NpayOrder status_init = new NpayOrder();
		status_init.setInFee(decimalFormat.format(inFee_init)+"");
		status_init.setTxnAmts(decimalFormat.format(txnAmt_init) + "");
		status_init.setCount(count_init);
		
		NpayOrder status_success = new NpayOrder();
		status_success.setInFee(decimalFormat.format(inFee_success)+"");
		status_success.setTxnAmts(decimalFormat.format(txnAmt_success) + "");
		status_success.setCount(count_success);
		
		NpayOrder status_error = new NpayOrder();
		status_error.setInFee(decimalFormat.format(inFee_error)+"");
		status_error.setTxnAmts(decimalFormat.format(txnAmt_error) + "");
		status_error.setCount(count_error);
		
		
		model.addAttribute("all", all);//全部
		model.addAttribute("status", status_init);//初始状态
		model.addAttribute("success", status_success);//成功
		model.addAttribute("error", status_error);//失败
	}
}
