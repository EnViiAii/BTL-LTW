import { kiemTraForm } from './form.js';

const formDatHang = document.getElementById("thong-tin-dat-hang");
formDatHang.addEventListener("submit", function(e) {
    if (!kiemTraForm(formDatHang)) {
        e.preventDefault();
    }
})
