let dsHopThoai = document.getElementsByClassName("hop-thoai");

function taoNutDong(hopThoai) {
  let nutDong = document.createElement("button");
  nutDong.classList.add("nut", "dong");
  nutDong.style.height = hopThoai.clientHeight / 6 + "px";
  nutDong.style.width = nutDong.style.height;
  nutDong.addEventListener("click", function () {
    hopThoai.remove();
  });
  hopThoai.appendChild(nutDong);
}

Array.from(dsHopThoai).forEach((hopThoai) => {
  taoNutDong(hopThoai);
  let thoiGianTuDong = hopThoai.getAttribute("data-tu-dong");
  if (thoiGianTuDong) {
    setTimeout((_) => hopThoai.remove(), Number(thoiGianTuDong));
  }
});
