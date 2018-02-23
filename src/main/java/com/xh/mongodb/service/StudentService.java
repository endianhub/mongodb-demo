package com.xh.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.xh.mongodb.dao.StudentDao;
import com.xh.mongodb.model.Student;
import com.xh.mongodb.pagination.Pagination;

@Service
public class StudentService {
	@Autowired
	private StudentDao stuDao;

	/**
	 * 查询所有
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param stu
	 * @return
	 */
	public List<Student> findAll(Student stu) {
		List<Student> list = stuDao.findObject(null, stu);
		return list;
	}

	/**
	 * 条件查询
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param stu
	 * @return
	 */
	public Student findByObj(Student stu) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(stu.getId()));
		List<Student> list = stuDao.findObject(query, stu);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param stu
	 */
	public void save(Student stu) {

		stuDao.save(stu);
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
	public int update(Student stu) {

		Query query = new Query()//
				.addCriteria(Criteria.where("id").is(stu.getId()));

		Update update = new Update();
		update.set("name", stu.getName());
		update.set("address", stu.getAddress());
		update.set("sex", stu.getSex());

		return stuDao.update(query, update, stu);
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
	public int delete(Student stu) {
		Query query = new Query()//
				.addCriteria(Criteria.where("id").is(stu.getId()));

		return stuDao.delete(query, stu);
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
	public int findCount(Student stu) {

		// Query query = new Query()//
		// .addCriteria(Criteria.where("id").is(stu.getId()));
		// return (int) stuDao.findCount(query, stu);
		return (int) stuDao.findCount(null, stu);
	}

	/**
	 * 分页查询
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 * @param query
	 * @param stu
	 * @return
	 */
	public Pagination<Student> getPagination(Query query, int currentPage, Student stu) {
		
		return stuDao.getPagination(query, currentPage, stu);
	}
}
