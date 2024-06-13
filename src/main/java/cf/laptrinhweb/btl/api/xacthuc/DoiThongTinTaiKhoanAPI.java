package cf.laptrinhweb.btl.api.xacthuc;

import cf.laptrinhweb.btl.dto.request.ThongTinTaiKhoanDTO;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.chung.ThongTinKhongHopLeException;
import cf.laptrinhweb.btl.exception.xacthuc.ThongTinDangNhapDaTonTaiException;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
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
import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauDangNhap;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;

@WebServlet("/api/tai-khoan/thong-tin")
public class DoiThongTinTaiKhoanAPI extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauDangNhap(req);

        try {
            NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
            ThongTinTaiKhoanDTO thongTinMoi = HoTroJson.layThongTin(req, ThongTinTaiKhoanDTO.class);
            gopThongTin(nguoiDung, thongTinMoi);
            xacThucService.doiThongTinTaiKhoan(nguoiDung);
            traVeThanhCong(resp, null);
        } catch (ThongTinDangNhapDaTonTaiException e) {
            String thongBaoLoi = null;
            if (e.getThongTinTrungLap().contains(EMAIL)) {
                thongBaoLoi = "Email đã tồn tại trên hệ thống";
            } if (e.getThongTinTrungLap().contains(TEN_DANG_NHAP)) {
                thongBaoLoi = "Tên đăng nhập đã được sử dụng bởi người khác";
            } else if (e.getThongTinTrungLap().contains(SO_DIEN_THOAI)) {
                thongBaoLoi = "Số điện thoại đã tồn tại trên hệ thống";
            }
            traVeLoi(resp, SC_CONFLICT, thongBaoLoi);
        } catch (ThongTinKhongHopLeException e) {
            traVeLoi(resp, SC_BAD_REQUEST, "Thông tin không hợp lệ. Kiểm tra lại định dạng các trường");
        }
    }

    private void gopThongTin(NguoiDung nguoiDung, ThongTinTaiKhoanDTO thongTinMoi) {
        nguoiDung.setTenHienThi(thongTinMoi.getTenHienThi());
        nguoiDung.setTenDangNhap(thongTinMoi.getTenDangNhap());
        nguoiDung.setEmail(thongTinMoi.getEmail());
        nguoiDung.setSoDienThoai(thongTinMoi.getSoDienThoai());
        nguoiDung.setMatKhau("000000");
    }
}
