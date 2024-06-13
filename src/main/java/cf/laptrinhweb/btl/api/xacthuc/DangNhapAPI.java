package cf.laptrinhweb.btl.api.xacthuc;

import cf.laptrinhweb.btl.dto.request.DangNhapDTO;
import cf.laptrinhweb.btl.dto.response.PhanHoiDangNhap;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.exception.xacthuc.SaiThongTinDangNhapException;
import cf.laptrinhweb.btl.exception.xacthuc.TaiKhoanBiKhoaException;
import cf.laptrinhweb.btl.helper.HoTroJson;
import cf.laptrinhweb.btl.helper.HoTroToken;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeLoi;
import static cf.laptrinhweb.btl.helper.HoTroRequest.traVeThanhCong;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet("/api/dang-nhap")
public class DangNhapAPI extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DangNhapDTO dangNhapDTO = HoTroJson.layThongTin(req, DangNhapDTO.class);
            NguoiDung nguoiDung = xacThucService.dangNhap(
                dangNhapDTO.getTenDangNhap(),
                dangNhapDTO.getMatKhau()
            );
            traVeThanhCong(
                resp,
                PhanHoiDangNhap.builder()
                    .accessToken(HoTroToken.taoAccessToken(nguoiDung))
                    .build()
            );
        } catch (SaiThongTinDangNhapException e) {
            traVeLoi(resp, SC_BAD_REQUEST, "Thong tin dang nhap khong chinh xac");
        } catch (TaiKhoanBiKhoaException e) {
            traVeLoi(resp, SC_UNAUTHORIZED, "Tai khoan cua ban da bi khoa. Lien he quan tri vien de biet them chi tiet");
        }
    }
}
