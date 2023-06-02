package dev.rivercat.mobileAppServer.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;

@Entity
@Table(name = "history")
@DynamicUpdate
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "username")
    private String username;

    @Column(name = "history")
    private ArrayList<String> history;

    @Column(name = "done")
    private boolean isDone;

    public History() {
    }

    public History(String shopName, String username, ArrayList<String> history, boolean isDone) {
        this.shopName = shopName;
        this.username = username;
        this.history = history;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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