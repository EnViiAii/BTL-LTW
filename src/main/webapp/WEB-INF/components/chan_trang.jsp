<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chan_trang.css" />

<footer>
  <div id="chan-trang">
    <div class="phan dang-ky-nhan-thong-bao">
      <h2 class="tieu-de">Nhận thông tin từ chúng tôi</h2>
      <p class="mo-ta">
        Đăng ký ngay để nhận những tin tức mới nhất về các sản phẩm, khuyến mãi cùng nhiều ưu đãi khác
      </p>
      <form id="form-dang-ky-thong-bao" method="POST">
        <div class="truong">
          <input class="truong" type="email" name="email" placeholder="Địa chỉ email" />
        </div>
        <button class="nut" type="submit">Đăng ký</button>
      </form>
    </div>
    <div class="phan co-the-mo-rong thong-tin">
      <h2 class="tieu-de">Thông tin</h2>
      <ul class="noi-dung">
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Về chúng tôi</span>
          </a>
        </li>
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Phương thức thanh toán</span>
          </a>
        </li>
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Đóng góp ý kiến</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="phan co-the-mo-rong chinh-sach">
      <h2 class="tieu-de">Chính sách</h2>
      <ul class="noi-dung">
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Vận chuyển và hoàn trả</span>
          </a>
        </li>
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Quyền riêng tư</span>
          </a>
        </li>
        <li>
          <a class="lien-ket gach-chan mo-rong sang-phai" href="#">
            <span>Điều khoản sử dụng</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="phan co-the-mo-rong cua-hang">
      <h2 class="tieu-de">Cửa hàng</h2>
      <address class="noi-dung dia-chi">
        <div>Số 104 Ngõ 236 Phố Tân Mai, Hà Nội, Việt Nam</div>
        <div><span>Điện thoại:</span> <strong class="phone-number">1–234–5678901</strong></div>
        <div><strong class="thoi-gian-lam-viec">Thứ hai - Thứ bảy: 09:00 - 21:00</strong></div>
      </address>
    </div>
  </div>
  <div id="ban-quyen-trang">
    <span>Bản quyền &copy; 2023.</span>
    <span>Phát triển bởi </span>
    <a href="#" class="lien-ket gach-chan mo-rong nha-phat-trien">
      <span>Dũng</span>
    </a>
  </div>
</footer>
