package cf.laptrinhweb.btl.helper;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.model.xacthuc.NguoiDungUngDung;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class HoTroToken {
    private static final String maKhoa = "chuoi_tao_khoa_de_ma_hoa_va_kiem_tra_jwt";
    private static final Key khoaToken = Keys.hmacShaKeyFor(maKhoa.getBytes());

    public static String taoAccessToken(NguoiDung nguoiDung) {
        return Jwts.builder()
            .setSubject(nguoiDung.getMaNguoiDung().toString())
            .claim("loaiToken", "accessToken")
            .claim("nguoiDung", new NguoiDungUngDung(nguoiDung))
            .setExpiration(Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(khoaToken)
            .compact();
    }

    public static Claims kiemTraVaLayThongTin(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(khoaToken)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
