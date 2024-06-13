<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/dang_nhap.css">
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div id="khung-dang-nhap">
        <h1>Đã có tài khoản</h1>
        <h2>Đăng nhập</h2>
        <form id="form-dang-nhap" class="tieu-chuan" method="POST">
            <div class="note">
                Tài khoản admin thử nghiệm: dunglv - dunglv
            </div>
            <div class="truong bat-buoc">
                <label>Tên đăng nhập</label>
                <input
                        type="text"
                        name="tenDangNhap"
                        placeholder="Tên đăng nhập, email hoặc số điện thoại"
                        required
                        autofocus
                />
            </div>
            <div class="truong bat-buoc">
                <label>Mật khẩu</label>
                <input type="password" name="matKhau" placeholder="Mật khẩu" required />
            </div>
            <div class="hanh-dong">
                <button class="nut kieu-1" type="submit">Xác nhận</button>
            </div>
        </form>
        <a class="lien-ket gach-chan mo-rong quen-mat-khau" href="#">
            <span>Quên mật khẩu?</span>
        </a>
        <h1>Khách hàng mới</h1>
        <p class="mo-ta">Đăng ký để nhận được những ưu đãi mới nhất dành riêng cho các khách hàng mới</p>
        <a class="nut kieu-2 tao-tai-khoan" href="<%=request.getContextPath()%>/dang-ky">Tạo tài khoản</a>
    </div>
    <jsp:include page="components/thong_bao.jsp">
        <jsp:param name="tuDongThongBao" value="6000" />
    </jsp:include>
    <jsp:include page="components/chan_trang.jsp" />
</body>
</html>
