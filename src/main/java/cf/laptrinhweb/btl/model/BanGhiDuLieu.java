package cf.laptrinhweb.btl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BanGhiDuLieu {
    private String nhan;
    private Number giaTri;
    public static BanGhiDuLieu of(String nhan, Number giaTri) {
        BanGhiDuLieu banGhiDuLieu = new BanGhiDuLieu();
        banGhiDuLieu.nhan = nhan;
        banGhiDuLieu.giaTri = giaTri;
        return banGhiDuLieu;
    }
}
