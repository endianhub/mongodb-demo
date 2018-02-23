package com.xh.mongodb.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.xh.mongodb.model.Student;
import com.xh.mongodb.pagination.Pagination;

@Repository
public class StudentDao extends BaseDao<Student> {

	private static Logger log = LoggerFactory.getLogger(StudentDao.class);

	/**
	 * 查询所有/条件查询
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param t
	 * @return
	 */
	public List<Student> findObject(Query query, Student stu) {

		return (List<Student>) super.findObj(query, stu);
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
	public Pagination<Student> getPagination(Query query, int currentPage, Student stu) {

		return super.getPagination(query, currentPage, stu);
	}

	/**
	 * 增加
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param t
	 */
	public void save(Student stu) {

		super.save(stu);
	}

	/**
	 * 更新，修改符合条件的所有
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param update
	 * @param t
	 */
	public int update(Query query, Update update, Student stu) {

		return super.update(query, update, stu);
	}

	/**
	 * 删除
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param t
	 */
	public int delete(Query query, Student stu) {

		return super.delete(query, stu);
	}

	/**
	 * 统计数量
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param t
	 * @return
	 */
	public long findCount(Query query, Student stu) {

		return super.findCount(query, stu);
	}
}
