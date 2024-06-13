package cf.laptrinhweb.btl.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TiLeDonHuy {
    private int soDonHuy;
    private int soDonThanhCong;
    private int conLai;
}
