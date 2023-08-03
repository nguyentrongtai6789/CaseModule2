package service;

import io.IOFile;
import model.AccountUser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUserManage implements IOFile<AccountUser> {
    private List<AccountUser> accountUserList = new ArrayList<>();
    private AccountUser user1 = new AccountUser("user1", "6789");
    private AccountUser user2 = new AccountUser("user2", "6789");
    private AccountUser user3 = new AccountUser("user3", "6789");
    private AccountUser user4 = new AccountUser("user4", "6789");
    private AccountUser accountUserLogin;

    public void setAccountUserList() {
        accountUserList.add(user1);
        accountUserList.add(user2);
        accountUserList.add(user3);
        accountUserList.add(user4);
        write(accountUserList);
    }

    public List<AccountUser> getAccountUserList() {
        return accountUserList;
    }

    @Override
    public void write(List<AccountUser> accountUsers) {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\UserAccount");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accountUsers);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\UserAccount");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            accountUserList = (List<AccountUser>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
