package cf.laptrinhweb.btl.filter;

import cf.laptrinhweb.btl.constant.KhoaSession;
import cf.laptrinhweb.btl.constant.LoaiLoi;
import cf.laptrinhweb.btl.exception.xacthuc.YeuCauDangNhapLaiException;
import cf.laptrinhweb.btl.model.xacthuc.NguoiDungUngDung;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "KiemTraBatBuocDangXuatFilter")
public class KiemTraBatBuocDangXuatFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Khoi chay filter kiem tra bat buoc dang xuat");
        this.servletContext = filterConfig.getServletContext();
        this.servletContext.setAttribute(KhoaSession.BUOC_DANG_XUAT, new HashSet<>());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        NguoiDungUngDung nguoiDung = (NguoiDungUngDung) httpRequest.getSession().getAttribute(KhoaSession.NGUOI_DUNG);
        if (nguoiDung != null && yeuCauDangXuat(nguoiDung)) {
            if (httpRequest.getServletPath().startsWith("/api")) {
                throw new YeuCauDangNhapLaiException();
            } else {
                httpRequest.getSession().invalidate();
                ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/dang-nhap?loi=" + LoaiLoi.YEU_CAU_DANG_NHAP_LAI);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @SuppressWarnings("unchecked")
    private boolean yeuCauDangXuat(NguoiDungUngDung nguoiDung) {
        return ((Set<Long>) this.servletContext.getAttribute(KhoaSession.BUOC_DANG_XUAT)).contains(nguoiDung.getMaNguoiDung());
    }

    @Override
    public void destroy() {
        System.out.println("Huy filter kiem tra tai khoan phai dang xuat");
    }
}
