package service;

import java.util.List;

import dao.UserDao;
import domain.User;

public class UserService {

	/**
	 * 注册业务方法
	 * @return 返回是否注册成功
	 */
	public boolean regist(User user) {
		
		UserDao dao = new UserDao();
		Integer result = dao.insertUser(user);
		
		if (result>=1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public List<User> selectAll() {
		return new UserDao().selectAll();
	}

	/**
	 * 删除单个用户
	 */
	public void deleteOne(String id) {
		UserDao dao = new UserDao();
		dao.deleteOne(id);
	}

	/**
	 * 查询一个用户
	 */
	public User selectUserOne(String userId) {
		UserDao dao = new UserDao();
		List<User> list = dao.selectOne(userId);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}


	/**
	 * 登录业务方法
	 * @param user
	 */
	public User login(User user) {
		UserDao dao = new UserDao();
		User usPs = dao.selectUsPs(user);
		return usPs;
	}


	/**
	 * 更新个人信息
	 * @param user
	 */
	public void updateinfo(User user) {
		UserDao dao = new UserDao();
		dao.updateinfo(user);
	}


	/**
	 * 管理用户信息更改
	 * @param user
	 */
	public void updateUserInfoServlet(User user) {
		UserDao dao = new UserDao();
		dao.updateUserInfoServlet(user);
	}
	
}