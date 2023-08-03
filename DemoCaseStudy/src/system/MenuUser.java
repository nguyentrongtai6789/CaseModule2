package system;

import model.*;
import service.CategoryManage;
import service.ProductManage;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MenuUser {
    Scanner scanner = new Scanner(System.in);
    public static Bill bill;
    public void displayMenuUser() {
        CategoryManage categoryManage = new CategoryManage();
        ProductManage productManage = new ProductManage();
        BillManage billManage = new BillManage();
        int choice;
        do {
            categoryManage.read();
            productManage.read();
            System.out.println("Tên tài khoản: " +  Login.accountUserLogin.getName());
            if (bill.getProductList().isEmpty()) {
                System.out.println("Giỏ hàng của bạn trống.");
            } else {
                System.out.println("Giỏ hàng của bạn gồm có các sản phẩm sau:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : bill.getProductList()) {
                        System.out.printf("%s\n", product);
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###.#");
                String formattedPrice = decimalFormat.format(bill.getTotalMoney());
                System.out.println("Tổng tiền cần thanh toán: " + formattedPrice + " VND");
            }
            System.out.println("Menu:");
            System.out.println("1. Hiện danh sách sản phẩm theo giá từ cao xuống thấp.");
            System.out.println("2. Hiện danh sách sản phẩm theo giá từ thấp lên cao.");
            System.out.println("3. Hiện danh sách sản phẩm theo danh mục.");
            System.out.println("4. Tìm kiếm sản phẩm theo tên.");
            System.out.println("5. Tìm kiếm sản phẩm theo giá.");
            System.out.println("6. Thêm sản phẩm vào giỏ hàng.");
            System.out.println("7. Xoá sản phẩm khỏi giỏ hàng.");
            System.out.println("8. Thanh toán.");
            System.out.println("0. Đăng xuất.");
            System.out.println("Nhập lựa chọn của bạn:");
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 0 && choice != 1 && choice != 2 && choice != 3 &&
                            choice != 4 && choice != 5 && choice != 6 && choice != 7)
                        throw new Exception("Lựa chọn không được hỗ trợ.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (choice) {
                case 1 -> productManage.displayProductByPriceDown();
                case 2 -> productManage.displayProductByPriceUp();
                case 3 -> productManage.displayProductByCategory();
                case 4 -> productManage.searchProductByName();
                case 5 -> productManage.searchProductByPrice();
                case 6 -> billManage.addProductToBill();
            }
        } while (choice != 0);
    }
}
