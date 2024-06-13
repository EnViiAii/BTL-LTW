<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/ds_danh_gia.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/DanhGiaStyle.css">
    </head>
    <body>
        <jsp:include page="components/header.jsp" />

        <main>
            <div class="thong-tin-san-pham">
                <div class="anh">
                    <img src="<%=request.getContextPath()%>/public/anh-trong.jpg">
                </div>
                <div class="thong-tin">
                    <h1 class="ten-san-pham">
                        <p> ${sanPham.tenSanPham}</p>
                    </h1>
                    <p class="mo-ta"> ${sanPham.moTa}</p>
                </div>
            </div>
            <h2 style="margin-top: 40px">Đánh giá của người dùng</h2>
            <ul class="toi-gian cac-danh-gia">
                <c:forEach var="danhGia" items="${tat_ca_danh_gia}">
                    <li class="muc-danh-gia">
                        <jsp:include page="components/danh_gia.jsp">
                            <jsp:param name="tenNguoiDanhGia" value="${danhGia.khachHangDanhGia.tenHienThi}"/>
                            <jsp:param name="thoiGian" value="${danhGia.ngay_danh_gia}"/>
                            <jsp:param name="diemSoDanhGia" value="${danhGia.soDiemDanhGia}"/>
                            <jsp:param name="noiDung" value="${danhGia.noi_dung_danh_gia}"/>
                        </jsp:include>
                    </li>
                </c:forEach>
            </ul>
        </main>

        <jsp:include page="components/chan_trang.jsp" />
    </body>
</html>