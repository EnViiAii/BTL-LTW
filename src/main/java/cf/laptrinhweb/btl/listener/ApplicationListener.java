package cf.laptrinhweb.btl.listener;

import cf.laptrinhweb.btl.helper.HoTroLuuTru;
import cf.laptrinhweb.btl.helper.HoTroRequest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.nio.file.Path;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\n\n\u001B[32m======================================");
        System.out.println("STARTING APPLICATION...");
        System.out.println("======================================\u001B[0m");

        HoTroLuuTru.noiLuuTru = Path.of(sce.getServletContext().getRealPath("public"));
        HoTroRequest.servletContext = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
