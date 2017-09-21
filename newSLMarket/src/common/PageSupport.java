package common;

import java.util.ArrayList;
import java.util.List;

public class PageSupport {
	private Integer totalCount = 0;//�ܼ�·��
	private Integer pageCount;//��ҳ��
	private Integer pageSize = 2;
	private Integer currentPage = 1;//��ǰҳ
	private Integer num = 3;//��ǰҳ֮ǰ��֮���ҳ��
	private List items =  new ArrayList();//��ǰҳ�б�
	
	//��ȡ�ܼ�¼��
	public Integer getTotalCount() {
		return totalCount;
	}
	
	//������ҳ��
	public void setTotalCount(Integer totalCount) {
		if (totalCount>0) {
			this.totalCount = totalCount;
			if (this.totalCount % this.pageSize == 0) {
				this.pageCount = this.totalCount / this.pageSize;
			}else if(this.totalCount % this.pageSize > 0){
				this.pageCount = this.totalCount / this.pageSize + 1;
			}else{
				this.pageCount =0;
			}
		}
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	//��ȡ��ǰҳ֮ǰ��֮����ʾ��ҳ�����
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
	//��ȡǰһҳ
	public Integer getPrev(){
		return currentPage-1;
		
	}
	
	//��ȡ��һҳ
	public Integer getNext(){
		return currentPage+1;
		
	}
	
	//��ȡ��һҳ
	public Integer getLast(){
		return pageCount;
	}
	
	//�ж��Ƿ���ǰһҳ
	public boolean getIsPrev(){
		if (currentPage>1) {
			return true;
		}
		return false;
	}
	
	//�ж��Ƿ��к�һҳ
	public boolean getIsNext(){
		if (currentPage<pageCount && pageCount!=null) {
			return true;
		}
		return false;
	}
	
	//��ǰҳ��ǰnum��ҳ
	public List<Integer> getPrevPages(){
		List<Integer> list = new ArrayList<Integer>();
		Integer _frontStart = 1;
		if (currentPage>num) {
			_frontStart = currentPage-num;
		}
		for (int i = _frontStart; i < currentPage; i++) {
			list.add(i);
		}
		return list;
	}
	
	
	//��ǰҳ�ĺ�num��ҳ
	public List<Integer> getNextPages(){
		List<Integer> list = new ArrayList<Integer>();
		Integer _endCount = num;
		if (pageCount !=null) {
			if (num<pageCount && (currentPage+num)<pageCount) {
				_endCount = currentPage+_endCount;
			}else{
				_endCount = pageCount;
			}
			for (int i = currentPage+1; i <= _endCount; i++) {
				list.add(i);
			}
		}
		
		
		
		
		return list;
	}
}
