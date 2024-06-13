package cf.laptrinhweb.btl.api.xacthuc;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.dto.request.PhanQuyenDTO;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeThanhCong;
import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/api/nguoi-dung/phan-quyen")
public class PhanQuyenAPI extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));

        PhanQuyenDTO phanQuyenDTO = HoTroJson.layThongTin(req, PhanQuyenDTO.class);
        NguoiDung nguoiDung = xacThucService.timNguoiDung(
            DieuKienNguoiDung.builder()
                .maNguoiDung(phanQuyenDTO.getMaNguoiDung())
                .build()
        ).get(0);
        xacThucService.phanQuyen(nguoiDung, phanQuyenDTO.getDanhSachQuyen());
        traVeThanhCong(resp, null);
    }
}
