package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.helper.KhoKetNoiCSDL;

import java.sql.Connection;
import java.sql.DriverManager;

public interface JdbcRepository {
    default Connection moKetNoi() {
        try {
            return KhoKetNoiCSDL.moKetNoi();
        } catch (Exception e) {
            System.err.println("Không thể kết nối đến CSDL");
            throw new RuntimeException(e);
        }
    }
}
