package com.example.hp.statiosis.MODELS;

public class user {

    private int id;
    private String first_name, second_name, username;
    private String password;
    private  int approval;

    public user(int id, String first_name, String second_name, String username, String password, int approval) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.username = username;
        this.password = password;
        this.approval = approval;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getApproval() {
        return approval;
    }
}
