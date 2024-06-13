package cf.laptrinhweb.btl.exception.xacthuc;

public class MatKhauKhongDungException extends RuntimeException {
    public MatKhauKhongDungException() {
        super("Mật khẩu không chính xác");
    }
}
