package dev.rivercat.mobileAppServer.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "history")
    private ArrayList<String> history;

    public History() {
    }

    public History(String username, ArrayList<String> history) {
        this.username = username;
        this.history = history;
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
