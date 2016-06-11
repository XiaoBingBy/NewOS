package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NewsService;
import domain.News;

/**
 * 新闻管理界面Servlet
 * @author XiaoBingBy
 *
 */
public class newsManageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//执行查询Service
		NewsService newsService = new NewsService();
		List<News> allArticle = newsService.selectAllArticle();
		
		request.setAttribute("allArticle", allArticle);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}