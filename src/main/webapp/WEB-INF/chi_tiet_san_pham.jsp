<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chi_tiet_san_pham.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/binh_luan.css" />
     
  </head>
  <body>
    <jsp:include page="components/header.jsp"/>
    <main>
      <div id="chi-tiet-san-pham">
        <div class="anh-san-pham">
          <c:if test="${!sanPham.danhSachAnh.isEmpty()}">
            <div class="danh-sach-anh">
              <ul class="danh-sach toi-gian">
                <c:forEach var="anh" items="${sanPham.danhSachAnh}">
                  <li class="anh">
                    <img src="${anh.duongDan}" />
                  </li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
          <div id="anh-hien-thi" class="anh-hien-thi">
            <img src="${(sanPham.anhXemTruoc == null) ? "/public/anh-trong.jpg" : sanPham.anhXemTruoc}" />
          </div>
        </div>
        <div class="chi-tiet-san-pham">
          <div class="noi-dung-co-ban">
            <h1 class="ten-san-pham">${sanPham.tenSanPham}</h1>
            <div class="gia">
              <span class="tien-te">${sanPham.gia}</span>
            </div>
            <table class="cac-thuoc-tinh">
              <tr>
                <th>Chất liệu</th>
                <td>${sanPham.chatLieu.tenChatLieu}</td>
              </tr>
              <tr>
                <th>Thương hiệu</th>
                <td>${sanPham.thuongHieu.tenThuongHieu}</td>
              </tr>
              <c:if test="${sanPham.kichThuoc != null}">
                <tr>
                  <th>Kích thước</th>
                  <td>${sanPham.kichThuoc}</td>
                </tr>
              </c:if>
              <c:if test="${sanPham.trongLuong != null}">
                <tr>
                  <th>Trọng lượng</th>
                  <td>${sanPham.trongLuong} gr</td>
                </tr>
              </c:if>
            </table>
          </div>
          <div class="hanh-dong">
            <form class="them-vao-gio-hang" action="<%=request.getContextPath()%>/gio-hang/them" method="POST">
              <input type="hidden" name="maSanPham" value="${sanPham.maSanPham}" />
              <div class="thay-doi-so-luong">
                <button type="button" class="giam">-</button>
                <input class="so-luong" name="soLuong" value="1" />
                <button type="button" class="tang">+</button>
              </div>
              <button id="nut-them-vao-gio-hang" class="nut kieu-1" type="submit">
                <span>Thêm vào giỏ</span>
              </button>
            </form>
          </div>
          <div class="chi-tiet">
            <div class="phan mo-ta-san-pham">
              <h2 class="tieu-de">Mô tả sản phẩm</h2>
              <div class="noi-dung">
                <div>
                  ${sanPham.moTa}
                </div>
              </div>
            </div>
            <div class = "hanh-dong">
            	<a class="lien-ket gach-chan thu-hep" style="color: #48cab2" href = "<%=request.getContextPath()%>/xem-tat-ca-danh-gia?maSanPham=${sanPham.maSanPham}">
                <span>Xem tất cả đánh giá</span>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div id="phan-binh-luan">
          <h2>Bình luận</h2>
          <form method="POST" action="<%=request.getContextPath()%>/them-binh-luan">
              <input type="hidden" name = "maSanPham" value = "${sanPham.maSanPham}"/>
              <textarea name="noi_dung_binh_luan" placeholder="Nội dung bình luận"></textarea>
              <button class="nut kieu-1 nut-gui-bl" type="submit">Gửi</button>
          </form>
          <ul class="danh-sach-binh-luan">
              <c:forEach var="binhLuanGoc" items="${danhSachBinhLuan.keySet()}">
                  <li class="khung-binh-luan">
                      <c:set var="binhLuan" scope="request" value="${binhLuanGoc}" />
                      <jsp:include page="binh_luan.jsp" />
                      <ul class="danh-sach-binh-luan phan-hoi">
                          <c:forEach var="binhLuanPhanHoi" items="${danhSachBinhLuan.get(binhLuanGoc)}">
                              <li>
                                  <c:set var="binhLuan" scope="request" value="${binhLuanPhanHoi}" />
                                  <jsp:include page="binh_luan.jsp" />
                              </li>
                          </c:forEach>
                      </ul>
                  </li>
              </c:forEach>
          </ul>
      </div>

      <div id="san-pham-lien-quan">
        <%request.setAttribute("danhSachSanPham", request.getAttribute("sanPhamLienQuan"));%>
        <jsp:include page="components/danh_sach_san_pham.jsp"/>
      </div>
    </main>
    <jsp:include page="components/chan_trang.jsp"/>

    <script>
        window.maSanPham = <%=request.getParameter("maSanPham")%>;
        window.contextPath = "<%=request.getContextPath()%>";
    </script>
    <script src="<%=request.getContextPath()%>/static/js/chi_tiet_san_pham.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/binh_luan.js"></script>
  </body>
</html>