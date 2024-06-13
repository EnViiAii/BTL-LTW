package cf.laptrinhweb.btl.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class HoTroJson {
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModules(new JavaTimeModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T layThongTin(HttpServletRequest request, Class<T> lopDich) {
        try {
            return objectMapper.readValue(request.getReader(), lopDich);
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay du lieu tu request", e);
        }
    }

    public static String taoJson(Object nguon) {
        try {
            return objectMapper.writeValueAsString(nguon);
        } catch (Exception e) {
            throw new RuntimeException("Khong the tao json tu object", e);
        }
    }
}
