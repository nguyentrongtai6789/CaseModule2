package model;

import io.IOFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountAdminManage implements IOFile<AccountAdmin> {
    private List<AccountAdmin> accountAdminList = new ArrayList<>();
    private AccountAdmin admin1 = new AccountAdmin("admin1", "6789");
    private AccountAdmin admin2 = new AccountAdmin("admin2", "6789");
    private AccountAdmin admin3 = new AccountAdmin("admin3", "6789");
    private AccountAdmin admin4 = new AccountAdmin("admin4", "6789");
    public void setAccountAdminList() {
        accountAdminList.add(admin1);
        accountAdminList.add(admin2);
        accountAdminList.add(admin3);
        accountAdminList.add(admin4);
        write(accountAdminList);
    }

    public List<AccountAdmin> getAccountAdminList() {
        return accountAdminList;
    }

    @Override
    public void write(List<AccountAdmin> list) {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\AdminAccount");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\AdminAccount");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            accountAdminList = (List<AccountAdmin>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
