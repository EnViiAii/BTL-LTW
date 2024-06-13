package cf.laptrinhweb.btl.controller.xacthuc;

import cf.laptrinhweb.btl.constant.KhoaSession;
import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;
import cf.laptrinhweb.btl.service.XacThucService;
import cf.laptrinhweb.btl.service.impl.XacThucServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cf.laptrinhweb.btl.helper.HoTroXacThuc.yeuCauQuyen;

@WebServlet("/quan-ly/nguoi-dung/phan-quyen")
public class PhanQuyenController extends HttpServlet {
    private final XacThucService xacThucService = new XacThucServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));

        Long maNguoiDung = Long.parseLong(req.getParameter("maNguoiDung"));
        NguoiDung nguoiDung = xacThucService.timNguoiDung(DieuKienNguoiDung.builder().maNguoiDung(maNguoiDung).build())
                .get(0);
        req.setAttribute("nguoiDung", nguoiDung);
        req.getRequestDispatcher("/WEB-INF/phan_quyen.jsp").forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        yeuCauQuyen(req, List.of(QuyenNguoiDung.ADMIN));

        Set<QuyenNguoiDung> quyenDuocChon = layDanhSachQuyen(req);
        NguoiDung nguoiDung = xacThucService.timNguoiDung(
            DieuKienNguoiDung.builder()
                .maNguoiDung(Long.parseLong(req.getParameter("maNguoiDung")))
                .build()
        ).get(0);
        xacThucService.phanQuyen(nguoiDung, quyenDuocChon);
        ((Set<Long>) req.getServletContext().getAttribute(KhoaSession.BUOC_DANG_XUAT)).add(nguoiDung.getMaNguoiDung());
        resp.sendRedirect(req.getContextPath() + "/quan-ly/nguoi-dung?maNguoiDung=" + nguoiDung.getMaNguoiDung());
    }

    private Set<QuyenNguoiDung> layDanhSachQuyen(HttpServletRequest req) {
        String[] danhSachQuyen = req.getParameterMap().get("quyen");
        if (danhSachQuyen == null) return Set.of();
        return Arrays.stream(danhSachQuyen)
            .map(QuyenNguoiDung::valueOf)
            .collect(Collectors.toSet());
    }
}
