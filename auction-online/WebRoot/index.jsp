<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script  type="text/javascript" src="js/WebCalendar.js">



</script>
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right"><a href="#" onclick="zhuxiao()" title="注销">注销</a></div>
  </div>
  <div class="forms">
  
  <form  id="form_query"    action="${pageContext.request.contextPath}/queryAuctions"  method="post">
  
    <input  id="page" name="pageNum"  type="hidden"  value="1"/>
    <label for="name">名称</label>
    <input name="auctionname" value="${condition.auctionname}" type="text" class="nwinput" id="name"/>
    <label for="names">描述</label>
    <input name="auctiondesc"  value="${condition.auctiondesc}" type="text" id="names" class="nwinput"/>
    <label for="time">开始时间</label>
    <input name="auctionstarttime" 
     value="<fmt:formatDate value="${condition.auctionstarttime}"  pattern="yyyy-MM-dd"/>"
     type="text" id="time" class="nwinput"  readonly="readonly"  onclick="selectDate(this,'yyyy-MM-dd')"/>  
    <label for="end-time">结束时间</label>
    <input name="auctionendtime" 
    value="<fmt:formatDate value="${condition.auctionendtime}"  pattern="yyyy-MM-dd"/>"
     type="text" id="end-time" class="nwinput"   readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd')"/>
     
     
    <label for="price">起拍价</label>
    <input name="auctionstartprice" value="${condition.auctionstartprice}"  type="text" id="price" class="nwinput" />
    <input type="submit" value="查询"  class="spbg buttombg f14  sale-buttom"/>
    
    </form>
    <c:if test="${sessionScope.user.userisadmin==1}">
      <input type="button"  value="发布" onclick="location='${pageContext.request.contextPath}/addAuction.jsp'" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
    
    <c:if test="${sessionScope.user.userisadmin==0}">
      <input type="button"  value="竞拍结果"  onclick="location='${pageContext.request.contextPath}/toAuctionnResult'" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
  
  </div>
  <div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li class="list-wd">描述</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno">操作</li>
      </ul>
      
      
      <c:forEach var="auction" items="${auctionList}" varStatus="state">
      
     
      <ul 
       <c:if test="${state.index%2==0}"> class="rows"</c:if>
         <c:if test="${state.index%2==1}"> class="rows even"</c:if>
      >
        <li>${auction.auctionname}</li>
        <li class="list-wd">${auction.auctiondesc}</li>
        <li>
        <fmt:formatDate value="${auction.auctionstarttime}" pattern="yyyy-MM-dd"/>
        </li>
        <li>
        <fmt:formatDate value="${auction.auctionendtime}" pattern="yyyy-MM-dd"/>
        </li>
        <li>${auction.auctionstartprice}</li>
        <li class="borderno red">
            <c:if test="${sessionScope.user.userisadmin==1}">
           <a href="#" title="竞拍" onclick="update(${auction.auctionid});">修改</a>|
          <a href="#" title="竞拍" onclick="abc(${auction.auctionid});">删除</a>
          </c:if>
          
          <c:if test="${sessionScope.user.userisadmin==0}">
          
          <a href="${pageContext.request.contextPath}/toDetail/${auction.auctionid}">竞拍</a>
          </c:if>
        </li>
      </ul>
      </c:forEach>
      
     <!--
      <div class="page">
      [当前第${page.pageNum}页，总共${page.pages}页，总共${page.total}条记录]
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=1">首页</a>
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.prePage}" title="">上一页</a>
        
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.nextPage}" title="">下一页</a>
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.pages}" title="">尾页</a> 
      </div>
        -->
       <div class="page">
      [当前第${page.pageNum}页，总共${page.pages}页，总共${page.total}条记录]
        <a href="javascript:jumpPage(1)">首页</a>
        <a href="javascript:jumpPage(${page.prePage})">上一页</a>
        
        <a href="javascript:jumpPage(${page.nextPage})">下一页</a>
        <a href="javascript:jumpPage(${page.pages})">尾页</a> 
      </div>
  </div>
  <script>
  
  
  function zhuxiao(){
	  if(confirm("确定要退出吗?")){
		  window.location.href="${pageContext.request.contextPath}/doLogout"
		  return true;
	  }else{
		  
		  return false;
		  
	  }
	  
  }
 //${pageContext.request.contextPath}/doLogout
  function jumpPage(pageNo){
	
	 
	  document.getElementById("page").value=pageNo;
	  document.getElementById("form_query").submit();
	  
	  
	  //document.getElementById("page").value=pageNo;
		//手动提交查询表单(包含了查询的条件)
		//document.getElementById("form_query").submit();
  }

   function abc(auctionid){
	   
	   if(confirm("你真的确认要删除吗？")){ 
		  
		   location="${pageContext.request.contextPath}/removeAuction/"+auctionid;
		   return true;
		 }
		 else{
			 return false;
			 }
			 
	   };
	   function update(auctionid){
		   if(confirm("你真的确认要修改吗？请确认")){
			  
			   location="${pageContext.request.contextPath}/toUpdate/"+auctionid;
			 
			   return true;
			   }
			   else{
				   return false;
				   }
		   }
  </script>
<!-- main end-->
</div>
</body>
</html>
