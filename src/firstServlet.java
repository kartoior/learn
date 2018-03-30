

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
		response.setCharacterEncoding("UTF-8");//����response,request���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");//�����ĵ�����
		PrintWriter out = response.getWriter();//��ȡout����
		//������ͻ��������
		out.print("<!DOCTYPE HTML PUBLIC\"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.print("<HTML>");
		out.print("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.print("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.print("<BODY>");
		String requestURI = request.getRequestURI(); //��ȡ��URI·��
		out.print("<form action='" + requestURI + "' method='post'>");
		out.print("������������֣�<input type='text' name='name' />");
        out.print("<input type='submit' />");
		out.print("</form>");
		out.print("");
		
		String name = request.getParameter("name");//��ȡ����������ύ��name����
		
		if(name != null && name.trim().length() > 0){
			//���name��Ϊ���ҳ��ȴ���0
			out.print("����, <b>" + name + "</b>. ��ӭ���� Java Web ���硣");	
		}
		out.print(" </BODY>");
		out.print("</HTML>");
		out.flush();
		out.close();//�ر�out
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
