const THOI_GIAN_CHUYEN_SLIDE = 3000;
const khungSlide = document.querySelector(".khung-chua-slide");

function khoiTao(khungSlide) {
  let soThuTuSlideHienTai = 0;
  const dsSlide = Array.from(khungSlide.querySelectorAll(".slide"));
  const dsNutChuyenSlide = [];
  let thanhTrangThai = khungSlide.querySelector(".thanh-trang-thai");

  dsSlide.forEach(_ => {
    let nutChuyenSlide = document.createElement("div");
    nutChuyenSlide.classList.add("trang-thai");
    thanhTrangThai.appendChild(nutChuyenSlide);
    dsNutChuyenSlide.push(nutChuyenSlide);
  });

  dsSlide[0].classList.add("hien-tai");
  dsNutChuyenSlide[0].classList.add("hien-tai");

  let chuTrinhChuyenSlide = setInterval(toiSlideTiep, THOI_GIAN_CHUYEN_SLIDE);

  dsNutChuyenSlide.forEach((nut, soThuTu) => {
    nut.addEventListener("click", function () {
      chuyenSlide(soThuTu);
      clearInterval(chuTrinhChuyenSlide);
      chuTrinhChuyenSlide = setInterval(toiSlideTiep, THOI_GIAN_CHUYEN_SLIDE);
    });
  });

  function chuyenSlide(soThuTu) {
    dsNutChuyenSlide.forEach((n) => n.classList.remove("hien-tai"));
    dsNutChuyenSlide[soThuTu].classList.add("hien-tai");
    dsSlide.forEach((s) => {
      s.classList.remove("truoc");
      if (s.classList.contains("hien-tai")) {
        s.classList.add("truoc");
        s.classList.remove("hien-tai");
      }
    });
    dsSlide[soThuTu].classList.add("hien-tai");
  }

  function toiSlideTiep() {
    soThuTuSlideHienTai = (soThuTuSlideHienTai + 1) % dsSlide.length;
    chuyenSlide(soThuTuSlideHienTai);
  }
}

khoiTao(khungSlide);
