package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.ChatLieu;
import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.mapper.ChatLieuMapper;
import cf.laptrinhweb.btl.mapper.TheLoaiMapper;
import cf.laptrinhweb.btl.repository.ChatLieuRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChatLieuRepositoryImpl implements ChatLieuRepository {
    @Override
    public List<ChatLieu> layTatCa() {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT * FROM chat_lieu
            """);
            ResultSet resultSet = ps.executeQuery();
            List<ChatLieu> dsChatLieu = new ArrayList<>();
            ChatLieuMapper mapper = new ChatLieuMapper();
            while (resultSet.next()) {
                dsChatLieu.add(mapper.map(resultSet));
            }
            return dsChatLieu;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay danh sach the loai", e);
        }
    }

	@Override
	public ChatLieu timChatLieu(Long ma_chat_lieu) {
		// TODO Auto-generated method stub
		ChatLieu a = new ChatLieu();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * from nguoi_dung
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, ma_chat_lieu);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	a.setMaChatLieu(rs.getLong("ma_chat_lieu"));
            	a.setTenChatLieu(rs.getString("ten_chat_lieu"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return a;
	}
}
