package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.UserService;
import domain.User;

public class loginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String verifycode = request.getParameter("verifycode");
		String code = (String)request.getSession().getAttribute("code");
		if (!verifycode.equals(code)) {
			request.setAttribute("mess", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		UserService userService = new UserService();
		User login = userService.login(user);
		if (login!=null) {
			user = login;
			request.getSession().setAttribute("myUser", user);
			response.sendRedirect(request.getContextPath()+"/admin/admin.jsp");
		} else {
			request.setAttribute("mess", "帐号或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}