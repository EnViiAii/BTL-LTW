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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_admin.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bang.css">
    <style>
        td:not(:nth-child(4)) {
            text-align: center;
        }
        .thanh-cong {
            color: var(--xanh-la);
        }
        .that-bai {
            color: var(--loi);
        }
        .chi-tiet {
            max-width: 30%;
        }
    </style>
</head>
<body>
<jsp:include page="components/menu_admin.jsp">
    <jsp:param name="mucHienTai" value="nguoi-dung"/>
</jsp:include>

<main>
    <h1 class="tieu-de-trang">
        Quản lý lịch sử hoạt động
    </h1>
    <div class="bang-admin">
        <div class="tuong-tac-bang">
            <form class="tieu-chuan loc-du-lieu">
                <input type="hidden" name="maNguoiDung" value="${param.get("maNguoiDung")}"/>
                <div class="bo-loc"></div>
                <div class="phan-trang">
                    Trang
                    <input type="number" name="trang" value="${param.get("trang") != null ? param.get("trang") : 1}" min="0" />
                    của
                    <span class="tong-so-trang">${danhSachHanhDong.tongSoTrang}</span>
                </div>
            </form>
        </div>
        <table class="bang">
            <thead>
            <tr>
                <th>Thời gian</th>
                <th>Loại hành động</th>
                <th>Địa chỉ IP</th>
                <th>Chi tiết</th>
                <th>Trạng thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hanhDong" items="${danhSachHanhDong.duLieu}">
                <tr>
                    <td>
                        <fmt:formatDate value="${hanhDong.thoiGian}" pattern="HH:mm:ss dd-MM-yyyy" />
                    </td>
                    <td>${hanhDong.loaiHanhDong}</td>
                    <td>${hanhDong.diaChiIP}</td>
                    <td class="chi-tiet">${hanhDong.chiTiet}</td>
                    <td>
                        <c:choose>
                            <c:when test="${hanhDong.thanhCong}">
                                <span class="thanh-cong">Thành công</span>
                            </c:when>
                            <c:when test="${!hanhDong.thanhCong}">
                                <span class="that-bai">Thất bại</span>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>
