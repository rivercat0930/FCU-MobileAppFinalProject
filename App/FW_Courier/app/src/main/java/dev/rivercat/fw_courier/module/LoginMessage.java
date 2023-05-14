package dev.rivercat.fw_courier.module;

public class LoginMessage {
    String username;
    String password;

    public LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
