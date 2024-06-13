<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/menu_admin.css" />

<nav>
  <ul id="menu">
    <li class="muc logo">
      <div class="hien-thi">
        <a href="<%=request.getContextPath()%>/" class="lien-ket">
          <div class="anh">
            <img src="<%=request.getContextPath()%>/static/images/logo_xanh.svg" />
          </div>
          <div class="ten-muc" style="display: none">
            <span>Trang chính</span>
          </div>
        </a>
      </div>
    </li>
    <li class="muc ${"dashboard".equals(param.get("mucHienTai")) ? "hien-tai" : ""}">
      <div class="hien-thi">
        <a href="<%=request.getContextPath()%>/quan-ly/dashboard" class="lien-ket">
          <div class="anh">
            <img src="<%=request.getContextPath()%>/static/images/thong_ke.svg" />
          </div>
          <div class="ten-muc">
            <span>Dashboard</span>
          </div>
        </a>
      </div>
    </li>
    <li class="muc ${"don-hang".equals(param.get("mucHienTai")) ? "hien-tai" : ""}">
      <div class="hien-thi">
        <a href="<%=request.getContextPath()%>/quan-ly/don-hang" class="lien-ket ${param.get("mucHienTai").equals("don-hang") ? "hien-tai" : ""}">
          <div class="anh">
            <img src="<%=request.getContextPath()%>/static/images/gio-hang-trang.svg" />
          </div>
          <div class="ten-muc">
            <span>Đơn hàng</span>
          </div>
        </a>
      </div>
    </li>
    <li class="muc co-menu-con ${param.get("mucHienTai").equals("danh-muc") ? "hien-tai" : ""}">
      <div class="hien-thi">
        <div class="anh">
          <img src="<%=request.getContextPath()%>/static/images/san_pham.svg" />
        </div>
        <div class="ten-muc">
          <span>Danh mục</span>
        </div>
      </div>
      <ul class="menu-con">
        <li>
          <a href="<%=request.getContextPath()%>/quan-ly/san-pham/tao-moi" class="lien-ket gach-chan mo-rong sang-phai">
            <span>Tạo mới sản phẩm</span>
          </a>
        </li>
        <li>
          <a href="<%=request.getContextPath()%>/quan-ly/san-pham" class="lien-ket gach-chan mo-rong sang-phai">
            <span>Quản lý sản phẩm</span>
          </a>
        </li>
      </ul>
    </li>
    <li class="muc ${param.get("mucHienTai").equals("nguoi-dung") ? "hien-tai" : ""}">
      <div class="hien-thi">
        <a href="<%=request.getContextPath()%>/quan-ly/nguoi-dung" class="lien-ket">
          <div class="anh">
            <img src="<%=request.getContextPath()%>/static/images/khach_hang.svg" />
          </div>
          <div class="ten-muc">
            <span>Quản lý người dùng</span>
          </div>
        </a>
      </div>
    </li>
    <li class="muc">
      <div class="hien-thi">
        <form action="<%=request.getContextPath()%>/dang-xuat" method="post">
          <button id="nut-dang-xuat" type="submit">
            <div class="anh">
              <img src="<%=request.getContextPath()%>/static/images/dang_xuat.svg" />
            </div>
            <div class="ten-muc">
              <span>Đăng xuất</span>
            </div>
          </button>
        </form>
      </div>
    </li>
  </ul>
</nav>

<script src="<%=request.getContextPath()%>/static/js/menu_admin.js"></script>
