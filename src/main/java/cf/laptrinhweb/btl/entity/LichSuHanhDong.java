package cf.laptrinhweb.btl.entity;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Builder
public class LichSuHanhDong {
    private Long maHanhDong;
    private NguoiDung nguoiDung;
    private LoaiHanhDong loaiHanhDong;
    private String diaChiIP;
    @Builder.Default
    private Date thoiGian = new Date();
    private String chiTiet;
    private boolean thanhCong;
}
