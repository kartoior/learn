

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class firstServlet
 */
public class firstServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");//设置response,request编码方式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");//设置文档类型
		PrintWriter out = response.getWriter();//获取out对象
		//输出到客户端浏览器
		out.print("<!DOCTYPE HTML PUBLIC\"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.print("<HTML>");
		out.print("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.print("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.print("<BODY>");
		String requestURI = request.getRequestURI(); //获取到URI路径
		out.print("<form action='" + requestURI + "' method='post'>");
		out.print("请输入你的名字：<input type='text' name='name' />");
        out.print("<input type='submit' />");
		out.print("</form>");
		out.print("");
		
		String name = request.getParameter("name");//获取浏览器对象提交的name参数
		
		if(name != null && name.trim().length() > 0){
			//如果name不为空且长度大于0
			out.print("您好, <b>" + name + "</b>. 欢迎来到 Java Web 世界。");	
		}
		out.print(" </BODY>");
		out.print("</HTML>");
		out.flush();
		out.close();//关闭out
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
