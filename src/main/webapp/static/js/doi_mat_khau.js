import { kiemTraForm, themThongBaoLoi } from "./form.js";

const formDoiMatKhau = document.getElementById("form-doi-mat-khau");
formDoiMatKhau.addEventListener("submit", function (e) {
  let formData = new FormData(formDoiMatKhau);
  if (!kiemTraForm(formDoiMatKhau)) {
    e.preventDefault();
  } else if (formData.get("matKhauMoi") !== formData.get("nhapLaiMatKhauMoi")) {
    e.preventDefault();
    let truongNhapLaiMk = formDoiMatKhau.querySelector(".truong.nhap-lai-mat-khau");
    themThongBaoLoi(truongNhapLaiMk, "Nhập lại mật khẩu không trùng khớp");
  }
});
