package common;

//防止sql注入
public class SQLTools {
	//模糊查询时防止注入，其实就是字符的替换
	public static String transfer(String keyword){
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replace("\\\\", "\\\\\\\\")
							.replace("\\%", "\\\\%")
							.replace("\\_", "\\\\_");
		}
		
		return keyword;
	}
}
