package cf.laptrinhweb.btl.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhanHoi<T> {
    private String thongBaoLoi;
    private T duLieu;
}
