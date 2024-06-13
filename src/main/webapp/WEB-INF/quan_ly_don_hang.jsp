<%@ page import="cf.laptrinhweb.btl.helper.HoTroRequest" %>
<%@ page import="java.util.Date" %>
<%@ page import="cf.laptrinhweb.btl.constant.TrangThaiDon" %>
<%@ page import="cf.laptrinhweb.btl.constant.SapXepDon" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% HoTroRequest.khongCachePage(response); %>

<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Title</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_admin.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bang.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/quan_ly_don_hang.css">
    </head>
    <body>
        <jsp:include page="components/menu_admin.jsp">
            <jsp:param name="mucHienTai" value="don-hang"/>
        </jsp:include>

        <main>
            <h1 class="tieu-de-trang">
                Quản lý đơn hàng
            </h1>
            <div class="bang-admin">
                <div class="tuong-tac-bang">
                    <form class="tieu-chuan loc-du-lieu">
                        <div class="bo-loc">
                            <div class="nhieu-lua-chon">
                                <c:forEach var="trangThaiDon" items="<%=TrangThaiDon.values()%>">
                                    <label>
                                        <input type="checkbox"
                                               name="trangThai"
                                               value="${trangThaiDon.toString()}"
                                               class="chon"
                                               ${param.get('trangThai') == null ? "checked" : ""}
                                               <c:if test="${param.get('trangThai') != null}">
                                                   <c:set var="dsTrangThai"
                                                          scope="page"
                                                          value='<%=Arrays.asList(request.getParameterValues("trangThai"))%>' />
                                                   ${dsTrangThai.contains(trangThaiDon.name()) ? "checked" : ""}
                                               </c:if>
                                        />
                                        <span class="checkbox-tu-tao"></span>
                                        <span class="noi-dung-checkbox">
                                            ${trangThaiDon.tieuDe}
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
                            <select name="sapXep">
                                <c:forEach var="kieuSapXep" items="<%=SapXepDon.values()%>">
                                    <option value="${kieuSapXep.toString()}" ${kieuSapXep.toString().equals(param.get("sapXep")) ? "selected" : ""}>
                                        ${kieuSapXep.tieuDe}
                                    </option>
                                </c:forEach>
                            </select>
                            <button class="nut kieu-1">Lọc</button>
                        </div>
                        <div class="phan-trang">
                            Trang
                            <input type="number" name="trang" value="${param.get("trang") != null ? param.get("trang") : 1}" min="0" />
                        </div>
                    </form>
                </div>
                <table class="bang">
                    <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Tên người nhận</th>
                            <th>Địa chỉ giao</th>
                            <th>Ngày đặt hàng</th>
                            <th>Trạng thái</th>
                            <th>Tổng tiền</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="donHang" items="${dsDonHang}">
                            <tr>
                                <td class="can-giua">${donHang.maDatHang}</td>
                                <td>${donHang.tenNguoiNhan}</td>
                                <td>${donHang.sdtNhan}</td>
                                <td class="can-giua">
                                    <c:set var="dinhDangNgayThang" value='<%=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")%>' scope="page"/>
                                    ${dinhDangNgayThang.format(donHang.ngayTaoDon)}
                                </td>
                                <td class="can-giua">${donHang.tinhTrang.tieuDe}</td>
                                <td class="tien-te can-phai">
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${donHang.tongTien}"/>
                                </td>
                                <td class="hanh-dong">
                                    <a class="lien-ket" href="<%=request.getContextPath()%>/quan-ly/don-hang/chi-tiet?maDatHang=${donHang.maDatHang}">
                                        <span>Xem chi tiết</span>
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
