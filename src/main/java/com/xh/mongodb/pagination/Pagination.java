package com.xh.mongodb.pagination;

import java.util.List;

/**
* <p>Title: </p>
* <p>Description: </p>
* 
* @author H.Yang
* @QQ 1033542070
* @date 2018年2月23日
*/
public class Pagination<T> {
	/** 每页显示条数 */
	private Integer pageSize = 10;

	/** 当前页 */
	private Integer currentPage = 1;

	/** 总页数 */
	private Integer totalPage = 1;

	/** 查询到的总数据量 */
	private Integer totalNumber = 0;

	/** 数据集 */
	private List list;

	public Pagination(Integer currentPage, Integer totalPage, Integer totalNumber) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.totalNumber = totalNumber;
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

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	/** 
	 * 处理查询后的结果数据 
	 *  
	 * @param items 
	 *            查询结果集 
	 * @param count 
	 *            总数 
	 */
	public void build(List<T> datas) {
		this.setList(datas);
		int count = this.getTotalNumber();
		int divisor = count / this.getPageSize();
		int remainder = count % this.getPageSize();
		this.setTotalPage(remainder == 0 ? divisor == 0 ? 1 : divisor : divisor + 1);
	}
}
