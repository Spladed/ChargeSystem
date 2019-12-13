package chargeStaffDisplay;

import java.math.BigDecimal;

//保留一位小数
public class reservedDigits {
	public static double getDoubleNumber(double money) { 
		BigDecimal b=new BigDecimal(money);
		double result=b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}
}
