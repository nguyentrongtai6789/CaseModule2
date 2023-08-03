package model;

import service.ProductManage;
import system.MenuUser;

import java.util.List;
import java.util.Scanner;

public class BillManage {
    Scanner scanner = new Scanner(System.in);

    public void addProductToBill() {
        ProductManage productManage = new ProductManage();
        productManage.read();
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
        List<Product> products = MenuUser.bill.getProductList();
        double totalMoney = MenuUser.bill.getTotalMoney();
        Product productAddToBill = new Product();
        for (Product product : productManage.getProductList()) {
            if (product.getId() == id) {
                totalMoney += product.getPrice();
                productAddToBill = product;
                break;
            }
        }
        if (products.isEmpty()) {
            productAddToBill.setQuantity(1);
            products.add(productAddToBill);
        } else {
            boolean check = false;
            for (Product product : products) {
                if (product.getId() == productAddToBill.getId()) {
                    check = true;
                    break;
                }
            }
            if (check) {
                for (Product product : products) {
                    int quantityInBill = product.getQuantity();
                    quantityInBill++;
                    product.setQuantity(quantityInBill);
                    break;
                }
            } else {
                productAddToBill.setQuantity(1);
                products.add(productAddToBill);
            }
        }
        MenuUser.bill.setProductList(products);
        MenuUser.bill.setTotalMoney(totalMoney);
        System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
    }
}