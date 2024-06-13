package cf.laptrinhweb.btl.service;

import java.util.List;
import java.util.Map;

import cf.laptrinhweb.btl.entity.BinhLuan;

public interface BinhLuanService{
	public void themBinhLuan(BinhLuan binh_luan);
	public void xoaBinhLuan(Long ma_binh_luan,Long ma_nguoi_dung);
	public Map<BinhLuan,List<BinhLuan>> layTatCaBinhLuan(Long ma_san_pham);
	public List<BinhLuan> layTatCaBinhLuanTraLoi(Long ma_binh_luan);
	public BinhLuan layBinhLuan(Long ma_binh_luan);
}
