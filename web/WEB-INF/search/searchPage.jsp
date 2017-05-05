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

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>${searchCode.key}_搜索</title>
    <link rel="stylesheet" href="<%=basePath%>/css/searchPage.css"/>
    <jsp:include page="../../static/static.jsp"/>
    <script>
        $(function () {
            $(".search-del").click(function () {
                $("#keyword").val("");
            });
        });
    </script>
</head>
<body link="#1024ee" class=" so-w1330">
<div id="header" style="height: 108px;">
    <div class="inner" style="top: 0px; height: 80px; overflow: visible;">
        <div class="hd-rtools">
            <div class="menu">
                <div class="show-list user-group ">
                    <a class="title" href="javascript:;">登录</a>
                    <ul class="g-menu g-shadow user-list">
                        <li id="user-login">
                            <a href="#" data-log="login">登录</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="head">
            <form name="f2" class="form" action="/search.do" method="get">
                <div style="margin-right: 2px; float: left;height:36px;">
                    <select id="type" name="type" class="type_search">
                        <c:if test="${code!=null}">
                            <c:forEach items="${code}" var="code">
                                <option value="${code.key}" <c:if
                                        test="${searchCode.type eq code.key}"> selected</c:if> >${code.value}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <span id="suggest-align" class="round">
                    <a href="javascript:;" class="search-del" title="清空" style="display: block;"></a>
                    <input type="text" id="keyword" name="key" tabindex="1" class="input_key" value="${searchCode.key} "
                           maxlength="100" autocomplete="off">
    			</span>
                <input type="submit" tabindex="3" id="su" value="搜一下" class="s_btn">
            </form>
        </div>
    </div>
    <div id="resBar" style="display: none;">
        <div class=""></div>
    </div>
</div>
<div id="warper">
    <div id="container">
        <div id="main" style="width: auto;">
            <ul class="result">
                <c:if test="${pager.dataList !=null}">
                    <c:forEach items="${pager.dataList}" var="data">
                        <li class="res-list" data-lazyload="1">
                            <h3 class="res-title ">
                                <a href="/detial.do?id=${data.id}" rel="noopener"  target="_blank"
                                   style="width:250px; display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
                                    ${data.text}
                                </a>
                            </h3>

                                <ul class="detail">
                                    <li class="item">
                                        <label class="gray">作者：</label>
                                        <span>${data.userInfo.name}</span>
                                    </li>

                                    <li class="item">
                                        <label class="gray">评论量：</label>
                                        <span>${data.commentsCount}</span>
                                    </li>
                                    <li class="item">
                                        <label class="gray">发布地：</label>
                                        <span>${data.location}</span>
                                    </li>
                                    <li class="item">
                                        <label class="gray">时间：</label>
                                        <span>${data.date}</span>
                                    </li>
                                </ul>
                            <p class="res-desc">博文${data.text}</p>
                            <p class="res-linkinfo">
                                <cite>www.<b>xx</b>zhushou.cn</cite>-
                                <a href="#" target="_blank" class="m">快照</a>
                            </p>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>

    <div style="clear:both;"></div>
    <style>
        .result .res-list p.dead-link {
            background-image: none
        }

        .hd-rtools #pop_wenda {
            right: 120px !important
        }

        .hd-rtools #pop_wenda .ico-target {
            top: -15px !important
        }
    </style>
</div>

<div id="page">
    <c:if test="${pager.prev==true}">
        <a id="spre" href="/search.do?type=${searchCode.type}&key=${searchCode.key}&currentPage=${pager.currentPage-1}">上一页</a>
    </c:if>
    <c:forEach items="${pager.pageIndex}" var="index">
        <c:choose>
            <c:when test="${index == pager.currentPage}">
                <strong>${index}</strong>
            </c:when>
            <c:otherwise>
                <a href="/search.do?type=${searchCode.type}&key=${searchCode.key}&currentPage=${index}">${index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${pager.next==true}">
        <a id="snext"
           href="/search.do?type=${searchCode.type}&key=${searchCode.key}&currentPage=${pager.currentPage+1}">下一页</a>
    </c:if>
    <span class="nums" style="margin-left:20px">找到相关结果约${pager.totalRecord}个</span>
</div>
<div id="footer" align="center">
    <span>Copyright © xxx.CN  xx搜索服务</span>
</div>
</body>
</html>