package cf.laptrinhweb.btl.constant;

import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern EMAIL = Pattern.compile("^([\\d\\w-_.])+@([\\d\\w.])+\\.([\\d\\w]){2,4}$");
    public static final Pattern SO_DIEN_THOAI = Pattern.compile("^(\\+\\d{1,3})(\\d{9})$");
    public static final Pattern TEN_DANG_NHAP = Pattern.compile("^[\\d\\w.]{2,16}$");
    public static final Pattern MAT_KHAU = Pattern.compile("^[a-zA-Z0-9~!@#$&*.,;:]{6,}$");
}
