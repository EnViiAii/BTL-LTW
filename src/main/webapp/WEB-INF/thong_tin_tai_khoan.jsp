<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
    <style>
      #khung-thong-tin-tai-khoan {
          margin: 1rem auto;
          max-width: 555px;
      }
    </style>
  </head>
  <body>
    <jsp:include page="components/header.jsp"/>

    <div id="khung-thong-tin-tai-khoan">
      <h1 style="text-align: center">Thông tin tài khoản</h1>
      <form id="form-thong-tin-tai-khoan" class="tieu-chuan" action="<%=request.getContextPath()%>/tai-khoan/sua-thong-tin" method="POST">
        <div class="truong bat-buoc" data-dieu-kien="batBuoc">
          <label>Tên</label>
          <input type="text" name="tenHienThi" value="${nguoiDung.tenHienThi}" required />
          <ul class="thong-bao-loi">
          </ul>
        </div>
        <div class="truong ${trungTenDangNhap ? "loi" : ""}" data-dieu-kien="tenDangNhap">
          <label>Tên đăng nhập</label>
          <input type="text" name="tenDangNhap" value="${nguoiDung.tenDangNhap}" />
          <ul class="thong-bao-loi">
            <c:if test="${trungTenDangNhap != null}">
              <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
            </c:if>
          </ul>
        </div>
        <div class="truong bat-buoc  ${trungEmail ? "loi" : ""}" data-dieu-kien="batBuoc email">
          <label>Email</label>
          <input type="email" name="email" value="${nguoiDung.email}" required />
          <ul class="thong-bao-loi">
            <c:if test="${trungEmail != null}">
              <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
            </c:if>
          </ul>
        </div>
        <div class="truong bat-buoc ${trungSdt ? "loi" : ""}" data-dieu-kien="batBuoc soDienThoai">
          <label>Số điện thoại</label>
          <input type="tel" name="soDienThoai" value="${nguoiDung.soDienThoai}" required />
          <ul class="thong-bao-loi">
            <c:if test="${trungSdt != null}">
              <li class="loi">Tên đăng nhập đã tồn tại trên hệ thống</li>
            </c:if>
          </ul>
        </div>
        <div class="hanh-dong">
          <button class="nut kieu-1" type="submit">Cập nhật</button>
        </div>
      </form>
    </div>

    <jsp:include page="components/chan_trang.jsp"/>

    <script type="module" src="<%=request.getContextPath()%>/static/js/form.js"></script>
    <script type="module" src="<%=request.getContextPath()%>/static/js/doi_thong_tin_tai_khoan.js"></script>
  </body>
</html>