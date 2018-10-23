<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 10/23/2018
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sách Việt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
</head>
<body class="body">
<div class="header">
    <div class="header-logo">
        <img src="${pageContext.request.contextPath}/resources/img/sachviet.png" style="height: 80px"/>
    </div>
    <div class="header-content">
        <p style="color: white; flex: auto; font-size: 20px; width: 90%;">
            Tìm kiếm hơn 10,000 tựa sách từ những website uy tín nhất
        </p>
        <div class="searchBox">
            <input type="text" title="Search value"/>
            <span class="hidden">x</span>
            <button>Tìm kiếm</button>
        </div>
    </div>
</div>

<div class="breadcrumb vertical-center">
    <p><a>Sách Văn học</a> > <a>Tiểu thuyết</a> > <a>Trinh thám</a></p>
</div>

<div class="content">
    <div class="content-aside">
        <div class="vertical-center-children">
            <div class="content-aside-item">
                Sách mới phát hành
            </div>
            <div class="content-aside-item">
                Sách được xem nhiều
            </div>
            <div class="content-aside-item">
                Sách Văn Học
            </div>
            <div class="content-aside-item">
                Sách Kinh Tế
            </div>
            <div class="content-aside-item">
                Sách Thiếu Nhi
            </div>
        </div>

        <div class="search-filter">
            <div class="title">
                <h3>Lọc kết quả tìm kiếm</h3>
            </div>

            <div class="separator"></div>

            <div class="filter-group">
                <div class="filter-name">Giá</div>
                <div>Từ &nbsp;&nbsp;&nbsp; <input type="text" size="8"/></div>
                <div>Đến &nbsp; <input type="text" size="8"/></div>
            </div>

            <div class="separator"></div>

            <div class="filter-group">
                <div class="filter-name">Nhà xuất bản</div>
                <div class="scroll-list">
                    <div><input type="checkbox"/> Fujiko F. Fujio</div>
                    <div><input type="checkbox"/> Gosho Ayaoma</div>
                </div>
            </div>

            <div class="separator"></div>
        </div>
    </div>
    <div class="content-main">
        <div class="vertical-center" style="height: 50px; padding-left: 25px">
            <p>
                <span style="font-weight: bold">Kết quả tìm kiếm:</span>
                &nbsp; Có 100,000 kết quả cho sản phẩm này
                <span class="pagination">
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

        <div class="book-list" >
            <div class="book-item hidden" data-book-template>
                <img class="box-shadow"
                     src=""
                     alt="" title=""/>
                <div class="book-title"><a href="#" title="" target="_blank" rel="noopener noreferrer"></a></div>
                <div class="book-author">
                    <small></small>
                </div>
                <div class="book-old-price"></div>
                <div class="book-price"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/constant.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search/searchModel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search/searchView.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search/searchOctopus.js"></script>
<script>
    ready(() => {
        pageContext = "${pageContext.request.contextPath}";
        searchView.init();
        searchView.bindElements();
    });
</script>
</html>