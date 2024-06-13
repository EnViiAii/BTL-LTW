<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/danh_gia.css">

<div class="danh-gia">
  <div class="diem-danh-gia">
    <jsp:include page="danh_gia_sao.jsp">
      <jsp:param name="saoDanhGia" value="${param.get('diemSoDanhGia')}"/>
    </jsp:include>
  </div>
  <div class="thong-tin-danh-gia">
    <span class="ten-nguoi-danh-gia"> ${param.get("tenNguoiDanhGia")} </span>
    <span> vào </span>
    <span class="thoi-gian-danh-gia"> ${param.get("thoiGian")} </span>
  </div>
  <p class="noi-dung">
    ${param.get("noiDung")}
  </p>
</div>