

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
		out.println("<HEAD><TITLE>���¼�鿴 Notice �ļ�</TITLE></HEAD>");
		out.println("<style>body, td, div {font-size:12px; }</style>");
		
		out.println("<form action='" + request.getRequestURI() + "' method='post'>");
        out.println("�˺ţ�<input type='text' name='username' style='width:200px;'><br/><br/>");
        out.println("���룺<input type='password' name='password' style='width:200px;'><br/><br/>");
        out.println("<input type='submit' value=' ��¼ '");
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
			//�����������û���
			String usernameParam = (String)params.nextElement();
		    //����ֵ��������
		    String passnameParam = getInitParameter(usernameParam);
		    
		    if(usernameParam.equalsIgnoreCase(username)
		    		&& passnameParam.equals(password)){
		    	//����û���������ƥ������ʾ
		    	request.getRequestDispatcher("/WEB-INF/notice.html").forward(request, response);
		    	return;	
		    }
		    this.doGet(request, response);//username,password��ƥ�䣬��ʾ��¼ҳ��
		}
	}

}
