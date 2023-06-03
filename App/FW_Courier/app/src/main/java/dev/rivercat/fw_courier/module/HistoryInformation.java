package dev.rivercat.fw_courier.module;

import java.util.ArrayList;

public class HistoryInformation {
    private String shopName;
    private String username;
    private ArrayList<String> history;

    public HistoryInformation(String shopName, String username, ArrayList<String> history) {
        this.shopName = shopName;
        this.username = username;
        this.history = history;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }
}
