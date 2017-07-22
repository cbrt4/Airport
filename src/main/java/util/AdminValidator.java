package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

public class AdminValidator {

    private final String CONFIG_PATH = "/admin.properties";
    private String LOGIN;
    private String PASSWORD;

    public boolean validate(HttpServletRequest request) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        initProperties(CONFIG_PATH);

        String login = (String) httpSession.getAttribute("login");
        String password = (String) httpSession.getAttribute("password");

        System.out.println("\nLOGIN: " + LOGIN +
                "\nPASSWORD: " + PASSWORD +
                "\nlogin: " + login +
                "\npassword: " + password);

        System.out.println(LOGIN.equals(login));
        System.out.println(PASSWORD.equals(password));

        return LOGIN.equals(login) && PASSWORD.equals(password);
    }

    private void initProperties(String configPath) throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream(configPath));

        LOGIN = properties.getProperty("login");
        PASSWORD = properties.getProperty("password");
    }
}
