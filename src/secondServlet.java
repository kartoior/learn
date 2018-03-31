

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
      * ��GET��ʽ����ҳ��ʱִ�к���
      * ִ��doGetǰ��ִ��getLastModified,������������
      * getLastModified���ص�����ֵ���ϴη���ʱ���ص���ֵ��ͬ��
      * ����Ϊ���ĵ�û�и��£���������û������ִ��doGet
      * ���getLastModified����-1������Ϊ��ʱ�̸��µģ�
      * ����ִ�иú���
      * /  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.log("ִ�� doGet ����������"); //����servlet�Դ�����־�����Ϣ������̨
		
		this.excute(request,response); //����doGet
	}

	/**
	 * ��POST��ʽ����ҳ��ʱִ�иú�����ִ��ǰ����ִ��getLastModified
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.log("ִ�� doPost ����..."); //����servlet�Դ�����־�����Ϣ������̨
		
		this.excute(request,response); //����doPost
	}
    /*
     * ���ظ�Servlet�����ĵ��ĸ���ʱ�䡣��Get��ʽ������Ч
     * ���ص�ʱ��Ϊ�����1970��1��1��08��00��00�ĺ�����
     * ���Ϊ-1����Ϊ��ʵʱ���¡�Ĭ��Ϊ-1
     * */
    @Override
    protected long getLastModified(HttpServletRequest req) {
    	// TODO Auto-generated method stub
    	this.log("ִ�� getLastModified����...");
    	return -1;
    }
	private void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		// ���� response ��  request �ı��뷽ʽ
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		
		//���ʸ�Servlet��URI
		String requestURI = request.getRequestURI();
		
		//���� Servlet �ķ�ʽ��GET����POST
		String method = request.getMethod();
		
		//�ͻ����ύ�Ĳ���paramֵ
		String param = request.getParameter("param");
		
		//�����ĵ�����ΪHTML����
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("��"+ method + "��ʽ���ʸ�ҳ�档ȡ����param����Ϊ��" + param + "<br/>");
        out.println("<form action='" + requestURI + "' method='get'><input type='text' name='param' value='param string'><input type='submit' value='��GET��ʽ��ѯҳ��' " + requestURI + "'></form>");
        out.println("<form action='" + requestURI + "' method='post'><input type='text' name='param' value='param string'><input type='submit' value='��POST��ʽ��ѯҳ��' " + requestURI + "'></form>");
	    
        //�ɿͻ����������ȡ���ĵ��ĸ���ʱ��
        out.println("<script>document.write('��ҳ��������ʱ�䣺' + document.lastModified);</script>");
        
        out.println("</body>");
        out.println("</HTML>");
        out.flush();  //��ջ�������������
        out.close();
	}

}
