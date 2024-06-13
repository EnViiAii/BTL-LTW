package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.constant.*;
import java.util.List;
import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.model.DieuKienDonHang;

public interface DatHangRepository extends JdbcRepository{
	void themDatHang(DatHang datHang);
	List<DatHang> layTatCaCuaNguoiDung(NguoiDung nguoidung);
	DatHang layDatHang(Long ma_dat_hang);
	DatHang layDonTheoMaDatHang(Long maDatHang, NguoiDung nguoidung);
	public DatHang layDonTheoMaBoiQuanLy(Long maDatHang);
    List<DatHang> locTheoDieuKien(DieuKienDonHang dieuKienDonHang);
    void CapNhatTrangThaiDon(Long maDatHang, TrangThaiDon trangThaiMoi );
}
