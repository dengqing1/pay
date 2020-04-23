package com.myd.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.myd.entity.KLTDuanXin;
import com.myd.entity.NpayBfInfo;
import com.myd.entity.NpayChannels;
import com.myd.entity.NpayDaifuRoutes;
import com.myd.entity.NpayInRoutes;
import com.myd.entity.NpayKJ;
import com.myd.entity.NpayMerFeeRates;
import com.myd.entity.NpayMerInfo;
import com.myd.entity.NpayOrder;
import com.myd.entity.OrdersBank;
import com.myd.entity.OrdersDaifu;
import com.myd.manager.service.NpayDaifuRoutesService;
import com.myd.service.NpayBfInfoService;
import com.myd.service.NpayChannelsService;
import com.myd.service.NpayInRoutesService;
import com.myd.service.NpayMerFeeRatesService;
import com.myd.service.NpayMerInfoService;
import com.myd.service.NpayOrderService;
import com.myd.service.PayService;
import com.myd.util.Base64CodeUtil;
import com.myd.util.DateUtil;
import com.myd.util.EntityIsNullUtil;
import com.myd.util.PayUtil;
import com.myd.util_wx.Pay10088Util;

@Service("payService")
public class PayServiceimpl implements PayService {

	private static Logger logger = Logger.getLogger(PayServiceimpl.class);

	@Autowired
	private NpayMerInfoService npayMerInfoService;
	@Autowired
	private NpayOrderService nPayOrderService;
	@Autowired
	private NpayInRoutesService npayInRoutesService;
	@Autowired
	private NpayDaifuRoutesService npayDaifuRoutesService;
	@Autowired
	private NpayChannelsService npayChannelsService;
	@Autowired
	private NpayMerFeeRatesService npayMerFeeRatesService;
	@Autowired
	private NpayBfInfoService npayBfInfoServcie;

	@Override
	public int payConfirmBank(OrdersBank orderInfo) {
		// TODO Auto-generated method stub

		// 网银支付
		List<String> filedList = EntityIsNullUtil.checkObjFieldIsNull(orderInfo);
		// 1.判断字段是否完整
		if (filedList.size() > 2) {

			return 0;
		}

		// 如果两个是空的判断是不是指定可以为可空的字符串
		if (filedList.size() > 0 && filedList.size() <= 2) {
			if (isRightFiled(filedList, orderInfo.getGateway())) {
				// 是指定的字符串为空
				// 判断url是否正确
				if ((!vaildateUrl(orderInfo.getBackUrl())) || (!vaildateUrl(orderInfo.getFrontUrl()))) {
					// url格式不正确

					return 1;
				}

				// 去验证签名是否正确
				int res = confirmSignMethod(orderInfo);
				if (res == 0) {
					// 没有该商户
					return 3;

				} else if (res == 1) {
					// 签名错误
					return 2;

				} else if (res == 200) {
					// 通过
					return 200;
				} else {
					// 未知错误
					return 10;

				}
			} else {
				// 必须的字段为空了
				return 0;

			}
		}

		// 没有为空的字符串就直接验证签名是否正确
		if (filedList.size() == 0) {

			if ((!vaildateUrl(orderInfo.getBackUrl())) || (!vaildateUrl(orderInfo.getFrontUrl()))) {
				// url格式不正确

				return 1;
			}

			int res = confirmSignMethod(orderInfo);
			if (res == 0) {
				// 没有该商户
				return 3;

			} else if (res == 1) {
				// 签名错误
				return 2;

			} else if (res == 200) {
				// 通过
				return 200;
			} else {
				// 未知错误
				return 10;

			}

		}

		return 10;
	}

