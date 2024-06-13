<%@ page import="cf.laptrinhweb.btl.constant.QuyenNguoiDung" %>
<%@ page import="cf.laptrinhweb.btl.helper.HoTroRequest" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/form_admin.css">
    <style>
        form.tieu-chuan {
            gap: 1.5rem;
        }
        .quyen > label {
            display: flex;
            gap: 0.5rem;
            text-transform: uppercase;
        }
        #danh-sach-quyen {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }
    </style>
</head>
<body>
    <jsp:include page="components/menu_admin.jsp">
        <jsp:param name="mucHienTai" value="nguoi-dung"/>
    </jsp:include>

    <main>
        <h1 class="tieu-de-trang">
            Phân quyền người dùng
        </h1>
        <form id="form-phan-quyen" class="tieu-chuan form-admin" method="POST">
            <input type="hidden" name="maNguoiDung" value="${nguoiDung.maNguoiDung}"/>
            <div class="truong">
                <label>Tên người dùng</label>
                <input type="text" value="${nguoiDung.tenHienThi}" disabled />
            </div>
            <div class="truong">
                <label>Quyền</label>
                <ul id="danh-sach-quyen" class="toi-gian">
                    <c:forEach var="quyen" items="<%=QuyenNguoiDung.values()%>">
                        <li class="quyen">
                            <label>
                                <div>
                                    <input type="checkbox" name="quyen" value="${quyen.name()}" ${nguoiDung.coQuyen(quyen) ? "checked" : ""}/>
                                    <span class="checkbox-tu-tao"></span>
                                </div>
                                <span>${quyen.name()}</span>
                            </label>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="hanh-dong">
                <button class="nut kieu-1" type="submit">Lưu</button>
            </div>
        </form>
    </main>
</body>
</html>
