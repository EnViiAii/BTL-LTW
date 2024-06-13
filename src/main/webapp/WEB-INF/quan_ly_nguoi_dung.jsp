<%@ page import="cf.laptrinhweb.btl.helper.HoTroRequest" %>
<%@ page import="cf.laptrinhweb.btl.constant.QuyenNguoiDung" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%HoTroRequest.khongCachePage(response);%>

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
            <jsp:param name="mucHienTai" value="nguoi-dung"/>
        </jsp:include>

        <main>
            <h1 class="tieu-de-trang">
                Quản lý người dùng
            </h1>
            <div class="bang-admin">
                <div class="tuong-tac-bang">
                    <form class="tieu-chuan loc-du-lieu">
                        <div class="bo-loc">
                            <div class="loc-quyen">
                                <c:set var="quyenDaChon"
                                       value='<%= Arrays.asList(Objects.requireNonNullElse(request.getParameterValues("quyen"), new String[]{})) %>'/>
                                <c:forEach var="quyen" items="<%=QuyenNguoiDung.values()%>">
                                    <label>
                                        <input type="checkbox"
                                               name="quyen"
                                               value="${quyen.name()}"
                                               class="chon"
                                               ${quyenDaChon.contains(quyen.name()) ? "checked" : ""}
                                        />
                                        <span class="checkbox-tu-tao"></span>
                                        <span class="noi-dung-checkbox">
                                                ${quyen.nhan}
                                        </span>
                                    </label>
                                </c:forEach>
                            </div>
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
                        <th>Tên đăng nhập</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Quyền được cấp</th>
                        <th>Ngày tạo tài khoản</th>
                        <th>Đã khoá</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="nguoiDung" items="${danhSachNguoiDung}">
                        <tr>
                            <td>${nguoiDung.tenDangNhap}</td>
                            <td>${nguoiDung.email}</td>
                            <td>${nguoiDung.soDienThoai}</td>
                            <td>
                                <ul class="toi-gian">
                                    <c:forEach var="quyen" items="${nguoiDung.dsQuyen}">
                                        <li>${quyen.tenQuyen}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td>
                                <fmt:formatDate value="${nguoiDung.thoiGianTao}" pattern="dd-MM-yyyy" />
                            </td>
                            <td>
                                <label>
                                    <input type="checkbox" class="chon" ${nguoiDung.daKhoa ? 'checked' : ''} disabled />
                                    <div class="checkbox-tu-tao"></div>
                                </label>
                            </td>
                            <td class="hanh-dong">
                                <span class="chon-hanh-dong">Chọn</span>
                                <div class="danh-sach" style="height: 0">
                                    <form method="POST" action="<%=request.getContextPath()%>/tai-khoan/doi-trang-thai">
                                        <input type="hidden" name="maNguoiDung" value="${nguoiDung.maNguoiDung}" />
                                        <input type="hidden" name="khoa" value="${nguoiDung.daKhoa ? false : true}" />
                                        <button>
                                            ${nguoiDung.daKhoa ? "Mở khoá" : "Khoá"}
                                        </button>
                                    </form>
                                    <a href="<%=request.getContextPath()%>/quan-ly/nguoi-dung/lich-su?maNguoiDung=${nguoiDung.maNguoiDung}" target="_blank">
                                        <span>Nhật ký hoạt động</span>
                                    </a>
                                    <a href="<%=request.getContextPath()%>/quan-ly/nguoi-dung/phan-quyen?maNguoiDung=${nguoiDung.maNguoiDung}">
                                        <span>Phân quyền</span>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>

        <script>
            let nutChonHanhDong = document.querySelectorAll(".bang-admin .hanh-dong .chon-hanh-dong");
            Array.from(nutChonHanhDong).forEach(nut => {
                nut.addEventListener("click", function() {
                    nut.classList.toggle("da-click");
                    if (nut.classList.contains("da-click")) {
                        nut.nextElementSibling.style.height = nut.nextElementSibling.scrollHeight + "px";
                    } else {
                        nut.nextElementSibling.style.height = "0px";
                    }
                });
            })
        </script>
    </body>
</html>
