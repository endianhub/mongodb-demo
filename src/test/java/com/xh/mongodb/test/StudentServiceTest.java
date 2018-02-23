package com.xh.mongodb.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.mongodb.model.Student;
import com.xh.mongodb.pagination.Pagination;
import com.xh.mongodb.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class StudentServiceTest {

	@Autowired
	private StudentService stuService;

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
	// @Test
	public void findAll() {
		Student stu = new Student();
		List<Student> list = stuService.findAll(stu);
		System.out.println(list.size());
		for (Student st : list) {
			System.out.println(st.toString());
		}
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
	// @Test
	public void save() {
		Student stu = new Student();
		stu.setName("张三");
		stu.setSex(1);
		stu.setAge(12);
		stu.setAddress("中国");

		stuService.save(stu);
		System.out.println(stu.getId());
	}

	/**
	 * 条件查询
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 */
	// @Test
	public void findByObj() {
		Student stu = new Student();
		stu.setId("5a8fd3ccf7028624bc8ed2f0");
		stu = stuService.findByObj(stu);
		System.out.println(stu.toString());
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
	// @Test
	public void update() {
		Student stu = new Student();
		stu.setId("5a8fd3ccf7028624bc8ed2f0");
		stu.setName("李四");
		stu.setAddress("美国");
		stu.setSex(2);

		int result = stuService.update(stu);
		System.out.println(result);
	}

	/**
	 * 统计
	 * 
	 * @author Hyang
	 * @date 2016年10月28日
	 * @explain
	 *
	 */
	// @Test
	public void findCount() {
		Student stu = new Student();
		int result = stuService.findCount(stu);
		System.out.println(result);
	}

	/**
	 * 添加多条数据
	 * 
	 * @author H.Yang
	 * @date 2018年2月23日
	 *
	 */
	// @Test
	public void saveMultiple() {
		for (int i = 0; i < 100; i++) {
			Student stu = new Student();
			stu.setName("张三_" + i);
			stu.setSex(1);
			stu.setAge(12);
			stu.setAddress("中国");

			stuService.save(stu);
			System.out.println(stu.getId());
		}
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
	@Test
	public void findPagination() {
		Student stu = new Student();
		Query query = new Query();
		Pagination<Student> listPage = stuService.getPagination(query, 10, stu);
		System.out.println("每页显示条数:" + listPage.getPageSize());
		System.out.println("当前页:" + listPage.getCurrentPage());
		System.out.println("查询到的总数据量:" + listPage.getTotalNumber());
		System.out.println("总页数:" + listPage.getTotalPage());
		
		List<Student> list = listPage.getList();
		for (Student s : list) {
			System.out.println(s.toString());
		}
	}
}
