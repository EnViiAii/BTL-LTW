package cf.laptrinhweb.btl.controller.donhang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.laptrinhweb.btl.constant.*;
import cf.laptrinhweb.btl.entity.*;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;
import cf.laptrinhweb.btl.helper.HoTroXacThuc;
import cf.laptrinhweb.btl.service.*;
import cf.laptrinhweb.btl.service.impl.*;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

// TODO: Dung @WebServlet thay vi viet mapping trong file web.xml
@WebServlet("/dat-hang")
public class DatHangController extends HttpServlet {
	
	 private final DatHangService datHangService = new DatHangServiceImpl();
	 private final GioHangService gioHangService = new GioHangServiceImpl();
	 private final SanPhamService sanPhamService = new SanPhamServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // tra ve giao dien trang dat hang la file dat_hang.jsp trong thu muc WEB-INF
        // TODO: truyen cac tham so cua gio hang, don hang giong nhu cach sau => xem cach lay ra bien trong file .jsp
    	yeuCauQuyen(req, List.of(QuyenNguoiDung.KHACH_HANG));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        List<SanPhamTrongGio> listSP = gioHangService.layTatCaCuaNguoiDung(nguoiDung);
        req.setAttribute("danhSachSanPham", listSP);
        if(listSP.size()==0) {
        	req.setAttribute("Loi", "Gio hang trong, hay mua sam them");
    		req.getRequestDispatcher("WEB-INF/gio_hang.jsp").forward(req, resp);
    		return;
        }
        for(SanPhamTrongGio i : listSP) {
	        if( i.getSoLuong() > i.getSanPham().getSoLuong()) {
	    		req.setAttribute("Loi", "Luong mua nhieu hon so luong con lai");
	    		req.getRequestDispatcher("WEB-INF/gio_hang.jsp").forward(req, resp);
	    		return;
	    	}
        }
        req.getRequestDispatcher("WEB-INF/dat_hang.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO:  xu ly phan dat hang => goi service de tao don hang
    	yeuCauQuyen(req, List.of(QuyenNguoiDung.KHACH_HANG));

        NguoiDung nguoiDung = HoTroXacThuc.nguoiDungHienTai(req);
        List<SanPhamTrongGio> listSP = gioHangService.layTatCaCuaNguoiDung(nguoiDung);
        DatHang dathang = new DatHang();
        List<SanPhamDat> dsDat = new ArrayList<>();
        for(SanPhamTrongGio i : listSP) {
        	SanPhamDat tmp = new SanPhamDat();
        	tmp.setGia(i.getSanPham().getGia());
        	tmp.setSoLuong(i.getSoLuong());
        	tmp.setSanPham(i.getSanPham());
        	dsDat.add(tmp);
        }

        dathang.setDiaChiGiao(req.getParameter("diaChi"));
        dathang.setNguoiDung(nguoiDung);
        dathang.setTenNguoiNhan(req.getParameter("tenNguoiNhan"));
        dathang.setSdtNhan(req.getParameter("dienThoai"));
        dathang.setTinhTrang(TrangThaiDon.CHO_DUYET);
        long milis = System.currentTimeMillis();
        dathang.setNgayTaoDon(LocalDateTime.now());
        dathang.setHinhThucThanhToan(req.getParameter("hinhThucThanhToan").equals("COD")? HinhThucThanhToan.THANH_TOAN_KHI_NHAN : HinhThucThanhToan.THE_NGAN_HANG);
        if(req.getParameter("phuongThucVanChuyen").equals("VIETTEL_POST")){
        	dathang.setPhuongThucVanChuyen(PhuongThucVanChuyen.VIETTEL_POST);
        }
        else if(req.getParameter("phuongThucVanChuyen").equals("VN_POST")){
        	dathang.setPhuongThucVanChuyen(PhuongThucVanChuyen.VIETNAM_POST);
        }
        else {
        	dathang.setPhuongThucVanChuyen(PhuongThucVanChuyen.GIAO_HANG_NHANH);
        }
        dathang.setDanhSachSanPham(dsDat);
        dathang.capNhatTongTien();
              
        datHangService.themDatHang(dathang, listSP, nguoiDung);
        System.out.print("thanh Cong");
        
        
        
        // dieu huong lai ve trang lich su don hang sau khi dat hang
        resp.sendRedirect(req.getContextPath() + "/lich-su-mua-hang");
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


