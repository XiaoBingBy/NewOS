package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NewsService;
import domain.News;

/**
 * 查找一条信息Servlet
 * @author XiaoBingBy
 *
 */
public class selectNews extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String NewsId = (String) request.getSession().getAttribute("NewsId");
		
		NewsService service = new NewsService();
		News result = service.selectOneNews(NewsId);
		request.getSession().setAttribute("upNews", result);
		
		System.out.println(NewsId);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}