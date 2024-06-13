package cf.laptrinhweb.btl.exception.chung;

import lombok.Getter;

import java.util.Set;

@Getter
public class ThongTinKhongHopLeException extends RuntimeException {
    public ThongTinKhongHopLeException() {
        super("Thông tin không hợp lệ");
    }
}
