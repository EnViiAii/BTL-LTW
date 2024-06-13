"use strict";
import { kiemTraForm } from "./form.js";

const formSanPham = document.getElementById("form-san-pham");
formSanPham.addEventListener("submit", function(e) {
    if (!kiemTraForm(formSanPham)) {
        e.preventDefault();
        return;
    }
    let daAn = formSanPham.querySelector("input[name='daAn']");
    daAn.value = daAn.checked;
})