<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html class="w3c">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>搜索</title>
    <link rel="stylesheet" href="<%=basePath%>css/search.css" />
    <jsp:include page="../../static/static.jsp"/>
</head>
<body class="">
<div id="skin_bg" style="background-color:#fff"></div>
<div class="page-wrap skin-page-wrap" id="page1">
    <header id="header">
        <nav id="hd_nav">
            <ul class="skin-text skin-text-top">
                <li class="login">
                    <a href="#" id="user-login" data-linkid="login" style="padding-right: 10px;">登录</a>
                </li>
            </ul>
        </nav>
    </header>
    <div id="main">
        <section id="bd_logo" class="anime">
            <a href="javascript:void(0);" class="skin-logo pngfix" data-linkid="logo" style="cursor:default" title="" data-title="" data-style="cursor:default" data-href="javascript:;" target="_blank"></a>
        </section>
        <section id="bd_search">
            <form action="/search.do" method="post" >
                <div style="margin-right: 2px; float: left;height:36px;">
                    <select id="type" name="type" class="type_index">
                        <c:if test="${code!=null}">
                            <c:forEach items="${code}" var="code" >
                                <option value="${code.key}">${code.value}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <fieldset id="input-container">
                    <div class="ac_wrap" style="min-width: 528px; top: 43px; left: 0px; display: none;">
                        <div unselectable="on" class="ac_wrap_inner">
                            <div unselectable="on" class="ac_menu_ctn">
                                <div unselectable="on" id="ac_one"></div>
                                <ul unselectable="on" class="ac_menu"></ul>
                            </div>
                            <div unselectable="on" id="provided-by" class="ac_toolbar">由360搜索提供</div>
                        </div>
                    </div>

                    <div id="suggest-align" class="skin-search-input">
                        <input type="text" name="key" class="placeholder" id="input" suggestwidth="528px" autocomplete="off">
                    </div>
                    <input type="submit" id="search-button" class="skin-search-button" value="搜一下">
                </fieldset>
            </form>
        </section>
    </div>
    <footer id="footer" class="skin-text skin-text-foot">
        <p style="padding-bottom: 20px;">©2017 xxx.com&nbsp;&nbsp; xxxxx&nbsp;&nbsp;京ICP备xxxxxx号-19&nbsp;&nbsp;
            <a target="_blank" href="#">京公网安备xxxxxxxxxx号</a>
        </p>
    </footer>
</div>
<script>
    var So = {
        "comm": {
            "abv": "",
            "guid": "C31CEAD7237931E8084BB22E3620CFC6.1493723287384",
            "md": "",
            "pid": "home",
            "src": "",
            "fr": "none",
            "t": 1493724390011,
            "user": {
                "qid": "",
                "imageId": "",
                "showName": ""
            },
            "ssurl": "https:\/\/p.ssl.so.com\/p\/",
            "ssl": 1
        },
        "web": {
            "soid": {
                "sign": 3426782681,
                "ts": 1493724390,
                "rm": 0
            }
        }
    };
    So.comm.home = 'so';
    So.comm.ls = '0';
    So.web.skin = {
        "flag": "",
        "type": 0,
        "close": 0,
        "skinNew": null,
        "skinOld": null,
        "skinJs": "",
        "limitTime": "",
        "tip": ""
    };
    hcSwitch = {
        "hasHomecard": 0,
        "hasRecomm": 0,
        "hasBaodiCard": 0,
        "defaultCloseBrowser": 1,
        "showNewCard": 1,
        "hotnewsCardFlag": "11"
    };
    var weiboPersent = 30;
    var noBrandMarked = '';
</script>
</body>
</html>