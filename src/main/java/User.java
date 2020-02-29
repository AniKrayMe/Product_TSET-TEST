import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class User {
    private String name;
    private String sureName;
    private String password;

    public User(String name, String sureName, String password) {
        this.name = name;
        this.sureName = sureName;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(sureName, user.sureName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sureName, password);
    }
    public String getBCryptPassword(){
        return BCrypt.hashpw(getPassword(),BCrypt.gensalt());
    }
}
