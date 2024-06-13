const danhSachAnh = Array.from(document.querySelectorAll("#chi-tiet-san-pham .danh-sach-anh .anh"));
const anhHienThi = document.querySelector("#anh-hien-thi");
danhSachAnh.forEach((anh) => {
  anh.addEventListener("click", function () {
    anhHienThi.firstElementChild.src = anh.firstElementChild.src;
    danhSachAnh.forEach((a) => a.classList.remove("hien-tai"));
    anh.classList.add("hien-tai");
  });
});

function moRong() {
  let noiDung = this.querySelector(".noi-dung");
  noiDung.style.height = noiDung.scrollHeight + "px";
}

function thuHep() {
  let noiDung = this.querySelector(".noi-dung");
  noiDung.style.height = 0;
}

const cacPhanChiTietSanPham = Array.from(document.querySelectorAll("#chi-tiet-san-pham .chi-tiet .phan"));
cacPhanChiTietSanPham.forEach((phan) => {
  phan.querySelector(".noi-dung").style.height = 0;

  phan.querySelector(".tieu-de").addEventListener("click", function () {
    let phanDangMo = cacPhanChiTietSanPham.find((p) => p.classList.contains("dang-mo"));
    // thu hep phan dang mo rong
    if (phanDangMo) {
      phanDangMo.classList.remove("dang-mo");
      thuHep.call(phanDangMo);
    }
    // mo rong phan duoc nhan neu can
    if (phanDangMo !== phan) {
      phan.classList.add("dang-mo");
      moRong.call(phan);
    }
  });
});
