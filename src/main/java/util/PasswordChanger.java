package util;

import repository.AdminRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PasswordChanger {

    private AdminRepository repository = new AdminRepository();

    public void change(HttpServletRequest request) throws ServletException, IOException {

        repository.getAll().forEach(adminEntity -> {
            if (adminEntity.getLogin().equals(request.getParameter("oldLogin")) &&
                adminEntity.getPassword().equals(request.getParameter("oldPassword"))) {
            adminEntity.setLogin(request.getParameter("newLogin"));
            adminEntity.setPassword(request.getParameter("newPassword"));
            repository.update(adminEntity);
            }
        });
    }
}
