import { kiemTraForm } from "./form.js";

document.getElementById("form-dang-ky").addEventListener("submit", function (e) {
  if (!kiemTraForm(e.target)) {
    e.preventDefault();
  }
});
