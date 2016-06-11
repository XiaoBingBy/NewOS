package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

/**
 * 删除单个用户
 * @author XiaoBingBy
 *
 */
public class deleteUserOne extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = (String) request.getParameter("id");
		UserService userService = new UserService();
		userService.deleteOne(id);
		response.sendRedirect(request.getContextPath()+"/admin/user-manage.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

}