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
        <img src="<%=request.getContextPath()%>/static/images/404.svg" />
      </div>
      <div class="noi-dung">
        <p>Xin lỗi, trang bạn đang tìm kiếm không tồn tại</p>
        <div class="hanh-dong">
          <a href="<%=request.getContextPath()%>/" class="nut kieu-2">
            <span>Về lại trang chủ</span>
          </a>
        </div>
      </div>
    </main>
    <jsp:include page="WEB-INF/components/chan_trang.jsp"/>
  </body>
</html>