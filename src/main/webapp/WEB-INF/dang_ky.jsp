<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Title</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/dang_ky.css">
</head>
<body>
  <jsp:include page="components/header.jsp"/>

  <div id="khung-dang-ky">
    <h1>Đăng ký tài khoản</h1>
    <h2>Thông tin cá nhân</h2>
    <form id="form-dang-ky" class="tieu-chuan" method="POST">
      <div class="truong nua">
        <label>Họ đệm</label>
        <input type="text" name="hoDem" value="${hoDem}" placeholder="Họ đệm" autofocus />
      </div>
      <div class="truong nua bat-buoc" data-dieu-kien="batBuoc">
        <label>Tên</label>
        <input type="text" name="ten" value="${ten}" placeholder="Tên" required />
      </div>
      <div class="truong ${trungTenDangNhap ? "loi" : ""}" data-dieu-kien="tenDangNhap">
        <label>Tên đăng nhập</label>
        <input type="text" name="tenDangNhap" value="${tenDangNhap}" placeholder="Tên đăng nhập" required />
        <ul class="thong-bao-loi">
          <c:if test="${trungTenDangNhap != null}">
            <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
          </c:if>
        </ul>
      </div>
      <div class="truong nua bat-buoc" data-dieu-kien="batBuoc matKhau">
        <label>Mật khẩu</label>
        <input type="password" name="matKhau" placeholder="Mật khẩu" required />
      </div>
      <div class="truong nua bat-buoc" data-dieu-kien="batBuoc">
        <label>Nhập lại mật khẩu</label>
        <input type="password" name="nhapLaiMatKhau" placeholder="Nhập lại mật khẩu" required />
      </div>
      <div class="truong bat-buoc  ${trungEmail ? "loi" : ""}" data-dieu-kien="batBuoc email">
        <label>Email</label>
        <input type="email" name="email" value="${email}" placeholder="Địa chỉ email" required />
        <ul class="thong-bao-loi">
          <c:if test="${trungEmail != null}">
            <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
          </c:if>
        </ul>
      </div>
      <div class="truong bat-buoc ${trungSdt ? "loi" : ""}" data-dieu-kien="batBuoc soDienThoai">
        <label>Số điện thoại</label>
        <input type="tel" name="soDienThoai" value="${soDienThoai}" placeholder="Số điện thoại" required />
        <ul class="thong-bao-loi">
          <c:if test="${trungSdt != null}">
            <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
          </c:if>
        </ul>
      </div>
      <div class="hanh-dong">
        <button class="nut kieu-1" type="submit">Tạo tài khoản</button>
      </div>
    </form>
    <div class="phan-cach">
      <div class="vach"></div>
      <span class="noi-dung">Đã có tài khoản</span>
      <div class="vach"></div>
    </div>
    <a class="nut kieu-2 tao-tai-khoan" href="<%=request.getContextPath()%>/dang-nhap">Đăng nhập</a>
  </div>

  <jsp:include page="components/chan_trang.jsp"/>

  <script type="module" src="<%=request.getContextPath()%>/static/js/form.js"></script>
  <script type="module" src="<%=request.getContextPath()%>/static/js/dang_ky.js"></script>
</body>
</html>
