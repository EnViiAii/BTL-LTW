package cf.laptrinhweb.btl.filter;

import cf.laptrinhweb.btl.model.thongbao.ThongBaoTkBiKhoa;
import cf.laptrinhweb.btl.model.thongbao.YeuCauDangNhapLai;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static cf.laptrinhweb.btl.constant.LoaiLoi.YEU_CAU_DANG_NHAP_LAI;

@WebFilter(filterName = "ThongBaoLoiFilter")
public class ThongBaoLoiFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String loi = request.getParameter("loi");
        if (loi != null) {
            switch (loi) {
                case YEU_CAU_DANG_NHAP_LAI -> request.setAttribute("thongBao", new YeuCauDangNhapLai());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
