const danhSachMuc = Array.from(document.querySelectorAll("#menu > .muc"));
const mucHienTai = danhSachMuc.find((muc) => muc.classList.contains("hien-tai"));

danhSachMuc
  .filter((muc) => muc.classList.contains("co-menu-con"))
  .forEach((muc) => {
    muc.addEventListener("click", function () {
      if (!muc.classList.contains("duoc-chon")) {
		  // huy chon muc khac
        danhSachMuc.find((m) => m.classList.contains("duoc-chon"))?.classList.remove("duoc-chon");
        // chon muc
        mucHienTai.classList.remove("hien-tai");
        muc.classList.add("duoc-chon");
      } else {
        // tro ve muc hien tai
        muc.classList.remove("duoc-chon");
        mucHienTai.classList.add("hien-tai");
      }
    });
  });
