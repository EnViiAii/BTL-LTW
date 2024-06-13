package cf.laptrinhweb.btl.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import cf.laptrinhweb.btl.model.*;

public interface ThongKeRepository extends JdbcRepository {
    Map<LocalDate, Double> thongKeDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    double layGiaTriDonTrungBinh(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    TiLeDonHuy tinhTiLeHuyDon(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    List<SanPhamMuaNhieu> layTopSanPhamBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    List<KhachHangMuaNhieu> layTopKhachMuaNhieu(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    List<TheLoaiMuaNhieu> layTheLoaiBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    List<ThuongHieuMuaNhieu> layThuongHieuBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc);
    Map<LocalDate, Double> thongKeSoDon(LocalDate ngayBatDau, LocalDate ngayKetThuc);
}
