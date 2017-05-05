<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>详情</title>
        <meta name="description" content="#">
        <meta name="viewport" content="#">
        <link rel="stylesheet" href="<%=basePath%>/css/detial.css">
    </head>
    <style type="text/css">
        span {
            color: #09f;
        }
    </style>
</head>
<body>
<article role="main" class="top-level" style="margin-top: 50px;">
    <div class="right" style="float:none;margin: 0 auto;">
        <h2 class="top-space"
            style="width:300px; display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
            ${data.text}
        </h2>
        <ul>
            <li>
                ID:
                <span> ${data.id}</span>
            </li>
            <li>
                作者:
                <span> ${data.userInfo.name}</span>&nbsp;&nbsp;
                出生地:
                <span> ${data.userInfo.region}</span>&nbsp;&nbsp;
                性别:
                <span> ${data.userInfo.gender}</span>
            </li>
            <li>
                评论数:
                <span> ${data.commentsCount}</span>
            </li>
            <li>
                转发量:
                <span> ${data.repostsCount}</span>
            </li>
            <li>
                收藏数:
                <span> ${data.likesCount}</span>
            </li>
            <li>
                日期:
                <span> ${data.date}</span>
            </li>
            <li>
                博文:
                <span> ${data.text}</span>
            </li>
            <li>
                评论:
                <span>
                    <c:forEach var="comment" items="${data.comments}">
                        ${comment} <br/>
                    </c:forEach>
                </span>
            </li>
            <li>
                位置标记:
                <span> ${data.location}</span>
            </li>


        </ul>
    </div>
    <div class="cl"></div>
</article>
<footer class="top-level">
    MD5decoder.org © 2012-2017
    <a rel="nofollow" target="_blank" href="http://milejko.com/">milejko.com</a>
</footer>
<div id="policy-window">
    <span>X</span>
    <p>详情</p>
</div>
</body>
</html>
