package test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import dao.NewsDao;
import dao.UserDao;
import domain.User;

import service.UserService;
import static java.lang.Math.*;
public class test {

	@Test
	public void testUID() {
		System.out.println(UUID.randomUUID().toString());
	}

	@Test
	public void testUserAll() {
		UserService userService = new UserService();
		List<User> selectAll = userService.selectAll();
		for (User user : selectAll) {
			System.out.println(user.getId());
		}
	}
	
	@Test
	public void testSeletcOne() {
		/*UserDao dao = new UserDao();
		List<User> selectOne = dao.selectOne("5");
		for (User user : selectOne) {
			System.out.println(user.getId());
		}*/
		new UserService().selectUserOne("345");
	}
	
	@Test
	public void staticTest() {
		System.out.println(max(4, 5));
	}
	
	@Test
	public void varP() {
		sum(1,2,3,4,5,4,5,6,7,2);
	}
	
	public static void sum(int ... args) {
		int i = 0;
		
		/*for (i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}*/
		for (int arg : args) {
			System.out.println(arg);
		}
	}
	@Test
	public void TestLogin() {
		UserService userService = new UserService();
		User user = new User();
		user.setUsername("123");
		user.setPassword("321");
		User login = userService.login(user);
		System.out.println(login);
	}
	
	@Test
	public void Testid() {
		System.out.println(new NewsDao().getID());
		"".substring(3, 3);
	}
	
	@Test
	public void TestUpdateInfo() {
		UserService userService = new UserService();
		User user = new User();
		user.setId("5");
		user.setUsername("root");
		user.setPassword("root");
		user.setSex("男");
		user.setProfession("调教女仆");
		user.setFavourite("看片");
		user.setNote("哈哈");
		userService.updateinfo(user);
	}
}
