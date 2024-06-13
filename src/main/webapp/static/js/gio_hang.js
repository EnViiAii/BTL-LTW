const dsMatHang = document.querySelectorAll("#gio-hang .mat-hang");

function chinhSoLuong(matHang, luongThayDoi) {
  let oNhapSoLuong = matHang.querySelector(".so-luong .o-nhap-so-luong");
  let soLuongHienTai = Number(oNhapSoLuong.value);
  if (soLuongHienTai + luongThayDoi > 0) {
    oNhapSoLuong.value = soLuongHienTai + luongThayDoi;
  }
}

Array.from(dsMatHang).forEach((matHang) => {
  let nutTang = matHang.querySelector(".so-luong button.tang");
  let nutGiam = matHang.querySelector(".so-luong button.giam");
  nutTang.addEventListener("click", function () {
    chinhSoLuong(matHang, +1);
  });
  nutGiam.addEventListener("click", function () {
    chinhSoLuong(matHang, -1);
  });
});
