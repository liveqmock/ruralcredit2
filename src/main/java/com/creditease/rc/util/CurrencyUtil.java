package com.creditease.rc.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: Description: 宜农贷2.0系统开发 Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司 2011-9-28
 * 
 * @author xiebingfeng
 * @version v1.0
 */
public class CurrencyUtil {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 
	 * @author 韩大年  
	 * @Description: 格式化金额 
	 * @param myDouble  
	 * @return 金额字符串
	 * @version v1.0
	 * 2013-1-21
	 */
	public static String format(double myDouble) {
		double myInt = Math.ceil(myDouble * 100) / 100;
		// 金额国际化的标准形式
		DecimalFormat format = new DecimalFormat("###,##0.0");
		return format.format(myInt);
	}
	/**
	 * 
	 * @param myDouble 
	 * @return String
	 */
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 格式化金额 
	 * @param myDouble  
	 * @return 金额字符串
	 * @version v1.0
	 * 2013-1-21
	 */
	public static String format2(double myDouble) {
		double myInt = Math.ceil(myDouble * 100) / 100;
		// 金额国际化的标准形式
		DecimalFormat format = new DecimalFormat("###,##0.00");
		return format.format(myInt);
	}
	
	/**
	 * 
	 *@author 韩大年
	 *@function： 将String金额转换成double金额
	 *@param moneyString 
	 *@return  
	 *2012-5-24
	 */
	public static Double parse(String moneyString){
		if (moneyString == null || "".equalsIgnoreCase(moneyString.trim())) {
			moneyString = "0.0";
		}
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.0");
		try {
			return decimalFormat.parse(moneyString).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0D;
	}

	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * 
	 * @param v2
	 *            加数
	 * 
	 * @return 两个参数的和
	 */

	public static double add(Double v1, Double v2) {
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("0");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}

		return b1.add(b2).doubleValue();

	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param list 
	* @return double
	 */
	public static double add(List<Double> list) {
		Double b1=0D;
		if(null!=list && list.size()>0){
			for(Double value:list){
				b1=add(b1,value);
			}
			
		}
		return b1;
		
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param values 
	* @return
	* double
	 */
	public static double add(Double ... values) {
		Double b1=0D;
		for(Double value:values){
			b1=add(b1,value);
		}
			
		return b1;
		
	}
	/**
	 * 
	 * @param v1  
	 * @param v2  
	 * @return  double
	 */
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 两个对象想加 
	 * @param v1 
	 * @param v2 
	 * @return 和
	 * @version v1.0
	 * 2013-1-21
	 */
	public static double addBigDecimal(Object v1, Object v2) {
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = (BigDecimal) v1;
		}
		if(v2==null){
			b2=new BigDecimal("0");
		}else{
			b2 = (BigDecimal) v2;
		}

		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * 
	 * @param v2
	 *            减数
	 * 
	 * @return 两个参数的差
	 */

	public static double sub(Double v1, Double v2) {

		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("0");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}

		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * 
	 * @param v2
	 *            乘数
	 * 
	 * @return 两个参数的积
	 */

	public static double mul(Double v1, Double v2) {

		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("0");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}

		return b1.multiply(b2).doubleValue();

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 
	 * 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @return 两个参数的商
	 */

	public static double div(Double v1, Double v2) {
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("1");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}
		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 
	 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * 
	 * @param v2
	 *            除数
	 * 
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * 
	 * @return 两个参数的商
	 */

	public static double div(Double v1, Double v2, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}

		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("1");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果
	 */

	public static double round(Double v, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}
		BigDecimal b =null;
		if(null==v){
			b= new BigDecimal("0");
		}else{
			b = new BigDecimal(Double.toString(v));
		}

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	/**
	 * 
	 *@author 韩大年
	 *@function： 两个Doub比较大小 
	 *@param v1 
	 *@param v2 
	 *@return  两个Doub比较大小
	 *2012-3-6
	 */
	public static int compareTo(Double v1,Double v2){
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(v1==null){
			b1=new BigDecimal("0");
		}else{
			b1 = new BigDecimal(Double.toString(v1));
		}
		if(v2==null){
			b2=new BigDecimal("0");
		}else{
			b2 = new BigDecimal(Double.toString(v2));
		}
		return b1.compareTo(b2);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description: main方法
	 * @param args 
	 * @version v1.0
	 * 2013-1-21
	 */
	public static void main(String[] args) {
		List<Double> list = new ArrayList<Double>();
		/*list.add(1D);
		list.add(2D);
		list.add(3D);
		list.add(4D);
		list.add(5D);
		list.add(6D);
		list.add(7D);
		list.add(8D);
		list.add(9D);
		Double result=add(list);
		System.out.println(result);*/
		Double result=add(1D,2D,3D,4D,5D,6D,7D,8D,9D,null);
		System.out.println(result);
		
	}

}
