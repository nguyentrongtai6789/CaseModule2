package model;

import java.io.Serializable;

public class AccountUser implements Serializable {
    private String name;
    private String passWord;

    private static final long serialUID = 287348732L;

    public AccountUser() {
    }

    public AccountUser(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
