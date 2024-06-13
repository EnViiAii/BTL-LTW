<%@ page import="cf.laptrinhweb.btl.helper.HoTroRequest" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Title</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bang.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/lich_su_don_hang.css">
    </head>
    <body>
    	  <jsp:include page="components/header.jsp"/>

        <main>
            <h1 class="tieu-de-trang">
                Lịch sử mua hàng
            </h1>
            <table class="bang">
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Trạng thái</th>
                        <th>Ngày tạo đơn</th>
                        <th>Tổng tiền</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="donhang" items="${danhSachDonHang}">
                    <tr>
                        <td class="danh-sach-san-pham">
                            <ul class="toi-gian">
                                <c:forEach var="sanpham" items="${donhang.danhSachSanPham}">
                                    <li>${sanpham.sanPham.tenSanPham} x ${sanpham.soLuong}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td class="can-giua">
                            ${donhang.tinhTrang}
                        </td>
                        <td class="can-giua">
                            <c:set var="dinhDangNgayThang" value='<%=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")%>' scope="page"/>
                                ${dinhDangNgayThang.format(donhang.ngayTaoDon)}
                        </td>
                        <td class="tien-te can-phai">${donhang.tongTien}</td>
                        <td class="can-giua">
                            <a class="lien-ket" style="color: #333" href="<%=request.getContextPath()%>/don-hang/chi-tiet?maDatHang=${donhang.maDatHang}">
                                Chi tiết
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>

        <jsp:include page="components/chan_trang.jsp"/>
    </body>
</html>
