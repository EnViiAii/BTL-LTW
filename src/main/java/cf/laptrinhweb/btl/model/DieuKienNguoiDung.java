package cf.laptrinhweb.btl.model;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.Quyen;
import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DieuKienNguoiDung extends PhanTrang {
    @Builder.Default
    private Integer kichThuoc = 15;
    private Long maNguoiDung;
    private String tuKhoa = "";
    private List<QuyenNguoiDung> quyen;

    public static DieuKienNguoiDung trichXuat(HttpServletRequest request) {
        DieuKienNguoiDung dieuKienNguoiDung = DieuKienNguoiDung.builder().tuKhoa(request.getParameter("tuKhoa")).build();
        String maNguoiDungStr = request.getParameter("maNguoiDung");
        if (maNguoiDungStr != null && !maNguoiDungStr.isBlank()) {
            dieuKienNguoiDung.setMaNguoiDung(Long.parseLong(maNguoiDungStr));
        }
        String trang = request.getParameter("trang");
        if (trang != null && !trang.isBlank() && Integer.parseInt(trang) > 0)
            dieuKienNguoiDung.setTrang(Integer.parseInt(trang) - 1);
        String kichThuoc = request.getParameter("kichThuoc");
        if (kichThuoc != null && !kichThuoc.isBlank())
            dieuKienNguoiDung.setKichThuoc(Integer.parseInt(kichThuoc));
        String[] dsQuyen = request.getParameterValues("quyen");
        if (dsQuyen != null)
            dieuKienNguoiDung.setQuyen(Arrays.stream(dsQuyen).map(QuyenNguoiDung::valueOf).toList());
        return dieuKienNguoiDung;
    }
}
