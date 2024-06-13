package cf.laptrinhweb.btl.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Trang<T> {
    private int tongSoTrang;
    private List<T> duLieu;
}
