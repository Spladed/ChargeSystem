package chargeStaffDisplay;


public class toChinese {
	public static String getNumber(String str) {
		String[] number= {"��","Ҽ","��","��", "��","��","½","��","��","��"};
		String[] level= {"Ԫ","ʰ","��","Ǫ"};
		String result="";
		int digit=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='.')
				break;
			digit++;
		}
		int sign=1;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='.') {
				sign=0;
			}
			else {
				result+=number[Integer.parseInt(str.charAt(i)+"")];
				if(sign==1) {
					result+=level[digit-1];
					digit--;
				}
				if(sign==0) {
					result+="����";
				}
			}				
		}
		
		return result;
	}
}
