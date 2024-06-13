let cacBinhLuan = document.getElementsByClassName("binh-luan");

function themFormBinhLuan(khungChua, maBinhLuanGoc) {
    // neu da co form phan hoi => xoa di thay the
    let formDaCo = khungChua.querySelector("form.tao-binh-luan");
    if (formDaCo) {
        formDaCo.remove();
    }
    // tao form binh luan moi
    let formBinhLuan = document.createElement("form");
    formBinhLuan.classList.add("tao-binh-luan");
    formBinhLuan.innerHTML = `
        ${maBinhLuanGoc ? `<input type="hidden" name="maBinhLuanGoc" value="${maBinhLuanGoc}" />` : ""}
        <textarea name="noi_dung_binh_luan" placeholder="Nội dung phản hồi"></textarea>
        <input type = "hidden" name = "maSanPham" value = "${window.maSanPham}"/>
        <button class="nut kieu-2 nut-gui-bl" type="submit">
          Gửi
        </button>
    `;
    formBinhLuan.setAttribute("method", "post");
    formBinhLuan.setAttribute("action", `${window.contextPath}/them-binh-luan`);
    khungChua.appendChild(formBinhLuan);
    formBinhLuan.querySelector("textarea").focus();
}

function laBinhLuanCapDau(binhLuan) {
    return binhLuan.parentElement.classList.contains("khung-binh-luan");
}

Array.from(cacBinhLuan).forEach((binhLuan) => {
    let nutPhanHoi = binhLuan.querySelector(".nut-phan-hoi");
    nutPhanHoi.addEventListener("click", function () {
        let khungPhanHoi;
        if (laBinhLuanCapDau(binhLuan)) {
            khungPhanHoi = binhLuan.nextElementSibling;
        } else {
            khungPhanHoi = binhLuan.parentElement.parentElement;
        }
        let maBinhLuanGoc = khungPhanHoi.previousElementSibling.getAttribute("data-ma-binh-luan");
        themFormBinhLuan(khungPhanHoi, maBinhLuanGoc);
    });
});