package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.NewsService;
import domain.News;
import domain.User;

public class newsPullServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		
		News news = new News();
		try {
			BeanUtils.populate(news, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		User user = (User) request.getSession().getAttribute("myUser");
		news.setUserid(user.getUsername());
		news.setPubtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		//操作数据库 添加
		NewsService newsService = new NewsService();
		newsService.AddArticle(news);
		response.sendRedirect(request.getContextPath()+"/admin/news-pull.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}