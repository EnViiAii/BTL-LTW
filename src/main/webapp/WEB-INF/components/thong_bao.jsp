<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<c:if test="${thongBao != null}">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/hop_thoai.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/thong_bao.css" />

  <div id="thong-bao-trang"
       class="hop-thoai thong-bao loi"
       data-tu-dong="${param.get("tuDongThongBao")}">
    <div class="anh">
      <img src="${thongBao.anh}" />
    </div>
    <div class="noi-dung">
      <h2 class="tieu-de">${thongBao.tieuDe}</h2>
      <p class="chi-tiet">${thongBao.noiDung}</p>
    </div>
  </div>

  <script src="<%=request.getContextPath()%>/static/js/hop_thoai.js"></script>
  <script>
    window.onload = function () {
      document.getElementById("thong-bao-trang").style.display = "flex";
    };
  </script>
</c:if>
