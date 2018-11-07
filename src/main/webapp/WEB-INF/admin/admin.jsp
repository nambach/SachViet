<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 11/6/2018
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sách Việt | Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body class="body">
<div class="header" style="height: 100px;">
    <div class="header-logo">
        <p style="color: white; font-size: 40px; width: 90%;">
            Quản trị viên Sách Việt
        </p>
    </div>
    <div class="header-content" style="align-items: flex-end">
        <p style="color: white; font-size: 18px; margin-right: 10%">
            Xin chào, <b>Nam Bach</b>
        </p>
    </div>
</div>

<div class="breadcrumb vertical-center">
</div>

<div class="content">
    <div class="content-aside">
        <div class="vertical-center-children noselect-all">
            <div class="content-aside-item selected" data-option="book">
                Quản lý Sách
            </div>
            <div class="content-aside-item" data-option="category">
                Quản lý Danh mục
            </div>
            <div class="content-aside-item" data-option="crawl">
                Khai thác dữ liệu
            </div>
            <div class="content-aside-item" data-option="stat">
                Thống kê
            </div>
        </div>

        <div class="search-filter" >

            <div class="title">
                <h3>Tài khoản</h3>
            </div>

            <div class="separator"></div>

            <div class="filter-group">
                <div class="filter-name">Nam Bach</div>
                <div><a href="${pageContext.request.contextPath}/">Đăng xuất</a></div>
            </div>

            <div class="separator"></div>
        </div>
    </div>
    <div class="content-main" data-option="book">
        <div class="vertical-center" style="height: auto; padding-left: 25px">
            <p>
                Tìm kiếm &nbsp; <input type="text" class="input" name="txtSearchBook"/>
            </p>
            <p>
                <span style="font-weight: bold">Kết quả tìm kiếm:</span>
                &nbsp;<span id="resultMessage"> Có <span id="numberOfResults">0</span> kết quả cho sản phẩm này</span>
                <span class="pagination hidden">
                    <a>&#171;</a>
                    <a>1</a>
                    <a>2</a>
                    <a>3</a>
                    ...
                    <a>&#187;</a>
                    <a>Last</a>
                </span>
            </p>

        </div>
        <div class="book-list">
            <%--Data goes here--%>

        </div>
    </div>

    <div class="content-main hidden" data-option="category">
        <div class="horizontal-center" style="height: auto; padding: 60px 25px">
            CATE
        </div>
    </div>

    <div class="content-main hidden" data-option="crawl">
        <div class="horizontal-center" style="height: auto; padding: 60px 25px">
            <button class="button" id="btnCrawl">Cào dữ liệu</button>
            <button class="button" id="btnStopCrawl" disabled>Dừng</button>
            <div class="crawling-pls-wait hidden" style="width: 100%; text-align: center; margin-top: 10px;">
                Xin chờ, việc cào có thể tốn vài phút <p class="dot-loading"></p>
            </div>
            <div class="crawling-result hidden" style="width: 100%; text-align: center; margin-top: 10px">
                Hoàn tất <br/>
            </div>
        </div>
    </div>

    <div class="content-main hidden" data-option="stat">
        <div class="horizontal-center" style="height: auto; padding: 60px 25px">
            STAT
        </div>
    </div>
</div>
<div class="loader hidden"><div></div></div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/constant.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/admin/adminModel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/adminView.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/adminOctopus.js"></script>
<script>
    ready(() => {
        pageContext = "${pageContext.request.contextPath}";
        let domParser = new DOMParser();
        adminOctopus.xsl = domParser.parseFromString('${xsl}', 'text/xml');
        adminOctopus.init();
    });
</script>
</html>

