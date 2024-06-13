package cf.laptrinhweb.btl.constant;

public class CSDL {
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String CONG = System.getenv("DB_PORT");
    public static final String DIA_CHI = System.getenv("DB_HOST");
    public static final String CSDL = System.getenv("DB_NAME");
    public static final String TEN_DANG_NHAP = System.getenv("DB_USERNAME");
    public static final String MAT_KHAU = System.getenv("DB_PASSWORD");
}
