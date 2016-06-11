package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.UserService;
import domain.User;

/**
 * 注册
 * @author XiaoBingBy
 *
 */
public class registServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String verifycode = request.getParameter("verifycode");
		String code = (String)request.getSession().getAttribute("code");
		if (!verifycode.equals(code)) {
			request.setAttribute("mess", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//从表单获取数据封装到bean中
		User user = new User();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
			String[] values = request.getParameterValues("favourite");
			String favourite = Arrays.toString(values);
			favourite = favourite.substring(1, favourite.length()-1);
			user.setFavourite(favourite);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//执行注册业务逻辑代码
		UserService userService = new UserService();
		boolean regist = userService.regist(user);
		if (regist) {
			System.out.println("注册成功");
		} else {
			System.out.println("注册失败");
		}
		
		System.out.println(user);
	}

}