package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import domain.User;

/**
 * Dao 操作 User数据表
 * 
 * @author XiaoBingBy
 * 
 */
public class UserDao {

	/**
	 * 插入数据
	 */
	public Integer insertUser(User user) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Object param[] = { getID(), user.getUsername(), user.getPassword(),
					user.getSex(), user.getProfession(), user.getFavourite(),
					user.getNote(), user.getType() };
			int update = queryRunner.update(sql, param);
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询所有用户 Servlet
	 * 
	 * @return
	 */
	public List<User> selectAll() {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

		String sql = "SELECT * FROM user";
		try {
			return queryRunner
					.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 删除一个用户
	 * 
	 * @param id
	 */
	public void deleteOne(String id) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "DELETE FROM user WHERE id = ?";
		try {
			int update = queryRunner.update(sql, new Object[] { id });
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 生成ID
	 * 
	 * @return
	 */
	public String getID() {
		String id = UUID.randomUUID().toString();
		return id;
	}

	/**
	 * 查询一个用户
	 * 
	 * @param userId
	 */
	public List<User> selectOne(String userId) {

		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			List<User> query = queryRunner.query(sql,
					new BeanListHandler<User>(User.class),
					new Object[] { userId });
			return query;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询 用户名 密码
	 * 
	 * @param user
	 */
	public User selectUsPs(User user) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			User query = queryRunner.query(sql, new BeanHandler<User>(
					User.class),
					new Object[] { user.getUsername(), user.getPassword() });
			return query;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 数据库操作 更新个人用户数据
	 * @param user
	 */
	public void updateinfo(User user) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql = "UPDATE user SET username = ?, password = ?, sex = ?, profession = ?, favourite = ?, note = ?  WHERE id = ?;";
		
		try {
			queryRunner.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getSex(), user.getProfession(), user.getFavourite(), user.getNote(), user.getId()});
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 数据库操作 更新个人用户数据
	 * 管理用户
	 * @param user
	 */
	public void updateUserInfoServlet(User user) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql = "UPDATE user SET username = ?, password = ?, sex = ?, profession = ?, favourite = ?, note = ?, type = ?  WHERE id = ?;";
		
		try {
			queryRunner.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getSex(), user.getProfession(), user.getFavourite(), user.getNote(), user.getType(), user.getId()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}