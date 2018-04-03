

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yahoo.search.ImageSearchRequest;
import com.yahoo.search.ImageSearchResult;
import com.yahoo.search.ImageSearchResults;
import com.yahoo.search.SearchClient;

/**
 * Servlet implementation class SearchServlet
 */
@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//搜索关键字
		String word = request.getParameter("word");
		//搜索类型
		String type = request.getParameter("type");
		//是否允许成人内容 Checkbox 的特性，如果选中，则为 “true”,否则为 null
		String allowedAdult = request.getParameter("allowedAdult");
		//转化为boolean类型
		boolean adultOk = "true".equals(allowedAdult);
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>"+ word +"搜索结果</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<div style='float:left; height:40px; '><img src='http://us.i1.yimg.com/us.yimg.com/i/ww/beta/y3.gif'></div>");
		out.println("<form action='" + request.getRequestURI() + "' method='get'>");
		out.println("<div style='height:40px;'>");
		out.println("<input type='radio' name='type' value='web'>" + (type.equals("web")?"checked":"") + "网页");
		out.println("<input type='radio' name='type' value='news'>" + (type.equals("news")?"checked":"") + "新闻");
		out.println("<input type='radio' name='type' value='image'>" + (type.equals("image")?"checked":"") + "图像");
		out.println("<input type='radio' name='type' value='video'>" + (type.equals("video")?"checked":"") + "视频");
		out.println(" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		out.println("<input type='checkbox' name='allowedAdult' value='true' " + (adultOk?"checked":"") + ">允许成人内容<br/>");
		out.println("<input type='text' name='word' value='" + word + "' style='width:300px;'><input type='submit' value='用雅虎搜索' style='width:100px; '>");
		out.println("</div>");
		out.println("</form>");
		//调用雅虎API
		SearchClient client = new SearchClient("javasdktest");
		try {
			//如果是搜图片
			if("image".equals(type)){
				ImageSearchRequest searchRequest = new ImageSearchRequest(URLEncoder.encode(word,"UTF-8"));
				//是否显示成人内容
				searchRequest.setAdultOk(adultOk);
				//查询记录数
				searchRequest.setResults(20);
				//从第0条记录开始显示
				searchRequest.setStart(BigInteger.valueOf(0));
				//记录开始时间
				double startTime = System.currentTimeMillis();
				//查询
				ImageSearchResults results = client.imageSearch(searchRequest);
				//记录结束时间
				double endTime = System.currentTimeMillis();
				out.println("<div align=right style='width:100%; background:#FFDDDD; height:25px; padding:2px; border-top:1px solid #FF9999'; margin-bottom:5px;>");
				out.println("总共" + results.getTotalResultsAvailable() + "条数据，总用时" + (endTime - startTime)/1000 + "秒。");
				out.println("</div>");
				//遍历所有的查询结果
				for(ImageSearchResult result : results.listResults()){
					out.println("<div class='imgDiv'>");
					out.println("<div align='center'><a href='" + result.getClickUrl() + "' target=_blank><img width=160 height=120 src=\"" + result.getThumbnail().getUrl() + "\" border='0'></a></div>");
					out.println("<div align='center'><a href=\"" + result.getRefererUrl() + "\" target=_blank>" + result.getTitle() + "</a></div>");
					out.println("<div align='center'>" + result.getWidth() + "x" + result.getHeight() + " " + result.getFileFormat() + "</div>");
					out.println("<div>" + (result.getSummary()==null? "" : result.getSummary()) + "</div>");
					out.println("</div>");					
				}
			}
			else if("web".equals(type)){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

	}

}
