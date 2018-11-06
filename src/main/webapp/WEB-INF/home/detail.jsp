<%--
  Created by IntelliJ IDEA.
  User: NAMBACH
  Date: 11/5/2018
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${compareGroup.title} | Sách Việt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detail.css">
</head>
<body class="body">
<div class="header">
    <div class="header-logo">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/img/sachviet.png" style="height: 80px"/>
        </a>
    </div>
    <div class="header-content">
        <p style="color: white; flex: auto; font-size: 20px; width: 90%;">
            Tìm kiếm hơn 10,000 tựa sách từ những website uy tín nhất
        </p>
    </div>
</div>

<div class="breadcrumb vertical-center">

</div>

<div class="compare-page" style="min-height: 100%;">
    <div class="compare-container">
        <div class="common-info">
            <div class="common-img box-shadow">
                <img src="${compareGroup.image}" alt="${compareGroup.title}" title="${compareGroup.title}"/>
            </div>
            <div class="common-title">
                <div>
                    <div class="title">${compareGroup.title}</div>
                    <div class="authors">${compareGroup.authors}</div>
                </div>
                <div class="price">Giá từ ${compareGroup.minPrice}</div>
            </div>
        </div>
        <div class="group-info">
            <div style="padding: 15px; font-size: 18px">
                So sánh giá
            </div>
            <div class="item box-shadow hidden" data-compare-template>
                <div class="item-internal-info">
                    <div class="item-img">
                        <img src="" alt="" title=""/>
                    </div>
                    <div class="item-title">
                        <div>
                            <div class="title"><a href="#" title="" target="_blank" rel="noopener noreferrer"></a></div>
                            <div class="authors"></div>
                        </div>
                        <div class="price"></div>
                    </div>
                </div>
                <div class="item-external-link">
                    <div>Nơi bán</div>
                    <a href="#" title="" target="_blank" rel="noopener noreferrer">
                        <img src="" />
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="right-container">
        <div class="title">
            Sách được xem nhiều nhất
        </div>
        <div class="item">
            <div class="img box-shadow">
                <img class="box-shadow" src="" alt="" title=""/>
            </div>
            <div class="info">
                <div class="title"><a href="#" title="" target="_blank" rel="noopener noreferrer"></a></div>
                <div class="authors"></div>
                <div class="price"></div>
            </div>
        </div>
    </div>


    <div style="margin-top: 50px; margin-bottom: 20px; font-size: 25px; font-weight: bold;">
        Có thể bạn muốn xem
    </div>
    <div class="bottom-container">
        <div class="suggest-list">
            <div class="book-item hidden" data-book-template>
                <img class="box-shadow box-shadow-hover"
                     src=""
                     alt="" title=""/>
                <div class="book-title"><a href="#" title="" target="_blank" rel="noopener noreferrer"></a></div>
                <div class="book-author">
                    <small></small>
                </div>
                <div class="book-price"></div>
                <div class="book-shops"></div>
            </div>
        </div>
    </div>
</div>
<div class="loader hidden"><div></div></div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/constant.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/detail/detailModel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/detail/detailView.js"></script>
<script>
    ready(() => {
        pageContext = "${pageContext.request.contextPath}";
        detailView.init("${compareId}", "${suggestId}");
        detailView.renderCompareGroup();
        // detailView.renderSuggestGroup();
    });
</script>
</html>
