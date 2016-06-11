package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import domain.News;

/**
 * 操作数据库 News Dao
 * @author XiaoBingBy
 *
 */
public class NewsDao {

	/**
	 * 插入文章 Dao
	 * @param news
	 */
	public void installArticle(News news) {//QueryRunner
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "INSERT INTO news (id, title, content, userid, pubtime) VALUES (?, ?, ?, ?, ?)";
		
		try {
			queryRunner.update(sql, new Object[]{getID(), news.getTitle(), news.getContent(), news.getUserid(), news.getPubtime()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		id = id.substring(1, 8);
		id="AC"+id;
		return id;
	}

	/**
	 * 查询所有新闻列表
	 * @return 
	 */
	public List<News> selectAll() {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM news";
		try {
			return queryRunner
					.query(sql, new BeanListHandler<News>(News.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer deleteNews(String id) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql = "DELETE FROM news WHERE id = ?";
		
		try {
			int update = queryRunner.update(sql, id);
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询一条新闻
	 * @param newsId
	 * @return
	 */
	public News selectOneNews(String newsId) {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "SELECT * FROM news WHERE id = ?";
		try {
			News result = queryRunner.query(sql, new BeanHandler<News>(News.class), newsId);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}