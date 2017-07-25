package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

public class AdminManager {

    private final String CONFIG_PATH = "/admin.properties";
    private String LOGIN;
    private String PASSWORD;

    public void manage(HttpServletRequest request) throws ServletException, IOException {

        LOGIN = request.getParameter("login");
        PASSWORD = request.getParameter("password");

        setProperties(CONFIG_PATH);
    }

    private void setProperties(String configPath) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream(configPath));

        properties.setProperty("login", LOGIN);
        properties.setProperty("password", PASSWORD);
    }
}
