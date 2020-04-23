package com.myd.util;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.myd.entity.BatchDaifuData;
import com.myd.entity.Gather;
import com.myd.entity.NpayMerchantBalance2018;
import com.myd.entity.NpayOrder;
import com.myd.entity.NpayTf56Bank;
import com.myd.service.NpayMerchantBalanceService;
import com.myd.service.NpayOrderService;
import com.myd.serviceimpl.NpayOrderServiceimpl;
/**
 * excel读写工具类
 * @author sun.kai
 * 2016年8月21日
 */
@Component
public class POIUtil {
	private static Logger logger  = Logger.getLogger(POIUtil.class);
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";
	
	@Autowired
	private static  NpayMerchantBalanceService npayMerchantBalanceService;

	
	/**
	 * 读入excel文件，解析后返回
	 * @param file
	 * @throws IOException 
	 */
	public static List<String[]> readExcel(MultipartFile file) throws IOException{
		//检查文件
		checkFile(file);
    	//获得Workbook工作薄对象
    	Workbook workbook = getWorkBook(file);
    	//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
    	List<String[]> list = new ArrayList<String[]>();
    	if(workbook != null){
    		for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
    			//获得当前sheet工作表
        		Sheet sheet = workbook.getSheetAt(sheetNum);
        		if(sheet == null){
        			continue;
        		}
        		//获得当前sheet的开始行
        		int firstRowNum  = sheet.getFirstRowNum();
        		//获得当前sheet的结束行
        		int lastRowNum = sheet.getLastRowNum();
        		//循环除了第一行的所有行
        		for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
        			//获得当前行
        			Row row = sheet.getRow(rowNum);
        			if(row == null){
        				continue;
        			}
        			//获得当前行的开始列
        			int firstCellNum = row.getFirstCellNum();
        			//获得当前行的列数
        			int lastCellNum = row.getPhysicalNumberOfCells();
        			String[] cells = new String[row.getPhysicalNumberOfCells()];
        			//循环当前行
        			for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
        				Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
        			}
        			list.add(cells);
        		}
    		}
    	}
		return list;
    }
	public static void checkFile(MultipartFile file) throws IOException{
		//判断文件是否存在
    	if(null == file){
    		logger.error("文件不存在！");
    		throw new FileNotFoundException("文件不存在！");
    	}
		//获得文件名
    	String fileName = file.getOriginalFilename();
    	//判断文件是否是excel文件
    	if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
    		logger.error(fileName + "不是excel文件");
    		throw new IOException(fileName + "不是excel文件");
    	}
	}
	public static Workbook getWorkBook(MultipartFile file) {
		//获得文件名
    	String fileName = file.getOriginalFilename();
    	//创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			//获取excel文件的io流
			InputStream is = file.getInputStream();
			//根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if(fileName.endsWith(xls)){
				//2003
				workbook = new HSSFWorkbook(is);
			}else if(fileName.endsWith(xlsx)){
				//2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return workbook;
	}
	public static String getCellValue(Cell cell){
		String cellValue = "";
		if(cell == null){
			return cellValue;
		}
		//把数字当成String来读，避免出现1读成1.0的情况
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		//判断数据的类型
        switch (cell.getCellType()){
	        case Cell.CELL_TYPE_NUMERIC: //数字
	            cellValue = String.valueOf(cell.getNumericCellValue());
	            break;
	        case Cell.CELL_TYPE_STRING: //字符串
	            cellValue = String.valueOf(cell.getStringCellValue());
	            break;
	        case Cell.CELL_TYPE_BOOLEAN: //Boolean
	            cellValue = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case Cell.CELL_TYPE_FORMULA: //公式
	            cellValue = String.valueOf(cell.getCellFormula());
	            break;
	        case Cell.CELL_TYPE_BLANK: //空值 
	            cellValue = "";
	            break;
	        case Cell.CELL_TYPE_ERROR: //故障
	            cellValue = "非法字符";
	            break;
	        default:
	            cellValue = "未知类型";
	            break;
        }
		return cellValue;
	}
	
	public static String getList(List<Gather> list,String excelPath, JSONObject jsonObject, String merchantid,String balance){
	    try {
	    	Integer totalAmount = 0;
	        File excel = new File(excelPath);
	        if (excel.isFile() && excel.exists()) {   //判断文件是否存在

	            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
	            Workbook wb;
	            //根据文件后缀（xls/xlsx）进行判断
	            FileInputStream fis = new FileInputStream(excel);   //文件流对象
	            if ( "xls".equals(split[1])){
	                wb = new HSSFWorkbook(fis);
	            }else if ("xlsx".equals(split[1])){
	                wb = new XSSFWorkbook(fis);
	            }else {
	                return "文件类型错误!";
	            }

	            //开始解析
	            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

	            int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
	            int lastRowIndex = sheet.getLastRowNum();
	            //System.out.println("firstRowIndex: "+firstRowIndex);
	            //System.out.println("lastRowIndex: "+lastRowIndex);
	            
	           
	            Map<String, String> map = new HashMap<>();
	            NpayOrderService npayOrderService = SpringContextUtil.getBean(NpayOrderService.class);
	            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
	                //System.out.println("rIndex: " + rIndex);
	                Row row = sheet.getRow(rIndex);
	                if (row != null) {
	                    int firstCellIndex = row.getFirstCellNum();
	                    int lastCellIndex = row.getLastCellNum();
	                    int i = 0;
	                    Gather gather = new Gather();	                    
	                    
	                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
	                        Cell cell = row.getCell(cIndex);
	                        if (cell != null) {
	                            //System.out.println(cell.toString());
	                            String value = cell.toString().trim();
//	                            value  = str(value) ;
	                            //第一列全是数字
	                          /*  if (i == 0) {
	                            	if (isInteger(value)) {
	                            		//TODO 去数据库里面查询 这个订单是否重复
	                            		String string = map.get(value);
	                            		if (StringUtils.isNotBlank(string)) {
	                            			return "商品订单号重复,订单号为:"+value;
										}
	                            		NpayOrder orderId = npayOrderService.getMerorderid(value);
	                            		if (orderId != null) {
	                            			return "商品订单号重复,订单号为:"+value;
										}
	                            		order.setMerorderid(value);
	                            		i++;
	                            		map.put(value, "1");
	                            		continue;
	                            	}
	                            	return "商品订单号格式有误,请输入数字。错误行:"+firstRowIndex;
								}else 
									*/
									
									
								if(i == 0){
//									 
									if(value.contains(".")){
										value  = str(value) ;
									}
									
									if (isInteger(value)) {
										
//										单笔限额   500 - 50000  元
										if(Integer.parseInt(value) < Integer.parseInt("50000")){
											return "代付请求失败,金额最小为  500 元。错误行:"+rIndex;
										}
										
										
										if(Integer.parseInt(value) > Integer.parseInt("5000000")){
											return "代付请求失败,金额最大为 50000 元。错误行:"+rIndex;
										}
										
										
										int txnmon = Integer.parseInt(value) + 500;
//										NpayMerchantBalance2018 balance = npayMerchantBalanceService.getBanlaceById(merchantid);
										logger.info("代付金额为>>>>>>>"+txnmon);
										logger.info("余额为>>>>>>>"+balance);
										if (balance == "" || (Integer.parseInt(balance) < txnmon)) {
											return "余额不足!。错误行:"+rIndex;

										}
										
										int txnAmt = Integer.parseInt(value);
										gather.setGatherTxnAmt(value);
	                            		i++;
	                            		totalAmount += txnAmt;
	                            		continue;
	                            	}
	                            	return "交易金额有误,请输入数字。错误行:"+rIndex;
								}else if(i == 1){
									if (isInteger(value)) {
										gather.setGatherAccno(value);
	                            		i++;
	                            		continue;
	                            	}
	                            	return "银行卡号有误,请输入数字。错误行:"+rIndex;
								}else if(i == 2){
									
									
									if("对公".equals(value)){
										value = "00";
									}
									if("对私".equals(value)){
										value = "01";
									}
									
									
									if ("00".equals(value) || "01".equals(value)) {
										gather.setGatherPpflag(value);
										i++;
	                            		continue;
									}
									return "对公对私标志有误,错误行:"+rIndex;
								}else if(i == 3){
									gather.setGatherCustomerName(value);
									i++;
								}else if(i == 4){
									NpayTf56Bank bank = npayOrderService.getBank(value);
									if (bank == null) {
										return "暂未开通此银行,错误行:"+rIndex;
									}
									gather.setGatherBankId(bank.getBankId());
									i++;
								}else if(i == 5){
									if (isInteger(value)) {
										gather.setGatherPhoneNo(value);
										i++;
										continue;
									}
									return "电话号码有误,请输入数字。错误行:"+rIndex;
								}
	                        }
	                    }
	                    
	                    gather.setGatherMerchantId(merchantid);
	                    gather.setGatherMerOrderId(DateUtil.getOrderId(new Date()));
	            		gather.setGatherTime(OfTime.getLongTime());//创建时间
	            		gather.setGatherUpdateTime(OfTime.getLongTime());//审核时间
	            		gather.setGatherState("1");//状态
	            		gather.setGatherKey("shadmin");
	            		list.add(gather);
	                    
	                    
	                }
	            }
	        } else {
	            return "找不到指定的文件";
	        }
	        
	       jsonObject.put("total_count", list.size());
	       jsonObject.put("total_amount", totalAmount);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "上传失败！";
	    }
	    
	    return "";
	}
	
	
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}

	
	public static String str(String str) {  
		 //正则表达

		str = str.replaceAll("0+?$", "");//去掉后面无用的零

		str = str.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
		return str ;
	}
	
	 
	
	

	
	public static void main(String[] args) throws IOException {
		List<NpayOrder> list = new ArrayList<>();
		String excelPath = "D:\\demo\\newsway\\apache-tomcat-7.0.57\\pic_file\\sample.xlsx";
//		String str = POIUtil.getList(list,excelPath);
//		System.out.println(str);
	}
}
