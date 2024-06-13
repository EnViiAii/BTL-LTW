package cf.laptrinhweb.btl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebFilter(filterName = "CacheFilter")
public class CacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            if (httpRequest.getServletPath().startsWith("/static")) {
                httpResponse.setHeader("Cache-Control", "public, max-age=" + TimeUnit.DAYS.toSeconds(30));
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
