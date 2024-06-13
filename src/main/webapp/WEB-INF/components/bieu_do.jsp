<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bieu_do.css"/>

<div class="bieu-do" style="overflow: auto">
  <div class="khung-chua-bieu-do" style="position: relative;">
    <canvas id="${param.get('idBieuDo')}"></canvas>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
<script>
  var ctx = document.getElementById("${param.get('idBieuDo')}").getContext("2d");

  var thietLap = {
    type: "${param.get("loaiBieuDo")}",
    data: {
      labels: [
        <c:forEach var="banGhi" items="${danhSachDuLieu.danhSachDuLieu}">"${banGhi.nhan}",</c:forEach>
      ],
      datasets: [
        {
          data: [
            <c:forEach var="banGhi" items="${danhSachDuLieu.danhSachDuLieu}">${banGhi.giaTri},</c:forEach>
          ],
          backgroundColor: "#48cab232",
          borderColor: "#48cab2",
          borderWidth: 2
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        title: {
          display: true,
          text: "${danhSachDuLieu.tenDanhSach}",
        },
        legend: {
          display: false,
        },
      },
    },
  };
  var myChart = new Chart(ctx, thietLap);
</script>