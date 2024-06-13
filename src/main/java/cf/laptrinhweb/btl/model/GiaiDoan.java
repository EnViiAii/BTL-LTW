package cf.laptrinhweb.btl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
public class GiaiDoan {
    private int nam;
    private int thang;

    public LocalDate ngayBatDau() {
        return LocalDate.of(nam, thang, 1);
    }

    public LocalDate ngayKetThuc() {
        return YearMonth.of(nam, thang).atEndOfMonth();
    }
}
