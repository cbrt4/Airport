package util;

import repository.AdminRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminValidator {

    private AdminRepository repository = new AdminRepository();

    public boolean validate(HttpServletRequest request) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        final boolean[] isValid = {false};

        repository.getAll().forEach(adminEntity -> {
            if (adminEntity.getLogin().equals(httpSession.getAttribute("login")) &&
                    adminEntity.getPassword().equals(httpSession.getAttribute("password")))
                isValid[0] = true;
        });

        return isValid[0];
    }
}
