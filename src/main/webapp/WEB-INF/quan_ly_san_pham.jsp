<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "vi_VN"/>

<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bang.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/quan_ly_nguoi_dung.css">
  </head>
  <body>
    <jsp:include page="components/menu_admin.jsp">
      <jsp:param name="mucHienTai" value="danh-muc"/>
    </jsp:include>

    <main>
      <h1 class="tieu-de-trang">
        Quản lý sản phẩm
      </h1>
      <div class="bang-admin">
        <div class="tuong-tac-bang">
          <form class="tieu-chuan loc-du-lieu">
            <div class="bo-loc">
              <div class="truong tim-kiem">
                <input class="o-tim-kiem" name="tuKhoa" value="${param.get("tuKhoa")}" placeholder="Từ khoá" />
              </div>
              <button id="nut-loc" class="nut kieu-1">Tìm kiếm</button>
            </div>
            <div class="phan-trang">
              Trang
              <input type="number" name="trang" value="${param.get("trang") != null ? param.get("trang") : 1}" min="0" />
              của
              <span class="tong-so-trang">10</span>
            </div>
          </form>
        </div>
        <table class="bang">
          <thead>
            <tr>
              <th>Mã</th>
              <th>Tên sản phẩm</th>
              <th>Ảnh</th>
              <th>Số lượng</th>
              <th>Chất liệu</th>
              <th>Thương hiệu</th>
              <th>Giá</th>
              <th>Đã ẩn</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="sanPham" items="${danhSachSanPham}">
              <tr>
                <td class="can-giua">${sanPham.maSanPham}</td>
                <td>${sanPham.tenSanPham}</td>
                <td>
                  <div class="anh-xem-truoc">
                    <img src='${sanPham.anhXemTruoc != null ? sanPham.anhXemTruoc : "/public/anh-trong.jpg"}'>
                  </div>
                </td>
                <td class="can-giua">${sanPham.soLuong}</td>
                <td class="can-giua">${sanPham.chatLieu.tenChatLieu}</td>
                <td class="can-giua">${sanPham.thuongHieu.tenThuongHieu}</td>
                <td class="tien-te can-phai">
                  <fmt:formatNumber value = "${sanPham.gia}"
                                    type = "currency"/>
                </td>
                <td>
                  <label>
                    <input type="checkbox" name="daAn" class="chon" ${sanPham.daAn ? 'checked' : ''} disabled />
                    <span class="checkbox-tu-tao"></span>
                  </label>
                </td>
                <td class="can-giua">
                  <a class="lien-ket" href="<%=request.getContextPath()%>/quan-ly/san-pham/chi-tiet?maSanPham=${sanPham.maSanPham}">
                    <span>Chi tiết</span>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </body>
</html>