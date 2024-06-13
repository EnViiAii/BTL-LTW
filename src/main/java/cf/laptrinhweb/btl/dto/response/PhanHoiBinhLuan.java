package cf.laptrinhweb.btl.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhanHoiBinhLuan {
	private String accessToken;
	
	public PhanHoiBinhLuan(String accessToken) {
		// TODO Auto-generated constructor stub
		this.accessToken = accessToken;
	}
}
