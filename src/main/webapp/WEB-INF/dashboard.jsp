<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bang.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/dashboard.css"/>
  </head>
  <body>
    <fmt:setLocale value = "vi_VN"/>
    <jsp:include page="components/menu_admin.jsp">
      <jsp:param name="mucHienTai" value="dashboard"/>
    </jsp:include>
    <main>
      <%
        int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
        int thangHienTai = Calendar.getInstance().get(Calendar.MONTH) + 1;
      %>
      <c:set var="thangHienTai" value="<%=thangHienTai%>"/>
      <c:set var="namHienTai" value="<%=namHienTai%>"/>
      <c:set var="namChon" value="${param.nam == null ? namHienTai : param.nam}"/>
      <c:set var="thangChon" value="${param.thang == null ? thangHienTai : param.thang}"/>
      <form method="get">
        <label for="nam">Chọn năm:</label>
        <select name="nam" id="nam">
          <c:forEach var="nam" begin="<%=namHienTai - 5%>" end="<%=namHienTai%>">
            <c:choose>
              <c:when test="${nam == namChon}">
                <option value="${nam}" selected>${nam}</option>
              </c:when>
              <c:otherwise>
                <option value="${nam}">${nam}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
        <label for="thang">Chọn tháng:</label>
        <select name="thang" id="thang">
          <c:forEach var="thang" begin="1" end="12">
            <c:choose>
              <c:when test="${thang == thangChon}">
                <option value="${thang}" selected>${thang}</option>
              </c:when>
              <c:otherwise>
                <option value="${thang}">${thang}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
        <button type="submit" class="nut kieu-1">Thống kê</button>
        <a style="display: inline-block" href="${pageContext.request.contextPath}/quan-ly/dashboard/xuat-bao-cao?thang=${thangChon}&nam=${namChon}" class="nut kieu-1">Xuất báo cáo</a>
      </form>
      <h1 style="text-align: center">Thống kê tháng ${param.thang} năm ${param.nam}</h1>
      <div id="cac-bieu-do">
        <% request.setAttribute("danhSachDuLieu", request.getAttribute("doanhThuTungNgay")); %>
        <div class="bieu-do-doanh-thu-30-ngay">
          <jsp:include page="components/bieu_do.jsp">
            <jsp:param name="loaiBieuDo" value="bar"/>
            <jsp:param name="idBieuDo" value="bieu-do-doanh-thu-30-ngay"/>
          </jsp:include>
        </div>
        <% request.setAttribute("danhSachDuLieu", request.getAttribute("soDonTungNgay")); %>
        <div class="bieu-do-so-don">
          <jsp:include page="components/bieu_do.jsp">
            <jsp:param name="loaiBieuDo" value="line"/>
            <jsp:param name="idBieuDo" value="bieu-do-so-don"/>
          </jsp:include>
        </div>
        <% request.setAttribute("danhSachDuLieu", request.getAttribute("tiLeHuyDon")); %>
        <div class="bieu-do-ti-le-don">
          <jsp:include page="components/bieu_do.jsp">
            <jsp:param name="loaiBieuDo" value="doughnut"/>
            <jsp:param name="idBieuDo" value="bieu-do-ti-le-don"/>
          </jsp:include>
        </div>
      </div>

      <div class="thong-ke">
        <div>
          <h2 class="tieu-de">Tổng doanh thu</h2>
          <div class="gia-tri tien-te">
            <fmt:formatNumber value = "${tongDoanhThu}"
                              type = "currency"/>
          </div>
        </div>
        <div>
          <h2 class="tieu-de">Giá trị trung bình đơn hàng</h2>
          <div class="gia-tri tien-te">
            <fmt:formatNumber value = "${trungBinhDon}"
                              type = "currency"/>
          </div>
        </div>
      </div>

      <hr/>

      <%-- THONG KE SAN PHAM --%>
      <div id="thong-ke-khac">
        <div class="phan-thong-ke">
          <h2 class="tieu-de-bang">Khách hàng mua nhiều nhất</h2>
          <div class="bang-admin">
            <table class="bang">
              <thead>
              <tr>
                <th>STT</th>
                <th>Tên đăng nhập</th>
                <th>Tên hiển thị</th>
                <th>Lượng mua (VNĐ)</th>
              </tr>
              </thead>
              <tbody>
            <c:forEach var="nguoiDung" items="${topMuaNhieu}">
              <tr>
                 <td> ${nguoiDung.stt}</td>
                <td>                
                    ${nguoiDung.tenDangNhap}
                </td>
                <td> ${nguoiDung.tenHienThi}</td>
                 <td> ${nguoiDung.tongTien}</td>
              </tr>
        </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        
        <div class="phan-thong-ke">
          <h2 class="tieu-de-bang">Sản phẩm bán chạy nhất</h2>
          <div class="bang-admin">
            <table class="bang">
              <thead>
              <tr>
                <th>STT</th>
                <th>Tên sản phẩm</th>
                <th>Số lượng đã bán</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="sanPham" items="${topBanChay}">
              <tr>
                <td> ${sanPham.stt}</td>
                <td>                
                    ${sanPham.tenSanPham}
                </td>
                <td> ${sanPham.soLuong}</td>
              </tr>
           </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        </div>
        
      <div id="thong-ke-khac">
        <div class="phan-thong-ke">
          <h2 class="tieu-de-bang">Thể loại bán chạy nhất </h2>
          <div class="bang-admin">
            <table class="bang">
              <thead>
              <tr>
                <th>STT</th>
                <th>Tên thể loại</th>
                <th>Số lượng đã bán </th>
              </tr>
              </thead>
              <tbody>
            <c:forEach var="theLoai" items="${theLoaiBanChay}">
              <tr>
                 <td> ${theLoai.stt}</td>
                <td> ${theLoai.tenTheLoai}</td>
                 <td> ${theLoai.soLuong}</td>
              </tr>
        </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        
        <div class="phan-thong-ke">
          <h2 class="tieu-de-bang">Thương hiệu bán chạy nhất</h2>
          <div class="bang-admin">
            <table class="bang">
              <thead>
              <tr>
                <th>STT</th>
                <th>Tên thương hiệu</th>
                <th>Số lượng đã bán</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="thuongHieu" items="${thuongHieuBanChay}">
              <tr>
                <td> ${thuongHieu.stt}</td>
                <td>                
                    ${thuongHieu.tenThuongHieu}
                </td>
                <td> ${thuongHieu.soLuong}</td>
              </tr>
           </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  </body>
</html>
