package cf.laptrinhweb.btl.helper;

import cf.laptrinhweb.btl.constant.CSDL;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class KhoKetNoiCSDL {
    private static final int SO_KET_NOI_TOI_THIEU = 2;
    private static final int SO_KET_NOI_TOI_DA = 5;

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setDriverClassName(CSDL.DRIVER_CLASS);
        String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?createDatabaseIfNotExist=true", CSDL.DIA_CHI, CSDL.CONG, CSDL.CSDL);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(CSDL.TEN_DANG_NHAP);
        config.setPassword(CSDL.MAT_KHAU);
        config.setMinimumIdle(SO_KET_NOI_TOI_THIEU);
        config.setMaximumPoolSize(SO_KET_NOI_TOI_DA);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public static Connection moKetNoi() throws SQLException {
        return dataSource.getConnection();
    }
}
