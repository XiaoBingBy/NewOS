package service;

import java.util.List;

import dao.NewsDao;
import domain.News;

public class NewsService {

	/**
	 * 添加文章业务方法
	 * @param news
	 */
	public void AddArticle(News news) {
		NewsDao dao = new NewsDao();
		dao.installArticle(news);
	}

	/**
	 * 查询所有文章业务方法
	 * @return 
	 */
	public List<News> selectAllArticle() {
		NewsDao dao = new NewsDao();
		List<News> list = dao.selectAll();
		return list;
	}

	public boolean deleteNews(String id) {
		NewsDao dao = new NewsDao();
		Integer result = dao.deleteNews(id);
		if(result>=1 && result!=null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查找一条新闻
	 * @param newsId
	 * @return 
	 */
	public News selectOneNews(String newsId) {
		NewsDao dao = new NewsDao();
		News result = dao.selectOneNews(newsId);
		return result;
	}

}