package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成验证码
 * @author XiaoBingBy
 *
 */
public class CodeServlet extends HttpServlet {

	private char mapTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//禁用缓存，每次访问此页面，都重新生成
        response.setHeader("Pragma","No-cache"); 
        response.setHeader("Cache-Control","no-cache"); 
        response.setDateHeader("Expires", 0); 
 
   
 
        //调用里面的方法，返回的是生成的验证码中的字符串
        String str = getEnsure(0,0,response.getOutputStream());
 
        //获得session，并把字符串保存在session中，为后面的对比做基础
        HttpSession session = request.getSession();
        session.setAttribute("code", str);
        System.out.println(str+"验证码");
	}

	/**
	 * 生成验证码,并返回随机生成的数字
	 * @param width
	 * @param height
	 * @param os
	 * @return 返回随机生成的数字 验证码
	 */
	public String getEnsure(int width, int height, OutputStream os) {
		if (width <= 0)
			width = 60;
		if (height <= 0)
			height = 20;

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xDCCCCC));
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);

		// 取随机产生的认证码
		String strEnsure = "";

		// 4代表4位验证码
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}

		// 将认证码显示到图象中
		g.setColor(Color.red);
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 14));

		// 画的具体坐标
		String str = strEnsure.substring(0, 1);
		g.drawString(str, 8, 14);
		str = strEnsure.substring(1, 2);
		g.drawString(str, 20, 15);
		str = strEnsure.substring(2, 3);
		g.drawString(str, 35, 18);
		str = strEnsure.substring(3, 4);
		g.drawString(str, 45, 15);

		// 释放图形上下文
		g.dispose();

		try {
			// 输出图象到页面
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			return "";
		}

		return strEnsure;
	}

}