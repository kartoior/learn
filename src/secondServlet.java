

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class secondServlet
 */
public class secondServlet extends HttpServlet {
     /**
      * 以GET方式访问页面时执行函数
      * 执行doGet前会执行getLastModified,如果浏览器发现
      * getLastModified返回到的数值与上次访问时返回的数值相同，
      * 则认为该文档没有更新，浏览器采用缓存而不执行doGet
      * 如果getLastModified返回-1，则认为是时刻更新的，
      * 总是执行该函数
      * /  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.log("执行 doGet 方法。。。"); //调用servlet自带的日志输出信息到控制台
		
		this.excute(request,response); //处理doGet
	}

	/**
	 * 以POST方式访问页面时执行该函数。执行前不会执行getLastModified
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.log("执行 doPost 方法..."); //调用servlet自带的日志输出信息到控制台
		
		this.excute(request,response); //处理doPost
	}
    /*
     * 返回该Servlet生成文档的更新时间。对Get方式访问有效
     * 返回的时间为相对于1970年1月1日08：00：00的毫秒数
     * 如果为-1则认为是实时更新。默认为-1
     * */
    @Override
    protected long getLastModified(HttpServletRequest req) {
    	// TODO Auto-generated method stub
    	this.log("执行 getLastModified方法...");
    	return -1;
    }
	private void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		// 设置 response 与  request 的编码方式
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		
		//访问该Servlet的URI
		String requestURI = request.getRequestURI();
		
		//访问 Servlet 的方式，GET或者POST
		String method = request.getMethod();
		
		//客户端提交的参数param值
		String param = request.getParameter("param");
		
		//设置文档类型为HTML类型
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("以"+ method + "方式访问该页面。取到的param参数为：" + param + "<br/>");
        out.println("<form action='" + requestURI + "' method='get'><input type='text' name='param' value='param string'><input type='submit' value='以GET方式查询页面' " + requestURI + "'></form>");
        out.println("<form action='" + requestURI + "' method='post'><input type='text' name='param' value='param string'><input type='submit' value='以POST方式查询页面' " + requestURI + "'></form>");
	    
        //由客户端浏览器读取该文档的更新时间
        out.println("<script>document.write('本页面最后更新时间：' + document.lastModified);</script>");
        
        out.println("</body>");
        out.println("</HTML>");
        out.flush();  //清空缓冲区的数据流
        out.close();
	}

}
