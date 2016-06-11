package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NewsService;

public class deleteNews extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		//删除
		NewsService service = new NewsService();
		boolean result = service.deleteNews(id);
		if (result) {
			response.sendRedirect(request.getContextPath()+"/admin/news-manage.jsp");
		} else {
			System.out.println("删除失败");
		}
		System.out.println(id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}