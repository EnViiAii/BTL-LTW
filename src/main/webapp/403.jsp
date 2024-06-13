<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/chung.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/trang_loi.css" />
  </head>
  <body>
    <jsp:include page="WEB-INF/components/header.jsp"/>
    <main>
      <div class="anh">
        <img src="<%=request.getContextPath()%>/static/images/golang_1.svg" />
      </div>
      <c:choose>
        <c:when test="${daDangNhap}">
          <div class="noi-dung">
            <p>Trang không tồn tại hoặc bạn không có quyền truy cập tài nguyên này</p>
            <div class="hanh-dong">
              <a href="<%=request.getContextPath()%>/" class="nut kieu-2">
                <span>Về lại trang chủ</span>
              </a>
            </div>
          </div>
        </c:when>
        <c:when test="${!daDangNhap}">
          <div class="noi-dung">
            <p>Bạn cần đăng nhập trước để truy cập tài nguyên này</p>
            <div class="hanh-dong">
              <a href="<%=request.getContextPath()%>/dang-nhap" class="nut kieu-2">
                <span>Đăng nhập ngay</span>
              </a>
            </div>
          </div>
        </c:when>
      </c:choose>
    </main>
    <jsp:include page="WEB-INF/components/chan_trang.jsp"/>
  </body>
</html>