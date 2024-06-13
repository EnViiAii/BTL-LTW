package cf.laptrinhweb.btl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThongBao {
    @Builder.Default
    protected String anh = "https://gist.githubusercontent.com/davecheney/63e28865d3ab6693b8c3a590bd2ab503/raw/650c4c0bf842f9440651311e534a971d98f7d10a/gopher2.svg";
    protected String tieuDe;
    protected String noiDung;
}
