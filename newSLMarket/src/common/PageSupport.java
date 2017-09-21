package common;

import java.util.ArrayList;
import java.util.List;

public class PageSupport {
	private Integer totalCount = 0;//总计路数
	private Integer pageCount;//总页数
	private Integer pageSize = 2;
	private Integer currentPage = 1;//当前页
	private Integer num = 3;//当前页之前和之后的页数
	private List items =  new ArrayList();//当前页列表
	
	//获取总记录数
	public Integer getTotalCount() {
		return totalCount;
	}
	
	//计算总页数
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
	
	//获取当前页之前或之后显示的页面个数
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
	
	//获取前一页
	public Integer getPrev(){
		return currentPage-1;
		
	}
	
	//获取后一页
	public Integer getNext(){
		return currentPage+1;
		
	}
	
	//获取后一页
	public Integer getLast(){
		return pageCount;
	}
	
	//判断是否有前一页
	public boolean getIsPrev(){
		if (currentPage>1) {
			return true;
		}
		return false;
	}
	
	//判断是否有后一页
	public boolean getIsNext(){
		if (currentPage<pageCount && pageCount!=null) {
			return true;
		}
		return false;
	}
	
	//当前页的前num条页
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
	
	
	//当前页的后num条页
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
