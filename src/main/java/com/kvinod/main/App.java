package com.kvinod.main;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kvinod.dao.ShipperDao;
import com.kvinod.entity.Shipper;

public class App {

	String resource;
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	ShipperDao dao;
	
	public App() throws Exception {
		resource = "mybatis-config.xml";
		inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		dao = sqlSession.getMapper(ShipperDao.class);

	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		// app.test1();
		// app.test2();
		// app.test3();
		// app.test4();
		app.test5();
	}

	void test5() {
		dao.deleteShipper(5);
		System.out.println("Shipper data deleted successfully!");
	}

	void test4() {
		Shipper shipper = dao.selectShipper(5);
		shipper.setPhone("(666) 142-4784");
		dao.updateShipper(shipper);
		System.out.println("Shipper details updated successfully!");
	}

	void test3() {
		List<Shipper> list = dao.findAll();
		for(Shipper s: list) {
			System.out.println(s);
		}
	}

	void test2() {
		try (SqlSession session = sqlSessionFactory.openSession()) {

			Shipper shipper;
			shipper = new Shipper();
			shipper.setId(5);
			shipper.setName("Acme Transports");
			shipper.setPhone("(666) 142-4784");
			dao.insertShipper(shipper);
			System.out.println("New shipper added!");
		}
	}

	void test1() throws Exception {

		try (SqlSession session = sqlSessionFactory.openSession()) {
			Shipper shipper = dao.selectShipper(4);
			System.out.println(shipper);
		}
	}
}
