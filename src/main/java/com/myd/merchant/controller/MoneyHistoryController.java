package com.myd.merchant.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 账务 --> 钱流水
 */
@Controller
public class MoneyHistoryController extends BaseController{

	@Autowired
	private NpayOrderService npayOrderService;
	
	/**
	 * 钱流水
	 * @return
	 */
	@RequestMapping("moneyHistory")
	public String history(HttpServletRequest request,
			Query query,Model model,@RequestParam Map<String,Object> params){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		User user = super.getUser(request);
		String merchantid = "";
		if (user != null ) {
			merchantid = user.getMerchantid();
		}
		params.put("merchantId", merchantid);
		params.put("status", 1001);
		List<NpayOrder> list = npayOrderService.list(params);
		this.calculate(model,list);
		
		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));		
		model.addAttribute("list", query.getDataTable(pageInfo));
		model.addAttribute("banks", npayOrderService.selectByBank());
		return "merchant/moneyHistory";
	}
	
	@RequestMapping("moneyHistory/search")
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
		params.put("status", 1001);

		params.put("offset", query.getPageNum());
		params.put("limit", query.getPageSize());
		query.setPages(npayOrderService.count(params));
		PageInfo<NpayOrder> pageInfo = new PageInfo<>(npayOrderService.list(params));
		return query.getDataTable(pageInfo);
	}
	
	@SuppressWarnings("unused")
	private void calculate(Model model,List<NpayOrder> list){
		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		Double inFeeAll = 0.00;//手续费用
		Double txnAmtAll = 0.00;//交易金额
		Double inFeeBank = 0.00;
		Double txnAmtBank = 0.00;
		Integer countBank = 0;
		
		Double inFeeDF = 0.00;
		Double txnAmtDF = 0.00;
		Integer countDF = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				NpayOrder order = list.get(i);
				Double inFee = Double.parseDouble(order.getInFee());
				Double txnAmt = Double.parseDouble(order.getTxnAmts());
				if ("bank".equals(order.getGateway())) {
					inFeeBank += inFee;
					txnAmtBank += txnAmt;
					++ countBank ;
				}else if("daifu".equals(order.getGateway())){
					inFeeDF += inFee;
					txnAmtDF += txnAmt;
					++ countDF ;
				}
				inFeeAll += inFee;
				txnAmtAll += txnAmt;
			}
		}
		
		NpayOrder all = new NpayOrder();
		all.setInFee(decimalFormat.format(inFeeAll)+"");
		all.setTxnAmts(decimalFormat.format(txnAmtAll) + "");
		all.setCount(list.size());
		
		NpayOrder bank = new NpayOrder();
		bank.setInFee(decimalFormat.format(inFeeBank)+"");
		bank.setTxnAmts(decimalFormat.format(txnAmtBank) + "");
		bank.setCount(countBank);
		
		NpayOrder df = new NpayOrder();
		df.setInFee(decimalFormat.format(inFeeDF)+"");
		df.setTxnAmts(decimalFormat.format(txnAmtDF) + "");
		df.setCount(countDF);
		
		model.addAttribute("all", all);
		model.addAttribute("bank", bank);
		model.addAttribute("daifu", df);
	}
}