	@Override
	public int payConfirmDaifu(OrdersDaifu orderInfoDaifu) {
		// TODO Auto-generated method stub

		// 代付
		List<String> filedList = EntityIsNullUtil.checkObjFieldIsNull(orderInfoDaifu);
		// 1.判断字段是否完整
		if (filedList.size() > 1) {

			return 0;
		}

		// 如果两个是空的判断是不是指定可以为可空的字符串
		if (filedList.size() > 0 && filedList.size() <= 1) {
			if (isRightFiled(filedList, orderInfoDaifu.getGateway())) {
				// 是指定的字符串为空
				// 验证信息
				if (!vaildateUrl(orderInfoDaifu.getBackUrl())) {
					// url格式不正确
					return 1;
				}

				int res = confirmSignMethod(orderInfoDaifu);
				if (res == 0) {
					// 没有该商户
					return 3;

				} else if (res == 1) {
					// 签名错误
					return 2;

				} else if (res == 200) {
					// 通过
					return 200;
				} else {
					// 未知错误
					return 10;

				}

			} else {
				// 不是指定的值为空
				return 0;

			}
		}

		// 没有为空的字符串就直接验证签名是否正确
		if (filedList.size() == 0) {
			if (!vaildateUrl(orderInfoDaifu.getBackUrl())) {
				// url格式不正确
				return 1;
			}

			int res = confirmSignMethod(orderInfoDaifu);

			if (res == 0) {
				// 没有该商户
				return 3;

			} else if (res == 1) {
				// 签名错误
				return 2;

			} else if (res == 200) {
				// 通过
				return 200;
			} else {
				// 未知错误
				return 10;

			}

		}
		return 10;
	}

	/**
	 * 判断是不是指定可以不传的字符串
	 * 
	 * @param list
	 * @return
	 */
	public boolean isRightFiled(List<String> list, String getway) {
		if (list == null) {
			// 没有直接返回true
			return true;
		}
		if ("bank".equals(getway.trim())) {

			if (list.size() >= 0 && list.size() <= 2) {
				list.remove("userId");
				list.remove("customerIp");
				// 如果还有的话就返回false
				if (list.size() == 0) {
					return true;
				}
			}
		} else if ("daifu".equals(getway.trim())) {

			if (list.size() >= 0 && list.size() <= 1) {
				list.remove("purpose");
				// 如果还有的话就返回false
				if (list.size() == 0) {
					return true;
				}
			}

		}

		return false;

	}

