package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.ChatLieu;
import cf.laptrinhweb.btl.entity.TheLoai;

import java.util.List;

public interface ChatLieuRepository extends JdbcRepository {
    List<ChatLieu> layTatCa();
    ChatLieu timChatLieu(Long ma_chat_lieu);
}
