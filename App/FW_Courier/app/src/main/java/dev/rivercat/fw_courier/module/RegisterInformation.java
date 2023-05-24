package dev.rivercat.fw_courier.module;

public class RegisterInformation {
    String username;
    String password;

    public RegisterInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
