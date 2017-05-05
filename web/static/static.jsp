<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script src="<%=basePath%>js/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="<%=basePath%>css/common.css"/>

