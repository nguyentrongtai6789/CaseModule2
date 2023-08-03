package service;

import model.Product;
import system.MenuUser;

import java.util.List;
import java.util.Scanner;

public class BillManage {
    Scanner scanner = new Scanner(System.in);

    public boolean checkQuantity(int id) {
        ProductManage productManage = new ProductManage();
        productManage.read();
        boolean check = false;
        for (Product product : productManage.getProductList()) {
            if (product.getId() == id) {
                if (product.getQuantity() == 0) {
                    check = true;
                    break;
                }
                break;
            }
        }
        return check;
    }

    public void addProductToBill() {
        ProductManage productManage = new ProductManage();
        productManage.read();
        if (productManage.getProductList().isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            int id;
            while (true) {
                try {
                    System.out.println("Nhập id sản phẩm bạn muốn thêm vào giỏ hàng:");
                    id = Integer.parseInt(scanner.nextLine());
                    productManage.checkId(id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
            if (checkQuantity(id)) {
                System.out.println("Số lượng sản phẩm trong cửa hàng đã hết.");
            } else {
                // danh sách sản phẩm trong giỏ hàng, ban đầu là trống
                List<Product> billProductList = MenuUser.bill.getProductList();
                // tổng tiền trong giỏ hàng
                double totalMoney = MenuUser.bill.getTotalMoney();
                // khởi tạo sản phẩm để thêm vào giỏ hàng
                Product productAddToBill = new Product();
                List<Product> productList = productManage.getProductList();
                for (Product product : productList) {
                    if (product.getId() == id) {
                        int quantity = product.getQuantity();
                        product.setQuantity(quantity - 1);
                        totalMoney += product.getPrice();
                        // sản phẩm thêm vào giỏ là sản phẩm trùng id trong danh sách
                        productAddToBill = product;
                        break;
                    }
                }
                productManage.write(productList);
                if (billProductList.isEmpty()) {
                    // nếu giỏ hàng trống thì cài đặt số lượng là 1 và thêm vào
                    productAddToBill.setQuantity(1);
                    billProductList.add(productAddToBill);
                } else {
                    // nếu giỏ hàng không trống
                    boolean check = false;
                    for (Product product : billProductList) {
                        // kiểm tra xem sản phẩm thêm vào có trong giỏ hàng hay chưa:
                        if (product.getId() == productAddToBill.getId()) {
                            check = true;
                            break;
                        }
                    }
                    if (check) {
                        // nếu sản phẩm có trong giỏ hàng"
                        for (Product product : billProductList) {
                            if (product.getId() == productAddToBill.getId()) {
                                // lấy số lượng đang có trong giỏ hàng:
                                int quantityInBill = product.getQuantity();
                                quantityInBill++;
                                // cộng thêm 1
                                product.setQuantity(quantityInBill);
                                break;
                            }
                        }
                    } else {
                        //nếu sản phẩm chưa có trong giỏ:
                        productAddToBill.setQuantity(1); // cài đặt số lượng là 1
                        billProductList.add(productAddToBill); // thêm vào giỏ
                    }
                }
                MenuUser.bill.setProductList(billProductList);
                MenuUser.bill.setTotalMoney(totalMoney);
                System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
            }
        }
    }

    public void deleteProductInBill() {
        List<Product> products = MenuUser.bill.getProductList();
        ProductManage productManage = new ProductManage();
        productManage.read();
        int id, quantity;
        while (true) {
            try {
                System.out.println("Nhập id sản phẩm bạn muốn xoá khỏi giỏ hàng:");
                id = Integer.parseInt(scanner.nextLine());
                productManage.checkId(id);
                System.out.println("Nhập số lượng bạn muốn xoá:");
                quantity = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        double totalMoney = MenuUser.bill.getTotalMoney();
        Product productDeleteInBill = new Product();
        for (Product product : products) {

        }
    }
}