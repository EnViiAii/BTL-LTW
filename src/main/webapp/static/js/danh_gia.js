const danhSachFormDanhGia = document.querySelectorAll(".form-danh-gia");

Array.from(danhSachFormDanhGia).forEach((formDanhGia) => {
  const danhGiaCuaToi = formDanhGia.querySelector(".danh-gia-cua-toi");
  const nutXacNhan = formDanhGia.querySelector(".hanh-dong .nut-danh-gia");
  let cacSao = Array.from(danhGiaCuaToi.querySelectorAll(".sao"));
  let saoDuocChon = cacSao.find((sao) => sao.classList.contains("chon"));

  cacSao.forEach((sao) => {
    sao.addEventListener("click", function (e) {
      // thay doi danh gia hien tai
      saoDuocChon?.classList.remove("chon");
      sao.classList.add("chon");
      saoDuocChon = sao;
      nutXacNhan.disabled = false;
    });

    sao.addEventListener("mouseover", function () {
      // gia dinh chon sao hien tai
      cacSao.forEach((s) => s.classList.remove("chon"));
      sao.classList.add("chon");
    });
  });

  danhGiaCuaToi.addEventListener("mouseout", function () {
    cacSao.forEach((s) => s.classList.remove("chon"));
    saoDuocChon?.classList.add("chon");
  });
});
