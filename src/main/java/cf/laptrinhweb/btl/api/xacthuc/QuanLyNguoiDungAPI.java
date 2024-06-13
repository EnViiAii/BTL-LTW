package cf.laptrinhweb.btl.api.xacthuc;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.dto.response.NguoiDungDTO;
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

@WebServlet("/api/nguoi-dung")
public class QuanLyNguoiDungAPI extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));
        
        DieuKienNguoiDung dieuKienNguoiDung = DieuKienNguoiDung.trichXuat(req);
        List<NguoiDungDTO> dsNguoiDung = xacThucService.timNguoiDung(dieuKienNguoiDung)
            .stream()
            .map(NguoiDungDTO::new)
            .toList();
        traVeThanhCong(resp, dsNguoiDung);
    }
}
