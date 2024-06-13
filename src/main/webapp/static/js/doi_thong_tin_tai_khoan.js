import {kiemTraForm} from "./form.js";

let formDoiThongTin = document.getElementById("form-thong-tin-tai-khoan");
formDoiThongTin.addEventListener("submit", function(e) {
    if (!kiemTraForm(formDoiThongTin)) {
        e.preventDefault();
    }
})