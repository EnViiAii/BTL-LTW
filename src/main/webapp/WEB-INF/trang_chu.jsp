<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_chu.css" />
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <main>
        <c:set var="danhSachSlide" value="${danhSachTinTuc}" scope="request"/>
        <jsp:include page="components/slide.jsp"/>
        <jsp:include page="components/danh_sach_the_loai.jsp"/>
        <section>
            <h1 class="tieu-de">Sản phẩm nổi bật</h1>
            <p class="mo-ta">Khám phá ngay hôm nay</p>
            <jsp:include page="components/danh_sach_san_pham.jsp"/>
        </section>
        <section>
            <h1 class="tieu-de">Mới xuất hiện</h1>
            <p class="mo-ta">Đừng bỏ lỡ những sản phẩm mới nhất của chúng tôi</p>
            <jsp:include page="components/danh_sach_san_pham.jsp"/>
        </section>
    </main>
    <jsp:include page="components/chan_trang.jsp"/>
    <jsp:include page="components/thong_bao.jsp"/>
</body>
