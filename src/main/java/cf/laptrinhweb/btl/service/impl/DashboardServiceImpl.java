package cf.laptrinhweb.btl.service.impl;

import cf.laptrinhweb.btl.model.*;
import cf.laptrinhweb.btl.repository.ThongKeRepository;
import cf.laptrinhweb.btl.repository.impl.ThongKeRepositoryImpl;
import cf.laptrinhweb.btl.service.DashboardService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class DashboardServiceImpl implements DashboardService {
    private final ThongKeRepository thongKeRepository = new ThongKeRepositoryImpl();

    @Override
    public Map<String, Object> layThongKeDoanhThu(GiaiDoan giaiDoan) {
        List<BanGhiDuLieu> doanhThuTungNgay = layDuLieuDoanhThu(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
        Map<String, Object> thongKe = new HashMap<>();
        double tongDoanhThu = 0;
        for (BanGhiDuLieu banGhi : doanhThuTungNgay) {
            tongDoanhThu += (Double) banGhi.getGiaTri();
        }
        thongKe.put("tongDoanhThu", tongDoanhThu);
        thongKe.put("trungBinhDon", thongKeRepository.layGiaTriDonTrungBinh(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc()));
        thongKe.put("doanhThuTungNgay", doanhThuTungNgay);
        return thongKe;
    }

    @Override
    public List<BanGhiDuLieu> thongKeSoDonTungNgay(GiaiDoan giaiDoan) {
        Map<LocalDate, Double> duLieuThongKeDuoc = thongKeRepository.thongKeSoDon(
            giaiDoan.ngayBatDau(),
            giaiDoan.ngayKetThuc()
        );
        return taoDuLieuHangNgay(duLieuThongKeDuoc, giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
    }

    @Override
    public List<BanGhiDuLieu> thongKeTrangThaiDon(GiaiDoan giaiDoan) {
        TiLeDonHuy tiLeDonHuy = thongKeRepository.tinhTiLeHuyDon(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());

        return Arrays.asList(
            new BanGhiDuLieu("Đơn hủy", tiLeDonHuy.getSoDonHuy()),
            new BanGhiDuLieu("Đơn thành công", tiLeDonHuy.getSoDonThanhCong()),
            new BanGhiDuLieu("Khác", tiLeDonHuy.getConLai())
        );
    }

    @Override
    public List<SanPhamMuaNhieu> layTopSanPhamBanChay(GiaiDoan giaiDoan) {
        return thongKeRepository.layTopSanPhamBanChay(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
    }
    
    @Override
    public List<KhachHangMuaNhieu> layTopKhachMuaNhieu(GiaiDoan giaiDoan) {
        return thongKeRepository.layTopKhachMuaNhieu(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
    }
    
    @Override
    public List<TheLoaiMuaNhieu> layTheLoaiBanChay(GiaiDoan giaiDoan) {
    	return thongKeRepository.layTheLoaiBanChay(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
    }

    @Override
    public List<ThuongHieuMuaNhieu> layThuongHieuBanChay(GiaiDoan giaiDoan) {
    	return thongKeRepository.layThuongHieuBanChay(giaiDoan.ngayBatDau(), giaiDoan.ngayKetThuc());
    }

    private List<BanGhiDuLieu> layDuLieuDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        Map<LocalDate, Double> duLieuThongKeDuoc = thongKeRepository.thongKeDoanhThu(ngayBatDau, ngayKetThuc);
        return taoDuLieuHangNgay(duLieuThongKeDuoc, ngayBatDau, ngayKetThuc);
    }

    private List<BanGhiDuLieu> taoDuLieuHangNgay(Map<LocalDate, Double> duLieu, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        List<BanGhiDuLieu> dsDuLieuDayDu = new ArrayList<>();
        LocalDate ngayHienTai = ngayBatDau;
        DateTimeFormatter dinhDangNgay = DateTimeFormatter.ofPattern("dd/MM");
        while (!ngayHienTai.isAfter(ngayKetThuc)) {
            double doanhThuCuaNgay = duLieu.getOrDefault(ngayHienTai, (double) 0);
            dsDuLieuDayDu.add(BanGhiDuLieu.of(dinhDangNgay.format(ngayHienTai), doanhThuCuaNgay));
            ngayHienTai = ngayHienTai.plusDays(1);
        }
        return dsDuLieuDayDu;
    }
}
