package cf.laptrinhweb.btl.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhanHoiGioHang {
    private String accessToken;
    
    public PhanHoiGioHang(String accessToken) {
		this.accessToken = accessToken;
	}
}
