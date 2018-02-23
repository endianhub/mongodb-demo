package com.xh.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.xh.mongodb.pagination.Pagination;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @QQ 1033542070
 * @date 2018年2月23日
 */
public class BaseDao<T> {

	// 每页显示五条
	protected static final int PAGE_SIZE = 10;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * <p>Title: 查询(查询所有，条件查询)</p>
	 * <p>Description: 当Query为空时查询所有的数据</p>
	 * 
	 * @author H.Yang
	 * @date 2018年2月23日
	 * 
	 * @param query
	 * @param t
	 * @return
	 */
	public List<?> findObj(Query query, T t) {
		return mongoTemplate.find(query, t.getClass());
	}

	/**
	 * <p>Title: 分页查询</p>
	 * <p>Description: 当Query为空时查询所有的数据</p>
	 * 
	 * @author H.Yang
	 * @date 2018年2月23日
	 * 
	 * @param query
	 * @param t
	 * @return
	 */
	public Pagination getPagination(Query query, int currentPage, T t) {
		// 获取总条数
		long totalCount = this.findCount(query, t);
		// 总页数
		int totalPage = (int) (totalCount / PAGE_SIZE);
		//
		int skip = (currentPage - 1) * PAGE_SIZE;

		Pagination<T> page = new Pagination<T>(currentPage, totalPage, (int) totalCount);
		query.skip(skip);// skip相当于从那条记录开始
		query.limit(PAGE_SIZE);// 从skip开始,取多少条记录

		List<T> datas = (List<T>) this.findObj(query, t);

		page.build(datas);// 获取数据

		return page;
	}

	/**
	* <p>Title: 统计数量</p>
	* <p>Description: 当Query为空时统计所有数据</p>
	* 
	* @author H.Yang
	* @date 2018年2月23日
	* 
	* @param query
	* @param t
	* @return
	*/
	public long findCount(Query query, T t) {
		return mongoTemplate.count(query, t.getClass());
	}

	/**
	* <p>Title: 增加</p>
	* <p>Description: </p>
	* 
	* @author H.Yang
	* @date 2018年2月23日
	* 
	* @param t
	*/
	public void save(T t) {
		mongoTemplate.save(t);
	}

	/**
	* <p>Title: 删除</p>
	* <p>Description: </p>
	* 
	* @author H.Yang
	* @date 2018年2月23日
	* 
	* @param query
	* @param t
	* @return
	*/
	public int delete(Query query, T t) {
		return mongoTemplate.remove(query, t.getClass()).getN();
	}

	/**
	 * 
	 * <p>Title: 更新，修改符合条件的所有</p>
	 * <p>Description: </p>
	 * 
	 * @author H.Yang
	 * @date 2018年2月23日
	 * 
	 * @param query
	 * @param update
	 * @param t
	 * @return
	 */
	public int update(Query query, Update update, T t) {
		return mongoTemplate.updateMulti(null, update, t.getClass()).getN();
	}

}
