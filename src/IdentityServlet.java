

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
    //����ַ��ֵ䣬������0��o��1��I���ѱ��ϵ��ַ�
	public static final char[] CHARS = {'2','3','4','5','6','7','8','9',
			'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q',
			'R','S','T','U','V','W','X','Y','Z'};
    
	//�����
	public static Random random = new Random();
	//��ȡ��λ�����
	public static String getRandomString(){
		//�ַ�������
		StringBuffer buffer = new StringBuffer();
		//ѭ�����Σ�ÿ��ȡһ������ַ�
		for(int i = 0;i < 6; i++){
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
	
	//��ȡ�����ɫ
	public static Color getRandomColor(){
		return new Color(random.nextInt(255),random.nextInt(255),
				random.nextInt(255));	
	}
	
	//����ĳ��ɫ�ķ�ɫ
	public static Color getReverseColor(Color c){
		return new Color(255-c.getRed(),255-c.getGreen(),
				255-c.getBlue());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����������ͣ�����
		response.setContentType("image/jpeg");
		//����ַ���
		String randomString = getRandomString();
		//�ŵ�session��
		request.getSession(true).setAttribute("randomString", randomString);
		
		//ͼƬ���
		int width = 100;
		//ͼƬ�߶�
		int height = 30;
		
		//�����ɫ�����ڱ���ɫ
		Color color = getRandomColor();
		//��ɫ������ǰ��ɫ
		Color reverse = getReverseColor(color);
		
		//����һ����ɫͼƬ
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��ȡ��ͼ����
		Graphics2D g = bi.createGraphics();
		//��������
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		//���ñ�����ɫ
		g.setColor(color);
		//���Ʊ���
		g.fillRect(0, 0, width, height);
		//����ǰ����ɫ
		g.setColor(reverse);
		//��������ַ�
		g.drawString(randomString, 18, 20);
		//�����100�������㣬���������
		for(int i = 0,n = random.nextInt(100);i < n;i++){
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		ServletOutputStream out = response.getOutputStream();
		//ת��JPEG��ʽ
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		//��ͼƬ���б���
		encoder.encode(bi);
		out.flush();
	}


}
