let cacDanhGiaSao = document.getElementsByClassName("danh-gia-sao");

function taoSao(tiLe) {
  let svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg.setAttribute("viewBox", "0 0 135 128");
  let noiDung = `
      <path d="M27.5256 128C26.9877 128 26.4499 127.731 25.9121 127.462C25.1054 126.924 24.5676 125.58 24.8365 124.504L36.6684 80.4034L1.17262 51.6303C0.0969896 51.0924 -0.171918 49.7479 0.0969895 48.6723C0.365897 47.5966 1.44153 46.7899 2.51716 46.7899L48.2314 44.3697L64.6348 1.61345C65.1726 0.806723 66.2483 0 67.3239 0C68.3995 0 69.4751 0.806723 69.7441 1.61345L86.1474 44.3697L131.862 46.7899C132.937 46.7899 134.013 47.5966 134.282 48.6723C134.551 49.7479 134.282 50.8235 133.475 51.6303L97.9794 80.4034L109.811 124.504C110.08 125.58 109.811 126.655 108.736 127.462C107.929 128 106.584 128.269 105.778 127.462L67.3239 102.723L28.8701 127.462C28.3323 128 28.0634 128 27.5256 128Z" fill="url(#${tiLe})"/>
      <defs>
        <linearGradient id="${tiLe}" x1="0" y1="0" x2="134.395" y2="-2.33819e-08" gradientUnits="userSpaceOnUse">
          <stop offset="${tiLe}" stop-color="#FED418"/>
          <stop offset="${tiLe}" stop-color="#EAEAEA"/>
        </linearGradient>
      </defs>
  `;
  svg.innerHTML = noiDung;
  return svg;
}

function taoSaoDanhGia(diemCuaSao) {
  let khungChua = document.createElement("label");
  khungChua.classList.add("sao");
  khungChua.innerHTML = `
    <input type="checkbox" name="danhGia" value="${diemCuaSao}"/>
  `;
  khungChua.appendChild(taoSao(1));
  return khungChua;
}

function taoSaoHienThi(tiLe) {
  let khungChua = document.createElement("div");
  khungChua.classList.add("sao");
  khungChua.appendChild(taoSao(tiLe));
  return khungChua;
}

Array.from(cacDanhGiaSao).forEach((danhGiaSao) => {
  let diemDanhGia = Number(danhGiaSao.getAttribute("data-diem"));
  let danhGiaCuaToi = danhGiaSao.classList.contains("danh-gia-cua-toi");
  let danhSachSaoDanhGia = [];
  let saoHienTai;

  // them 5 sao vao khung
  let saoDanhGiaConLai = diemDanhGia;
  for (let i = 1; i <= 5; i++) {
    let sao = danhGiaCuaToi ? taoSaoDanhGia(i) : taoSaoHienThi(saoDanhGiaConLai > 1 ? 1.0 : saoDanhGiaConLai);
    danhGiaSao.appendChild(sao);
    if (!danhGiaCuaToi) {
      saoDanhGiaConLai -= saoDanhGiaConLai > 1 ? 1 : saoDanhGiaConLai;
    } else {
      danhSachSaoDanhGia.push(sao);
    }
  }

  if (danhGiaCuaToi) {
    danhSachSaoDanhGia.forEach((sao) => {
      sao.addEventListener("mouseover", function () {
        danhSachSaoDanhGia.forEach((s) => s.classList.remove("chon"));
        sao.classList.add("chon");
        danhGiaSao.classList.remove("chua-danh-gia");
      });
      sao.addEventListener("click", function () {
        saoHienTai = sao;
      });
    });
    // reset lai ve sao dang chon
    danhGiaSao.addEventListener("mouseout", function () {
      danhSachSaoDanhGia.forEach((s) => s.classList.remove("chon"));
      saoHienTai?.classList.add("chon");
      if (!saoHienTai) {
        danhGiaSao.classList.add("chua-danh-gia");
      }
    });
  }
});
