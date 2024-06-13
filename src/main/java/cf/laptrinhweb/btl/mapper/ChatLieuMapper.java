package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.ChatLieu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatLieuMapper {
    public ChatLieu map(ResultSet resultSet) throws SQLException {
        return ChatLieu.builder()
            .maChatLieu(resultSet.getLong("ma_chat_lieu"))
            .tenChatLieu(resultSet.getString("ten_chat_lieu"))
            .build();
    }
}
