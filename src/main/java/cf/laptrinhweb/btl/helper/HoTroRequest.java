package cf.laptrinhweb.btl.helper;

import cf.laptrinhweb.btl.dto.response.PhanHoi;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HoTroRequest {
    public static ServletContext servletContext;

    public static String layThamSo(HttpServletRequest request, String tenThamSo) {
        try {
            Part part = request.getParts()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(tenThamSo))
                .findFirst()
                .orElse(null);
            return part == null ? null : IOUtils.toString(part.getInputStream(), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void khongCachePage(HttpServletResponse response) {
        response.addHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("Cache-Control", "pre-check=0, post-check=0");
        response.setDateHeader("Expires", 0);
    }

    public static void traVeThanhCong(HttpServletResponse response, Object duLieu) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(HoTroJson.taoJson(
            PhanHoi.builder()
                .duLieu(duLieu)
                .build()
        ));
    }

    public static void traVeLoi(HttpServletResponse response, int maTrangThai, String thongBaoLoi) throws IOException {
        response.setStatus(maTrangThai);
        response.getWriter().println(HoTroJson.taoJson(
            PhanHoi.builder()
                .thongBaoLoi(thongBaoLoi)
                .build()
        ));
    }
}
