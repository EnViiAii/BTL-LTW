<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/menu.css" />

<header>
  <div id="dau-trang">
    <div class="lien-he">
      <span>SĐT: </span>
      <strong>0123456789</strong>
    </div>
    <div class="tin-noi-bat">
      Deal CỰC SỐC giữa tháng 5 này
      <a class="lien-ket gach-chan thu-hep" href="#">
        <span>Xem ngay</span>
      </a>
    </div>
    <ul class="mang-xa-hoi">
      <li>
        <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
          <span>Facebook</span>
        </a>
      </li>
      <li>
        <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
          <span>Twitter</span>
        </a>
      </li>
      <li>
        <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
          <span>Instagram</span>
        </a>
      </li>
    </ul>
  </div>
  <div id="menu-chinh">
    <div id="an-hien-menu">
      <button class="nut chuc-nang"></button>
    </div>
    <div class="logo">
      <a href="<%=request.getContextPath()%>/">
        <img src="<%=request.getContextPath()%>/static/images/logo.svg" height="40"/>
      </a>
    </div>
    <nav id="thanh-dieu-huong" class="co-the-dong">
      <ul>
        <li class="muc hien-tai">
          <a class="lien-ket gach-chan mo-rong" href="<%=request.getContextPath()%>/">
            <span>Trang Chủ</span>
          </a>
        </li>
        <li class="muc co-menu-con">
          <a class="lien-ket gach-chan mo-rong">
            <span>Danh mục</span>
          </a>
          <div class="menu-con">
            <ul class="toi-gian">
              <c:forEach var="theLoai" items="${danhSachTheLoai}">
                <li>
                  <a class="lien-ket gach-chan mo-rong sang-phai" href="<%=request.getContextPath()%>/the-loai?maDanhMuc=${theLoai.maTheLoai}">
                    <span>
                      ${theLoai.tenTheLoai}
                    </span>
                  </a>
                </li>
              </c:forEach>
            </ul>
          </div>
        </li>
      </ul>
    </nav>
    <div class="menu-chuc-nang">
      <div class="chuc-nang an-tren-mobile">
        <button id="tim-kiem" class="nut chuc-nang"></button>
        <div class="menu-con"></div>
      </div>
      <div class="chuc-nang an-tren-mobile">
        <button id="tai-khoan" class="nut chuc-nang"></button>
        <div class="menu-con">
          <ul>
            <c:if test="${daDangNhap}">
              <li>
                <a class="lien-ket gach-chan mo-rong sang-phai" href="<%=request.getContextPath()%>/tai-khoan">
                  <span>Thông tin tài khoản</span>
                </a>
              </li>
              <li>
                <a class="lien-ket gach-chan mo-rong sang-phai" href="<%=request.getContextPath()%>/doi-mat-khau">
                  <span>Đổi mật khẩu</span>
                </a>
              </li>
              <li>
                <form method="POST" style="margin: 0" action="<%=request.getContextPath()%>/dang-xuat">
                  <button id="nut-dang-xuat" type="submit" class="chuc-nang gach-chan mo-rong sang-phai">
                    <span>Đăng xuất</span>
                  </button>
                </form>
              </li>
            </c:if>
            <c:if test="${!daDangNhap}">
              <li>
                <a class="lien-ket gach-chan mo-rong sang-phai" href="<%=request.getContextPath()%>/dang-nhap">
                  <span>Đăng nhập</span>
                </a>
              </li>
            </c:if>
          </ul>
        </div>
      </div>
      <div class="chuc-nang">
        <a href="<%=request.getContextPath()%>/gio-hang" id="nut-gio-hang" class="nut chuc-nang"></a>
        <div class="menu-con"></div>
      </div>
    </div>
  </div>
  <div id="menu-thay-the"></div>
</header>

<script src="<%=request.getContextPath()%>/static/js/menu.js"></script>