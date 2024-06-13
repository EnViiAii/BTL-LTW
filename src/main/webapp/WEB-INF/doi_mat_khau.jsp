<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/doi_mat_khau.css" />
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>

        <div id="khung-doi-mat-khau">
            <h1>Thay đổi mật khẩu</h1>
            <c:if test="${thongBao != null}">
                <div class="thong-bao loi">${thongBao}</div>
            </c:if>
            <form id="form-doi-mat-khau" class="tieu-chuan" method="POST">
                <div class="truong bat-buoc" data-dieu-kien="batBuoc">
                    <label for="matKhauCu">Mật khẩu cũ</label>
                    <input id="matKhauCu" type="password" name="matKhauCu" required autofocus/>
                </div>
                <div class="truong bat-buoc" data-dieu-kien="batBuoc matKhau">
                    <label for="matKhauMoi">Mật khẩu mới</label>
                    <input id="matKhauMoi" type="password" name="matKhauMoi" required />
                </div>
                <div class="truong bat-buoc nhap-lai-mat-khau" data-dieu-kien="batBuoc">
                    <label for="nhapLaiMatKhauMoi">Nhập lại mật khẩu mới</label>
                    <input id="nhapLaiMatKhauMoi" type="password" name="nhapLaiMatKhauMoi" required />
                </div>
                <div class="hanh-dong">
                    <button class="nut kieu-1" type="submit">Đổi mật khẩu</button>
                </div>
            </form>
        </div>

        <jsp:include page="components/chan_trang.jsp"/>
        <jsp:include page="components/thong_bao.jsp">
            <jsp:param name="tuDongDong" value="4000"/>
        </jsp:include>

        <script type="module" src="<%=request.getContextPath()%>/static/js/form.js"></script>
        <script type="module" src="<%=request.getContextPath()%>/static/js/doi_mat_khau.js"></script>
    </body>
</html>