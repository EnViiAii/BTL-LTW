package cf.laptrinhweb.btl.helper;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class HoTroLuuTru {
    public static Path noiLuuTru;

    public static String luuFile(Part requestPart) {
        // tao ten file
        String tenFile;
        do {
            tenFile = UUID.randomUUID().toString();
        } while (Files.exists(noiLuuTru.resolve(tenFile)));

        // luu file
        tenFile += tachPhanMoRongFile(requestPart.getSubmittedFileName());
        try {
            Files.write(noiLuuTru.resolve(tenFile), requestPart.getInputStream().readAllBytes(), CREATE_NEW);
            return "/public/" + tenFile;
        } catch (IOException e) {
            throw new RuntimeException("Khong the luu file", e);
        }
    }

    private static String tachPhanMoRongFile(String tenFile) {
        return tenFile.substring(tenFile.lastIndexOf("."));
    }
}
