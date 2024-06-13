<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/slide.css" />

<div class="khung-chua-slide">
  <c:forEach var="slide" items="${danhSachSlide}">
    <div class="slide">
      <div class="anh-nen">
        <img src="${slide.anh}" />
      </div>
      <div class="noi-dung truot-sang-trai">
        <h2 class="tieu-de">${slide.tieuDe}</h2>
        <p>${slide.moTa}</p>
        <div class="hanh-dong">
          <a class="nut kieu-1">${slide.nhanNutLienKet != null ? slide.nhanNutLienKet : "Khám phá ngay"}</a>
        </div>
      </div>
    </div>
  </c:forEach>
  <div class="thanh-trang-thai"></div>
</div>

<script src="<%=request.getContextPath()%>/static/js/slide.js"></script>