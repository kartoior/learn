<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
   %>
<% 
   //获取用户本地信息
   Locale locale = request.getLocale();
   //获取用户所在地的时间
   Calendar calendar = Calendar.getInstance(locale);
   //获取小时 Calendar.HOUR是使用12小时制
   int hour = calendar.get(Calendar.HOUR_OF_DAY);
   String greeting = "";
   if(hour <= 6){
	   greeting = "凌晨好，您该睡觉了，良好的睡眠是美好一天的开始。";
   }else if(hour <= 9){
	   greeting = "早晨好，早餐应该注意营养。";
   }else if(hour <= 12){
	   greeting = "上午好，工作时注意保护眼睛。";
   }else if(hour <= 18){
	   greeting = "下午好，小心工作中打瞌睡。";
   }else if(hour <= 24){
	   greeting = "晚上好，放松一下自己，好好休息。睡觉不要太晚啊~~";
   }else {
	   
   }
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 定义页面缓存 -->
<meta http-equiv="pragma" content="no-cache">
<!-- 指定请求与响应机制 -->
<meta http-equiv="cache-control" content="no-cache">
<!-- 设定网页的到期时间 -->
<meta http-equiv="expires" content="0">
<!-- 关键字,给搜索引擎用的 -->
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<!-- 对页面的描述性说明，做seo用 -->
<meta http-equiv="description" content="This is my page">
</head>
<body>
  <table>
   <tr>
     <td><%= greeting %></td>
   <tr>
  </table>
</body>
</html>