package model;

import java.io.Serializable;
import java.util.List;

public class AccountAdmin implements Serializable {
    private static final long serialUID = 12321413L;
    private String name;
    private String passWord;
    public AccountAdmin() {
    }

    public AccountAdmin(String name, String passWord) {
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
}
