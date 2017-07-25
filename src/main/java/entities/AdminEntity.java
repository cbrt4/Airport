package entities;

import javax.persistence.*;

@Entity
@Table(name = "admin", schema = "airport")
public class AdminEntity {

    private int id;
    private String login;
    private String password;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminEntity)) return false;

        AdminEntity that = (AdminEntity) o;

        if (getId() != that.getId()) return false;
        if (!getLogin().equals(that.getLogin())) return false;
        return getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nid=" + id +
                "\nlogin: " + login +
                "\npassword: " + password;
    }
}
