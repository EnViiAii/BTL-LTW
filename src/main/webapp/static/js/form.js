const kiemTra = {
  batBuoc: {
    thongBaoLoi: "Không được bỏ trống",
    hopLe: function (giaTri) {
      return giaTri?.trim().length > 0;
    },
  },
  tenDangNhap: {
    thongBaoLoi: "Tên đăng nhập không hợp lệ",
    hopLe: function (giaTri) {
      return giaTri.match(/^[\d\w.]{2,16}$/);
    },
  },
  matKhau: {
    thongBaoLoi: "Mật khẩu không đảm bảo",
    hopLe: function (giaTri) {
      return giaTri.match(/^[a-zA-Z0-9~!@#$&*.,;:]{6,}$/);
    },
  },
  email: {
    thongBaoLoi: "Địa chỉ email không hợp lệ",
    hopLe: function (giaTri) {
      return giaTri.match(/^([\d\w-_.])+@([\d\w.])+\.([\d\w]){2,4}$/);
    },
  },
  soDienThoai: {
    thongBaoLoi: "Số điện thoại không hợp lệ",
    hopLe: function (giaTri) {
      return giaTri.match(/^(\+\d{1,3})(\d{9})$/);
    },
  },
  khongAm: {
    thongBaoLoi: "Không thể nhận giá trị âm",
    hopLe: function (giaTri) {
        return !giaTri || Number(giaTri) >= 0;
    }
  }
};

function layGiaTriTruong(truong) {
  return truong.querySelector("input").value;
}

export function themThongBaoLoi(truong, thongBaoLoi) {
  let khungChuaLoi = truong.querySelector(".thong-bao-loi");
  truong.classList.add("loi");
  // them cac loi
  let theLoi = document.createElement("li");
  theLoi.classList.add("loi");
  theLoi.innerHTML = thongBaoLoi;
  return khungChuaLoi.appendChild(theLoi);
}

export function kiemTraForm(form) {
  let dsTruong = form.querySelectorAll(".truong");
  let formHopLe = true;

  Array.from(dsTruong).forEach((truong) => {
    let khungChuaLoi = truong.querySelector(".thong-bao-loi");
    if (!khungChuaLoi) {
      khungChuaLoi = document.createElement("ul");
      khungChuaLoi.classList.add("thong-bao-loi");
      khungChuaLoi.style.display = "none";
      truong.appendChild(khungChuaLoi);
    }
    khungChuaLoi.innerHTML = "";
    let dsDieuKien = truong.getAttribute("data-dieu-kien")?.split(/\s+/g);
    dsDieuKien?.forEach((dieuKien) => {
      if (!kiemTra[dieuKien].hopLe(layGiaTriTruong(truong))) {
        khungChuaLoi.style.display = "block";
        themThongBaoLoi(truong, kiemTra[dieuKien].thongBaoLoi);
        formHopLe = false;
      } else {
        truong.classList.remove("loi");
      }
    });
  });

  return formHopLe;
}
