package common;

//��ֹsqlע��
public class SQLTools {
	//ģ����ѯʱ��ֹע�룬��ʵ�����ַ����滻
	public static String transfer(String keyword){
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replace("\\\\", "\\\\\\\\")
							.replace("\\%", "\\\\%")
							.replace("\\_", "\\\\_");
		}
		
		return keyword;
	}
}
