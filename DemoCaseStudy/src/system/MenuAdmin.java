package system;

import service.CategoryManage;
import service.ProductManage;

import java.util.Scanner;

public class MenuAdmin {
    Scanner scanner = new Scanner(System.in);

    public void displayMenuAdmin() {
        CategoryManage categoryManage = new CategoryManage();
        ProductManage productManage = new ProductManage();
        int choice;
        do {
            categoryManage.read();
            productManage.read();
            productManage.display();
            categoryManage.display();
            System.out.println("Menu:");
            System.out.println("1. Thêm danh mục sản phẩm.");
            System.out.println("2. Sửa danh mục sản phẩm.");
            System.out.println("3. Xoá danh mục sản phẩm.");
            System.out.println("4. Thêm sản phẩm.");
            System.out.println("5. Sửa sản phẩm.");
            System.out.println("6. Xoá sản phẩm.");
            System.out.println("0. Đăng xuất.");
            while (true) {
                try {
                    System.out.println("Nhập lựa chọn của bạn:");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 0 && choice != 1 && choice != 2 && choice != 3 &&
                            choice != 4 && choice != 5 && choice != 6)
                        throw new Exception("Lựa chọn không được hỗ trợ.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (choice) {
                case 1:
                    categoryManage.add();
                    break;
                case 2:
                    categoryManage.edit();
                    break;
                case 3:
                    categoryManage.delete();
                    break;
                case 4:
                    productManage.add();
                    break;
                case 5:
                    productManage.edit();
                    break;
                case 6:
                    productManage.delete();
                    break;
            }
        } while (choice != 0);

    }
}