	/**
	 * 验证签名是否正确(0没有该商户，1签名信息错误,200全部通过<有该商户，并且验签通过>,2未知错误)
	 * 
	 * @param orderInfo
	 * @return
	 */
	public int confirmSignMethod(Object orderInfo) {
		String singNature = "";// 传过来的签名信息
		// Base64 base64 = new Base64();
		String merId = "";// 商户号码
		if (orderInfo instanceof OrdersBank) {
			orderInfo = (OrdersBank) orderInfo;
			singNature = ((OrdersBank) orderInfo).getSignature();
			merId = ((OrdersBank) orderInfo).getMerchantId();
			// 进行解码
			((OrdersBank) orderInfo).setSubject(Base64Decoding(((OrdersBank) orderInfo).getSubject()));
			((OrdersBank) orderInfo).setBody(Base64Decoding(((OrdersBank) orderInfo).getBody()));

		} else if (orderInfo instanceof OrdersDaifu) {

			orderInfo = (OrdersDaifu) orderInfo;
			singNature = ((OrdersDaifu) orderInfo).getSignature();
			merId = ((OrdersDaifu) orderInfo).getMerchantId();
			// 进行解码
			((OrdersDaifu) orderInfo).setSubject(Base64Decoding(((OrdersDaifu) orderInfo).getSubject()));
			((OrdersDaifu) orderInfo).setBody(Base64Decoding(((OrdersDaifu) orderInfo).getBody()));

		}

		// 是不是根据商户号来判断的
		try {
			SortedMap<String, Object> map = PayUtil.objectToSortedMap(orderInfo);
			// 需要根据商户号去拿秘钥

			NpayMerInfo npayMerInfo = npayMerInfoService.getMerInfoById(merId);
			if (npayMerInfo == null) {
				return 0;
			} else {
				String tempSingNature = PayUtil.signMethod(map, npayMerInfo.getMerSecretKey());// 咱们根据秘钥生成的签名信息
				if (singNature.equals(tempSingNature)) {
					// 通过
					return 200;
				} else {
					// 验签失败
					return 1;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 2;

	}

	/**
	 * 判断传来的url是否包含&和？不包含 返回true
	 * 
	 * @param url
	 * @return
	 */
	public boolean vaildateUrl(String url) {

		if (url != null) {
			if (url.contains("&") || url.contains("?") || url.contains("？")) {
				return false;
			} else {
				return true;
			}

		}

		return false;
	}

	/**
	 * base64解码
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String Base64Decoding(String code) {

		if (code == null) {

			return null;
		} else {
			Base64 base64 = new Base64();
			byte[] b = code.getBytes();
			String a = null;
			try {
				a = new String(base64.decode(b), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return a;

		}

	}

	/**
	 * 保存订单
	 */
	@Override
	public int addOrder(NpayOrder order, Object obj) {

		NpayBfInfo npayBfInfo = new NpayBfInfo();
		Date date = new Date();

		if (obj instanceof OrdersBank) {
			// 银行支付
			OrdersBank obank = (OrdersBank) obj;
			// 根据商户号及交易金额查询出渠道号及渠道id
			List<NpayInRoutes> InRoutes = npayInRoutesService.getRoutesBychaneId(obank.getMerchantId(),
					Integer.parseInt(obank.getTxnAmt()));
			// 查出有多个渠道 就取第一个
			NpayInRoutes npayInRoutes = null;
			NpayChannels npayChannels = null;
			NpayMerFeeRates npayMerFeeRates = null;
			boolean flag = false;
			boolean flag1 = false;
			if (InRoutes != null && InRoutes.size() != 0) {
				for (int i = 0; i < InRoutes.size(); i++) {
					npayInRoutes = InRoutes.get(i);

					if (npayInRoutes != null) {

						npayChannels = npayChannelsService.getChannels(obank.getGateway(),
								npayInRoutes.getChannelabbr(), npayInRoutes.getChannelmerid());// 得到渠道信息
					} 
					

					if (npayChannels != null) {

						
						int type = obank.getDcType();
						String cradType = "debit";
						if (type != 0) {
							cradType = "credit";
						}

						Map<String, Object> map = new HashMap<String, Object>();
						map.put("chanId", obank.getMerchantId());
						map.put("getway", obank.getGateway());
						map.put("cardType", cradType);
						map.put("channelAbbr", npayInRoutes.getChannelabbr());
						map.put("channelMerId", npayInRoutes.getChannelmerid());

						// 根据商户号，网关，及卡的类型查询出费率信息 渠道简称 渠道商户号
						npayMerFeeRates = npayMerFeeRatesService.getNpayMerFeeRates(map);// 得到费率信息
						
//						先判段渠道全部通了沒有
						
						if(npayMerFeeRates != null){
						
							// 单日渠道累计金额
							Integer sum = nPayOrderService.selectSumTxnAmt(npayInRoutes.getChannelabbr(), null);
							if (sum == null)
								sum = 0;
							// 加上单前交易金额
							sum = new BigDecimal(sum).add(new BigDecimal(Integer.parseInt(obank.getTxnAmt())))
									.stripTrailingZeros().intValue();
							// 单日渠道规定金额
							if (sum <= npayChannels.getAccumulative()) {
	
								flag = true;
	
								// 单日商户累计金额
								Integer sum1 = nPayOrderService.selectSumTxnAmt(null, obank.getMerchantId());
								if (sum1 == null)
									sum1 = 0;
	
								// 加上单前交易金额
								sum1 = new BigDecimal(sum1).add(new BigDecimal(Integer.parseInt(obank.getTxnAmt())))
										.stripTrailingZeros().intValue();
								// 单日商户规定金额
								if (sum1 <= npayInRoutes.getAccumulative()) {
									// 结束循环
									flag1 = true;
									break;
								}
	
							}
	
						}
					}

				}
			}

			if (InRoutes == null || InRoutes.size() == 0) {
				// 支付通道未开通
				logger.info("在InRoutes表查不到对应的信息>>>>" + JSON.toJSONString(obank));
				return 1;

			}
			
			if (npayChannels == null) {
				// 支付通道未开通
				logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(obank));
				return 2;

			}
			
			if (npayMerFeeRates == null) {

				logger.info("查不到对应的费率信息>>>>" + JSON.toJSONString(obank));
				// 查询费率失败
				return 3;

			}
			
			

			if (!flag) {
				// 支付通道金额已满
				logger.info("channels支付通道金额已满 " + npayChannels.getAccumulative());
				return 20;

			}

			if (!flag1) {
				// 商户金额已满
				logger.info("inroute商户金额已满" + npayInRoutes.getAccumulative());
				return 21;

			}

			// 上游手续费
			String outFee = "0";
			if ("fix".equals(npayChannels.getFeeType())) {
				outFee = String.valueOf(npayChannels.getFeeAmount());
			} else {
				BigDecimal temp = new BigDecimal(
						(float) npayChannels.getFeeAmount() / 10000 * Integer.parseInt(obank.getTxnAmt()));
				outFee = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			}

			// 下游手续费
			String feilv = "0";
			if ("fix".equals(npayMerFeeRates.getFeeType())) {
				feilv = String.valueOf(npayMerFeeRates.getFeeAmount());
			} else {
				BigDecimal temp = new BigDecimal(
						(float) npayMerFeeRates.getFeeAmount() / 10000 * Integer.parseInt(obank.getTxnAmt()));
				feilv = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			}

			Map<String, String> info = new HashMap<>();
			info.put("customerNm", obank.getCustomerNm());
			info.put("phoneNo", obank.getPhoneNo());
			String cusinfo = JSON.toJSONString(info);

			order.setMerchantid(obank.getMerchantId());
			order.setMerorderid(obank.getMerOrderId());
			order.setTxnamt(Integer.parseInt(obank.getTxnAmt()));
			order.setFronturl(obank.getFrontUrl());
			order.setBackurl(obank.getBackUrl());
			order.setSubject(Base64CodeUtil.Base64Decoding(obank.getSubject()));
			order.setBody(Base64CodeUtil.Base64Decoding(obank.getBody()));
			order.setUserid(obank.getUserId());
			order.setSignature(obank.getSignature().replace(" ", "+"));
			order.setSignmethod(obank.getSignMethod());
			order.setCustomerinfo(cusinfo);
			order.setBankid(obank.getBankId());
			order.setDctype(obank.getDcType());
			order.setCustomerip(obank.getCustomerIp());
			order.setGateway(obank.getGateway());
			order.setAccno(obank.getAccNo());

			// order.setOrderid(DateUtil.getOrderId(date));
			order.setOrderid(Pay10088Util.generateOrderId()); // 20为随机字符串
			order.setStatus(1000);// 待支付
			order.setStatusdesc("订单待支付");
			order.setNotifytimes(123);// 通知时间
			order.setRefundtimes((byte) 0);// 退款时间
			order.setInFee(feilv);// 下家手续费金额(需要根据金额及下家的手续费计算)
			order.setInFeeAmount(npayMerFeeRates.getFeeAmount().toString());// 下家费率(根据下家提供的进行查询)
			order.setInFeeType(npayMerFeeRates.getFeeType());// 下家手续费类型
			order.setOutFee(outFee);// 上家手续费金额(根据金额及费率进行计算)
			order.setOutFeeAmount(npayChannels.getFeeAmount().toString());// 上家费率(在表里面查询)
			order.setOutFeeType(npayChannels.getFeeType());// 上家手续费类型
			order.setChannelMerAbbr(npayChannels.getChannelMerAbbr());// 渠道商户名缩写(根据商户号去inrote表里面查询)
			order.setChannelId(npayChannels.getChannelId());// 渠道号
			order.setChannelMerId(npayChannels.getChannelMerId());// 渠道商户号
			order.setCheckStatus(0);// 0:没有对账, 1:对账成功, 默认0
			order.setCstatus("  ");
			order.setLastUpdate(DateUtil.getDateFormart(date));

			npayBfInfo.setBackurl(obank.getBackUrl());
			npayBfInfo.setFronturl(obank.getFrontUrl());
			npayBfInfo.setMerorderid(obank.getMerchantId());

		} else if (obj instanceof OrdersDaifu) {
			// 代付
			// 银行支付
			OrdersDaifu odaifu = (OrdersDaifu) obj;

			// 根据商户号及交易金额查询出渠道号及渠道id
			List<NpayDaifuRoutes> DaifuRoutes = npayDaifuRoutesService.selectByChartId(odaifu.getMerchantId(),
					Integer.parseInt(odaifu.getTxnAmt()));
			NpayChannels npayChannels = null;
			NpayDaifuRoutes npayDaifuRoutes = null;
			NpayMerFeeRates npayMerFeeRates = null;
			if (DaifuRoutes != null && DaifuRoutes.size() != 0) {
				for (int i = 0; i < DaifuRoutes.size(); i++) {
					npayDaifuRoutes = DaifuRoutes.get(i);

			
			
					if (npayDaifuRoutes != null) {
		
						npayChannels = npayChannelsService.getChannels(odaifu.getGateway(), npayDaifuRoutes.getChannelabbr(),
								npayDaifuRoutes.getChannelmerid());// 得到渠道信息
					
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("chanId", odaifu.getMerchantId());
						map.put("getway", odaifu.getGateway());
						map.put("cardType", null);
						map.put("channelAbbr", npayDaifuRoutes.getChannelabbr());
						map.put("channelMerId", npayDaifuRoutes.getChannelmerid());
			
						// 根据商户号，网关，及卡的类型查询出费率信息
						npayMerFeeRates = npayMerFeeRatesService.getNpayMerFeeRates(map);// 得到费率信息
						
					} 
					
					 if(npayDaifuRoutes != null && npayChannels != null && npayMerFeeRates != null){
						 
						 break ;
					 }
					 
					
				}
			}
			
			if (DaifuRoutes == null || DaifuRoutes.size() == 0) {
				// 支付通道未开通
				logger.info("在InRoutes表查不到对应的信息>>>>" + JSON.toJSONString(odaifu));
				return 1;

			}
			
			
			if (npayChannels == null) {
				// 支付通道未开通
				return 2;
			}
			
					
			if (npayMerFeeRates == null) {
				// 查询费率失败
				return 3;

			}

			// 上游手续费
			String outFee = "0";
			if ("fix".equals(npayChannels.getFeeType())) {
				outFee = String.valueOf(npayChannels.getFeeAmount());
			} else {
				BigDecimal temp = new BigDecimal(
						(float) npayChannels.getFeeAmount() / 10000 * Integer.parseInt(odaifu.getTxnAmt()));
				outFee = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			}

			Map<String, String> info = new HashMap<>();
			info.put("customerNm", odaifu.getCustomerNm());
			info.put("phoneNo", odaifu.getPhoneNo());
			String cusinfo = JSON.toJSONString(info);

			order.setSignmethod(odaifu.getSignMethod());
			order.setSignature(odaifu.getSignature().replace(" ", "+"));
			order.setVersion(odaifu.getVersion());
			order.setTxntype(odaifu.getTxnType());
			order.setTxnsubtype(odaifu.getTxnSubType());
			order.setBiztype(odaifu.getBizType());
			order.setAccesstype(odaifu.getAccessType());
			order.setAccessmode(odaifu.getAccessMode());
			order.setMerchantid(odaifu.getMerchantId());
			order.setMerorderid(odaifu.getMerOrderId());
			order.setAccno(odaifu.getAccNo());
			order.setCustomerinfo(cusinfo);
			order.setPpflag(odaifu.getPpFlag());
			order.setTxntime(odaifu.getTxnTime());
			order.setTxnamt(Integer.parseInt(odaifu.getTxnAmt()));
			order.setCurrency(odaifu.getCurrency());
			order.setBackurl(odaifu.getBackUrl());
			order.setPaytype(odaifu.getPayType());
			order.setBankid(odaifu.getBankId());
			order.setGateway(odaifu.getGateway());
			order.setSubject(Base64CodeUtil.Base64Decoding(odaifu.getSubject()));
			order.setBody(Base64CodeUtil.Base64Decoding(odaifu.getBody()));
			// 用途跟开行名字没有

			order.setOrderid(DateUtil.getOrderId(date));
			order.setStatus(1000);// 待支付
			order.setStatusdesc("订单待支付");
			order.setNotifytimes(123);// 通知时间
			order.setRefundtimes((byte) 0);// 退款时间
			// 把 费率的元 换成分
			order.setInFee(npayMerFeeRates.getFeeAmount() + "");// 下家手续费金额(需要根据金额及下家的手续费计算)
			order.setInFeeAmount((npayMerFeeRates.getFeeAmount()) + "");// 下家费率(根据下家提供的进行查询)
			order.setInFeeType(npayMerFeeRates.getFeeType());// 下家手续费类型
			order.setOutFee(outFee);// 上家手续费金额(根据金额及费率进行计算)
			order.setOutFeeAmount(npayChannels.getFeeAmount().toString());// 上家费率(在表里面查询)
			order.setOutFeeType(npayChannels.getFeeType());// 上家手续费类型
			order.setChannelMerAbbr(npayChannels.getChannelMerAbbr());// 渠道商户名缩写(根据商户号去inrote表里面查询)
			order.setChannelId(npayChannels.getChannelId());// 渠道号
			order.setChannelMerId(npayChannels.getChannelMerId());// 渠道商户号
			order.setCheckStatus(0);// 0:没有对账, 1:对账成功, 默认0
			order.setCstatus("");
			order.setLastUpdate(DateUtil.getDateFormart(date));

			npayBfInfo.setBackurl(odaifu.getBackUrl());
			npayBfInfo.setMerorderid(odaifu.getMerchantId());

		} else if (obj instanceof NpayKJ) {
			// 快捷支付
			NpayKJ kj = (NpayKJ) obj;
			// 根据商户号及交易金额查询出渠道号及渠道id
			List<NpayInRoutes> InRoutes = npayInRoutesService.getRoutesBychaneId(kj.getMerchantId(),
					Integer.parseInt(kj.getTxnAmt()));

			// 查出有多个渠道 就取第一个
			NpayInRoutes npayInRoutes = null;
			NpayChannels npayChannels = null;
			NpayMerFeeRates npayMerFeeRates = null;
			boolean flag = false;
			boolean flag1 = false;
				if (InRoutes != null && InRoutes.size() != 0) {
					for (int i = 0; i < InRoutes.size(); i++) {
						npayInRoutes = InRoutes.get(i);

						if (npayInRoutes != null) {

							npayChannels = npayChannelsService.getChannels(kj.getGateway(),
									npayInRoutes.getChannelabbr(), npayInRoutes.getChannelmerid());// 得到渠道信息
						} 
						
						
					
						

						if (npayChannels != null) {

							
							String type = kj.getDcType();
							String cradType = "debit";
							if (!"0".equals(type) ) {
								cradType = "credit";
							}

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("chanId", kj.getMerchantId());
							map.put("getway", kj.getGateway());
							map.put("cardType", cradType);
							map.put("channelAbbr", npayInRoutes.getChannelabbr());
							map.put("channelMerId", npayInRoutes.getChannelmerid());

							// 根据商户号，网关，及卡的类型查询出费率信息 渠道简称 渠道商户号
							npayMerFeeRates = npayMerFeeRatesService.getNpayMerFeeRates(map);// 得到费率信息
							
//							先判段渠道全部通了沒有
							
							if(npayMerFeeRates != null){
							
								// 单日渠道累计金额
								Integer sum = nPayOrderService.selectSumTxnAmt(npayInRoutes.getChannelabbr(), null);
								if (sum == null)
									sum = 0;
								// 加上单前交易金额
								sum = new BigDecimal(sum).add(new BigDecimal(Integer.parseInt(kj.getTxnAmt())))
										.stripTrailingZeros().intValue();
								// 单日渠道规定金额
								if (sum <= npayChannels.getAccumulative()) {
	
									flag = true;
	
									// 单日商户累计金额
									Integer sum1 = nPayOrderService.selectSumTxnAmt(null, kj.getMerchantId());
									if (sum1 == null)
										sum1 = 0;
	
									// 加上单前交易金额
									sum1 = new BigDecimal(sum1).add(new BigDecimal(Integer.parseInt(kj.getTxnAmt())))
											.stripTrailingZeros().intValue();
									// 单日商户规定金额
									if (sum1 <= npayInRoutes.getAccumulative()) {
										// 结束循环
										flag1 = true;
										break;
									}
	
								}
							}
						}

					}
				}

				
				if (InRoutes == null || InRoutes.size() == 0) {
					// 支付通道未开通
					logger.info("在InRoutes表查不到对应的信息>>>>" + JSON.toJSONString(kj));
					return 1;

				}
				
				if (npayChannels == null) {
					// 支付通道未开通
					logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(kj));
					return 2;

				}
				
				if (npayMerFeeRates == null) {

					logger.info("查不到对应的费率信息>>>>" + JSON.toJSONString(kj));
					// 查询费率失败
					return 3;

				}
				
				

				if (!flag) {
					// 支付通道金额已满
					logger.info("channels支付通道金额已满 " + npayChannels.getAccumulative());
					return 20;

				}

				if (!flag1) {
					// 商户金额已满
					logger.info("inroute商户金额已满" + npayInRoutes.getAccumulative());
					return 21;

				}

			

				// 上游手续费
				String outFee = "0";
				if ("fix".equals(npayChannels.getFeeType())) {
					outFee = String.valueOf(npayChannels.getFeeAmount());
				} else {
					BigDecimal temp = new BigDecimal(
							(float) npayChannels.getFeeAmount() / 10000 * Integer.parseInt(kj.getTxnAmt()));
					outFee = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
				}

				// 下游手续费
				String feilv = "0";
				if ("fix".equals(npayMerFeeRates.getFeeType())) {
					feilv = String.valueOf(npayMerFeeRates.getFeeAmount());
				} else {
					BigDecimal temp = new BigDecimal(
							(float) npayMerFeeRates.getFeeAmount() / 10000 * Integer.parseInt(kj.getTxnAmt()));
					feilv = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
				}
			
			
			
			
			
			
			
			
			
			
/*
			NpayChannels npayChannels = null;
			if (npayInRoutes != null) {
				npayChannels = npayChannelsService.getChannels(kj.getGateway(), npayInRoutes.getChannelabbr(),
						npayInRoutes.getChannelmerid());// 得到渠道信息
			} else {
				logger.info("在route表查不到对应的信息>>>>" + JSON.toJSONString(kj));
				// 支付金额不正确
				return 1;
			}

			if (npayChannels == null) {
				// 支付通道未开通
				logger.info("在channels表查不到对应的信息>>>>" + JSON.toJSONString(kj));
				return 2;

			}

			int type = Integer.parseInt(kj.getDcType());
			String cradType = "debit";
			if (type != 0) {
				cradType = "credit";
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chanId", kj.getMerchantId());
			map.put("getway", kj.getGateway());
			map.put("cardType", cradType);
			map.put("channelAbbr", npayInRoutes.getChannelabbr());
			map.put("channelMerId", npayInRoutes.getChannelmerid());

			// 根据商户号，网关，及卡的类型查询出费率信息
			NpayMerFeeRates npayMerFeeRates = npayMerFeeRatesService.getNpayMerFeeRates(map);// 得到费率信息
			if (npayMerFeeRates == null) {

				logger.info("查不到对应的费率信息>>>>" + JSON.toJSONString(kj));
				// 查询费率失败
				return 3;

			}
			String feilv = "0";
			if ("fix".equals(npayMerFeeRates.getFeeType())) {
				feilv = String.valueOf(npayMerFeeRates.getFeeAmount());
			} else {
				BigDecimal temp = new BigDecimal(
						npayMerFeeRates.getFeeAmount() / 10000 * Integer.parseInt(kj.getTxnAmt()));
				feilv = temp.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			}
*/
				
				
			Map<String, String> info = new HashMap<>();
			info.put("customerNm", kj.getCustomerNm());
			info.put("phoneNo", kj.getPhoneNo());
			String cusinfo = JSON.toJSONString(info);

			order.setMerchantid(kj.getMerchantId());
			order.setMerorderid(kj.getMerOrderId());
			order.setTxnamt(Integer.parseInt(kj.getTxnAmt()));

			order.setSignature(kj.getSignature());
			order.setCustomerinfo(cusinfo);
			order.setDctype(Byte.parseByte(kj.getDcType()));
			order.setGateway(kj.getGateway());
			order.setAccno(kj.getAccNo());
			order.setCvv2(kj.getCvv2());
			order.setPayeridno(kj.getPayerIdNo());
			order.setAcctvaliddate(kj.getAcctValidDate());
			order.setBackurl(kj.getBackUrl());

			order.setOrderid(DateUtil.getOrderId(date));
			order.setStatus(1000);// 待支付
			order.setStatusdesc("订单待支付");
			order.setNotifytimes(123);// 通知时间
			order.setRefundtimes((byte) 0);// 退款时间
			order.setInFee(feilv);// 下家手续费金额(需要根据金额及下家的手续费计算)
			order.setInFeeAmount(npayMerFeeRates.getFeeAmount().toString());// 下家费率(根据下家提供的进行查询)
			order.setInFeeType(npayMerFeeRates.getFeeType());// 下家手续费类型
			order.setOutFee(outFee);// 上家手续费金额(根据金额及费率进行计算)
			order.setOutFeeAmount(npayChannels.getFeeAmount().toString());// 上家费率(在表里面查询)
			order.setOutFeeType(npayChannels.getFeeType());// 上家手续费类型
			order.setChannelMerAbbr(npayChannels.getChannelMerAbbr());// 渠道商户名缩写(根据商户号去inrote表里面查询)
			order.setChannelId(npayChannels.getChannelId());// 渠道号
			order.setChannelMerId(npayChannels.getChannelMerId());// 渠道商户号
			order.setCheckStatus(0);// 0:没有对账, 1:对账成功, 默认0
			order.setCstatus(" ");
			order.setLastUpdate(DateUtil.getDateFormart(date));

			npayBfInfo.setMerorderid(kj.getMerOrderId());
			npayBfInfo.setOrderid(order.getOrderid());
			
			
			order.setSubject(Base64CodeUtil.Base64Decoding(kj.getSubject()));
			order.setBody(Base64CodeUtil.Base64Decoding(kj.getBody()));
			
			
			npayBfInfoServcie.addNpayBfInfo(npayBfInfo);
		}

		nPayOrderService.addOrder(order);
		logger.info("生成订单信息>>>>" + JSON.toJSONString(order));

		return 200;

	}

}
