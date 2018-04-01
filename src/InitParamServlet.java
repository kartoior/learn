

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet
 */
public class InitParamServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>请登录查看 Notice 文件</TITLE></HEAD>");
		out.println("<style>body, td, div {font-size:12px; }</style>");
		
		out.println("<form action='" + request.getRequestURI() + "' method='post'>");
        out.println("账号：<input type='text' name='username' style='width:200px;'><br/><br/>");
        out.println("密码：<input type='password' name='password' style='width:200px;'><br/><br/>");
        out.println("<input type='submit' value=' 登录 '");
        out.println("</form>");
        
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Enumeration params = this.getInitParameterNames();
		
		while(params.hasMoreElements()){
			//参数名，即用户名
			String usernameParam = (String)params.nextElement();
		    //参数值，即密码
		    String passnameParam = getInitParameter(usernameParam);
		    
		    if(usernameParam.equalsIgnoreCase(username)
		    		&& passnameParam.equals(password)){
		    	//如果用户名和密码匹配则显示
		    	request.getRequestDispatcher("/WEB-INF/notice.html").forward(request, response);
		    	return;	
		    }
		    this.doGet(request, response);//username,password不匹配，显示登录页面
		}
	}

}
