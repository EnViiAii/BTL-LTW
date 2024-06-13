package cf.laptrinhweb.btl.api.xacthuc;

import cf.laptrinhweb.btl.constant.LoaiThongTinDangNhap;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.chung.ThongTinKhongHopLeException;
import cf.laptrinhweb.btl.exception.xacthuc.ThongTinDangNhapDaTonTaiException;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cf.laptrinhweb.btl.constant.LoaiThongTinDangNhap.*;
import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeLoi;
import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeThanhCong;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;

@WebServlet("/api/dang-ky")
public class DangKyAPI extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NguoiDung thongTinDangKy = HoTroJson.layThongTin(req, NguoiDung.class);
            xacThucService.dangKy(thongTinDangKy);
            traVeThanhCong(resp, null);
        } catch (ThongTinDangNhapDaTonTaiException e) {
            String thongBaoLoi = null;
            if (e.getThongTinTrungLap().contains(EMAIL)) {
                thongBaoLoi = "Email đã tồn tại trên hệ thống";
            } else if (e.getThongTinTrungLap().contains(SO_DIEN_THOAI)) {
                thongBaoLoi = "Số điện thoại đã tồn tại trên hệ thống";
            } else if (e.getThongTinTrungLap().contains(TEN_DANG_NHAP)) {
                thongBaoLoi = "Tên đăng nhập đã được sử dụng bởi người khác";
            }
            traVeLoi(resp, SC_CONFLICT, thongBaoLoi);
        } catch (ThongTinKhongHopLeException e) {
            traVeLoi(resp, SC_BAD_REQUEST, "Thông tin không hợp lệ. Kiểm tra lại định dạng các trường");
        }
    }
}
