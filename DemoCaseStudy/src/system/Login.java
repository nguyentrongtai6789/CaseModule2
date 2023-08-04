package system;

import model.*;
import service.AccountAdminManage;
import service.AccountUserManage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    public static AccountUser accountUserLogin;
    public void displayLogin() {
        int choice;
        while (true) {
            while (true) {
                try {
                    System.out.println("Nhập lựa chọn của bạn:");
                    System.out.println("1. Quản lí cửa hàng");
                    System.out.println("2. Người mua hàng.");
                    System.out.println("0. Thoát.");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 1 && choice != 2 && choice != 0)
                        throw new Exception("Lựa chọn không được hỗ trợ.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> userLogin();
                case 0 -> System.exit(0);
            }
        }
    }

    private void adminLogin() {
        while (true) {
            try {
                checkAccountAdmin();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        MenuAdmin menuAdmin = new MenuAdmin();
        menuAdmin.displayMenuAdmin();
    }

    private void userLogin() {
        while (true) {
            try {
                checkAccountUser();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        MenuUser menuUser = new MenuUser();
        List<Product> products = new ArrayList<>();
        MenuUser.bill = new Bill(products, 0.0);
        menuUser.displayMenuUser();
    }

    private void checkAccountUser() throws Exception {
        System.out.println("Nhập tên tài khoản người dùng:");
        String name = scanner.nextLine();
        System.out.println("Nhập mật khẩu:");
        String passWord = scanner.nextLine();
        AccountUserManage accountUserManage = new AccountUserManage();
        accountUserManage.setAccountUserList();
        accountUserManage.read();
        boolean check = false;
        for (AccountUser accountUser : accountUserManage.getAccountUserList()) {
            if (accountUser.getName().equals(name) && accountUser.getPassWord().equals(passWord)) {
                check = true;
                accountUserLogin = accountUser;
                System.out.println("Đăng nhập với tài khoản người dùng " + name + " thành công.");
                break;
            }
        }
        if (!check) throw new Exception("Tài khoản hoặc mật khẩu không đúng.");
    }

    private void checkAccountAdmin() throws Exception {
        System.out.println("Nhập tên tài khoản quản lí:");
        String name = scanner.nextLine();
        System.out.println("Nhập mật khẩu:");
        String passWord = scanner.nextLine();
        AccountAdminManage accountAdminManage = new AccountAdminManage();
        accountAdminManage.setAccountAdminList();
        accountAdminManage.read();
        boolean check = false;
        for (AccountAdmin accountAdmin : accountAdminManage.getAccountAdminList()) {
            if (accountAdmin.getName().equals(name) && accountAdmin.getPassWord().equals(passWord)) {
                check = true;
                System.out.println("Đăng nhập với tài khoản quản lí " + name + " thành công.");
                break;
            }
        }
        if (!check) throw new Exception("Tài khoản hoặc mật khẩu không đúng.");
    }
}
