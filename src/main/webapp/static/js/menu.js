const nutMoMenu = document.querySelector("#an-hien-menu button");
const menuChinh = document.getElementById("menu-chinh");
const menuDieuHuong = document.getElementById("thanh-dieu-huong");
nutMoMenu.addEventListener("click", function () {
  menuDieuHuong.classList.add("hien");
});

const dsTheCoTheDong = document.getElementsByClassName("co-the-dong");
Array.from(dsTheCoTheDong).forEach((the) => {
  let nutDong = document.createElement("button");
  nutDong.classList.add("nut", "dong", "chuc-nang");
  nutDong.addEventListener("click", function () {
    the.classList.remove("hien");
  });
  the.appendChild(nutDong);
});

const moRongMenu = document.querySelectorAll("#thanh-dieu-huong .muc.co-menu-con > .lien-ket");
Array.from(moRongMenu).forEach((moRong) => {
  moRong.addEventListener("click", function () {
    let menuCon = this.nextElementSibling;
    if (menuCon.style.maxHeight) {
      menuCon.style.maxHeight = null;
    } else {
      menuCon.style.maxHeight = menuCon.scrollHeight + "px";
    }
  });
});

window.addEventListener("scroll", function (e) {
  if (window.scrollY > menuChinh.offsetHeight + menuChinh.offsetTop + 100) {
    menuChinh.classList.add("cuon-theo");
  } else {
    menuChinh.classList.remove("cuon-theo");
  }
});
