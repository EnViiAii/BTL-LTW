package cf.laptrinhweb.btl.filter;

import cf.laptrinhweb.btl.constant.KhoaSession;
import cf.laptrinhweb.btl.helper.HoTroRequest;
import cf.laptrinhweb.btl.helper.HoTroToken;
import cf.laptrinhweb.btl.model.xacthuc.NguoiDungUngDung;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "TokenFilter")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String headerXacThuc = httpRequest.getHeader("Authorization");
            if (headerXacThuc != null && headerXacThuc.startsWith("Bearer ")) {
                String token = headerXacThuc.substring(7);
                try {
                    Claims claims = HoTroToken.kiemTraVaLayThongTin(token);
                    var nguoiDungUngDung = new ObjectMapper().convertValue(claims.get("nguoiDung"), NguoiDungUngDung.class);
                    httpRequest.getSession().setAttribute(KhoaSession.NGUOI_DUNG, nguoiDungUngDung);
                } catch (ExpiredJwtException e) {
                    HoTroRequest.traVeLoi(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "Token da het han");
                    return;
                } catch (JwtException e) {
                    HoTroRequest.traVeLoi(httpResponse, HttpServletResponse.SC_FORBIDDEN, "Token khong hop le");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
