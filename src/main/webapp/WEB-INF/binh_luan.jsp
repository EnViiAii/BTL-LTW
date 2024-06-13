<%@ page contentType="text/html;charset=UTF-8" %>

<div class="binh-luan" data-ma-binh-luan="${binhLuan.id}">
  <div class="thong-tin">
    <span class="ten"> ${binhLuan.nguoi_binh_luan.tenHienThi} </span>
    vào
    <span class="thoi-gian"> ${binhLuan.ngay_binh_luan} </span>
    đã nói
  </div>
  <div class="noi-dung">
    ${binhLuan.noi_dung_binh_luan}
  </div>
  <div class="hanh-dong">
    <button class="nut-phan-hoi">Phản hồi</button>
  </div>
</div>