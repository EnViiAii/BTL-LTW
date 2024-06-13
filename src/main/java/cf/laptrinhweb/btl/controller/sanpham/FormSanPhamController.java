package cf.laptrinhweb.btl.controller.sanpham;

import cf.laptrinhweb.btl.repository.ChatLieuRepository;
import cf.laptrinhweb.btl.repository.TheLoaiRepository;
import cf.laptrinhweb.btl.repository.ThuongHieuRepository;
import cf.laptrinhweb.btl.repository.impl.ChatLieuRepositoryImpl;
import cf.laptrinhweb.btl.repository.impl.TheLoaiRepositoryImpl;
import cf.laptrinhweb.btl.repository.impl.ThuongHieuRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FormSanPhamController extends HttpServlet {
    private final TheLoaiRepository theLoaiRepository = new TheLoaiRepositoryImpl();
    private final ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepositoryImpl();
    private final ChatLieuRepository chatLieuRepository = new ChatLieuRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("danhSachTheLoai", theLoaiRepository.layTatCa());
        req.setAttribute("danhSachThuongHieu", thuongHieuRepository.layTatCa());
        req.setAttribute("danhSachChatLieu", chatLieuRepository.layTatCa());
    }
}
