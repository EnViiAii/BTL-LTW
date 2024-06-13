package cf.laptrinhweb.btl.repository;

import java.util.Map;
import java.util.List;

import cf.laptrinhweb.btl.entity.BinhLuan;

public interface BinhLuanRepository extends JdbcRepository{
	public void themBinhLuan(BinhLuan binhLuan);
	public void xoaBinhLuan(Long ma_binh_luan,Long ma_nguoi_dung);
	public Map<BinhLuan, List<BinhLuan>> layTatCaBinhLuan(Long ma_san_pham);
	public List<BinhLuan> latTatCaBinhLuanTraLoi(Long ma_binh_luan);
	public BinhLuan layBinhLuan(Long ma_binh_luan);
}
