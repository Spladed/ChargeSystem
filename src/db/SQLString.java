package db;

public class SQLString {
	public static String construct(String[] content) {
		String str="";
		for(int i=0;i<content.length;i++) {
			if(i==0)
				str+=content[i];
			else
				str+=","+content[i];
		}
		return str;
	}
	
	public static String insertColumn(String[] content) {
		String str="(";
		for(int i=0;i<content.length;i++) {
			if(i==0)
				str+=content[i];
			else
				str+=","+content[i];
		}
		str+=")";
		return str;
	}
	
	public static String insertValues(String[] content) {
		String str="(";
		for(int i=0;i<content.length;i++) {
			if(i==0)
				str+="\""+content[i]+"\"";
			else
				str+=","+"\""+content[i]+"\"";
		}
		str+=")";
		return str;
	}
	
	public static void main(String[] args) {
		String[] column= {"staff_id","staff_name","birthday","gender","address","phone","department_id","job","pwd"};
		String[] content= {"007","Ð»°²âù","1999-06-02","F","´ºÌï»¨»¨Ó×ÖÉÔ°","123333","003","M","xieanyi"};
		String[] ss= {"*"};
		System.out.println(insertColumn(column));
		System.out.println(insertValues(content));
		System.out.println(construct(ss));
		
	}
}
