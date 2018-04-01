

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class IdentityServlet
 */
public class IdentityServlet extends HttpServlet {
    //随机字符字典，不包括0、o、1、I等难辨认的字符
	public static final char[] CHARS = {'2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q',
			'R','S','T','U','V','W','X','Y','Z'};
    
	//随机数
	public static Random random = new Random();
	//获取六位随机数
	public static String getRandomString(){
		//字符串缓存
		StringBuffer buffer = new StringBuffer();
		//循环六次，每次取一个随机字符
		for(int i = 0;i < 6; i++){
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
	
	//获取随机颜色
	public static Color getRandomColor(){
		return new Color(random.nextInt(255),random.nextInt(255),
				random.nextInt(255));	
	}
	
	//返回某颜色的反色
	public static Color getReverseColor(Color c){
		return new Color(255-c.getRed(),255-c.getGreen(),
				255-c.getBlue());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置输出类型，必须
		response.setContentType("image/jpeg");
		//随机字符串
		String randomString = getRandomString();
		//放到session中
		request.getSession(true).setAttribute("randomString", randomString);
		
		//图片宽度
		int width = 100;
		//图片高度
		int height = 30;
		
		//随机颜色，用于背景色
		Color color = getRandomColor();
		//反色，用于前景色
		Color reverse = getReverseColor(color);
		
		//创建一个彩色图片
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取绘图对象
		Graphics2D g = bi.createGraphics();
		//设置字体
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		//设置背景颜色
		g.setColor(color);
		//绘制背景
		g.fillRect(0, 0, width, height);
		//设置前景颜色
		g.setColor(reverse);
		//绘制随机字符
		g.drawString(randomString, 18, 20);
		//画最多100个噪音点，随机噪音点
		for(int i = 0,n = random.nextInt(100);i < n;i++){
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		ServletOutputStream out = response.getOutputStream();
		//转成JPEG格式
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		//对图片进行编码
		encoder.encode(bi);
		out.flush();
	}


}
