

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextParamServlet
 */
public class ContextParamServlet extends HttpServlet {
	
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
        out.println("<HEAD><TITLE>读取上下文参数</TITLE></HEAD>");
        out.println("<link rel='stylesheet' type='text/css' href='../css/style.css'>");
        out.println("<BODY>");
        out.print("<div align=center><br/>");
        out.println("<fieldset style='width:90%'><legend>所有的上下文参数<legend><br/>");
        //获取上下文
        ServletContext servletContext = getServletConfig().getServletContext();
        //获取参数
        String uploadFolder = servletContext.getInitParameter("upload folder");
        String allowedFileType = servletContext.getInitParameter("allowed file type");
	    
	    out.println("<div class='line'>");
	    out.println("<div align='left' class='leftDiv'>上传文件夹</div>");
	    out.println("<div align='left' class='rightDiv'>" + uploadFolder + "</div>");
	    out.println("</div>");
	    
	    out.println("<div class='line'>");
	    out.println("<div align='left' class='leftDiv'>实际硬盘路径</div>");
	    out.println("<div align='left' class='rightDiv'>" + servletContext.getRealPath(uploadFolder) + "</div>");
	    out.println("</div>");
	    
	    out.println("<div class='line'>");
	    out.println("<div align='left' class='leftDiv'>允许上传的类型</div>");
	    out.println("<div align='left' class='rightDiv'>" + allowedFileType + "</div>");
	    out.println("</div>");
	    out.println("</fieldset></div>");
	    out.println("</BODY>");
	    out.println("</HTML>");
	    out.flush();
	    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
