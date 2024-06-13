<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/khung_tai_anh_len.css" />

<div class="tai-file-len tai-file-anh">
    <input type="file" id="${param.get("tenTruongAnh")}" name="${param.get("tenTruongAnh")}" accept="image/png, image/jpeg, image/webp, image/avif" multiple="multiple" />
    <div class="giao-dien">
        <div class="anh-do-hoa">
            <img src="<%=request.getContextPath()%>/static/images/anh_mau.svg" alt="Tải ảnh lên"/>
        </div>
        <p>Kéo thả ảnh vào đây, hoặc <label class="chon-file" for="${param.get("tenTruongAnh")}">Chọn từ máy</label></p>
        <p class="mo-ta">Định dạng hỗ trợ: JPEG, JPG, PNG, WEBP</p>
    </div>
    <div class="ket-qua">
        <c:forEach var="anh" items="${danhSachAnh}">
            <div class="anh">
                <img src="${anh.duongDan}">
            </div>
        </c:forEach>
    </div>
</div>

<script src="<%=request.getContextPath()%>/static/js/khung_tai_anh_len.js"></script>