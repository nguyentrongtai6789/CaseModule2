package service;

import model.Product;
import system.MenuUser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillManage {
    Scanner scanner = new Scanner(System.in);

    public boolean checkQuantity(int id) {
        ProductManage productManage = new ProductManage();
        productManage.read();
        boolean check = false;
        for (Product product : productManage.getProductList()) {
            if (product.getQuantity() == 0 && product.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean checkQuantityInShop(int id) {
        ProductManage productManage = new ProductManage();
        productManage.read();
        List<Product> billProductList = MenuUser.bill.getProductList();
        boolean check = false;
        if (billProductList.isEmpty()) {
            for (Product product : productManage.getProductList()) {
                if (product.getId() == id && product.getQuantity() == 0) {
                    check = true;
                    break;
                }
            }
        } else {
            for (Product product : productManage.getProductList()) {
                if (product.getId() == id) {
                    for (Product product1 : billProductList) {
                        if (product1.getId() == id) {
                            if (product.getQuantity() <= product1.getQuantity()) {
                                check = true;
                                break;
                            }
                            break;
                        }
                    }
                    break;
                }

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
                System.out.println("Sản phẩm trong kho đã hết.");
            } else if (checkQuantityInShop(id)) {
                System.out.println("Số lượng sản phẩm thêm vào giỏ hàng là tối đa.");
            } else {
                List<Product> billProductList = MenuUser.bill.getProductList();
                double totalMoney = MenuUser.bill.getTotalMoney();
                Product productAddToBill = new Product();
                List<Product> productList = productManage.getProductList();
                for (Product product : productList) {
                    if (product.getId() == id) {
                        totalMoney += product.getPrice();
                        productAddToBill = product;
                        break;
                    }
                }
                productManage.write(productList);
                if (billProductList.isEmpty()) {
                    productAddToBill.setQuantity(1);
                    billProductList.add(productAddToBill);
                } else {
                    boolean check = false;
                    for (Product product : billProductList) {
                        if (product.getId() == productAddToBill.getId()) {
                            check = true;
                            break;
                        }
                    }
                    if (check) {
                        for (Product product : billProductList) {
                            if (product.getId() == productAddToBill.getId()) {
                                int quantityInBill = product.getQuantity();
                                quantityInBill++;
                                product.setQuantity(quantityInBill);
                                break;
                            }
                        }
                    } else {
                        productAddToBill.setQuantity(1);
                        billProductList.add(productAddToBill);
                    }
                }
                MenuUser.bill.setProductList(billProductList);
                MenuUser.bill.setTotalMoney(totalMoney);
                System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
            }
        }
    }

    public void checkIdInBill(int id) throws Exception {
        List<Product> billProductList = MenuUser.bill.getProductList();
        boolean check = false;
        for (Product product : billProductList) {
            if (product.getId() == id) {
                check = true;
                break;
            }
        }
        if (!check) throw new Exception("Sản phẩm không có trong giỏ hàng.");
    }


    public void deleteProductInBill() {
        List<Product> billProductList = MenuUser.bill.getProductList();
        double totalMoney = MenuUser.bill.getTotalMoney();
        if (billProductList.isEmpty()) {
            System.out.println("Giỏ hàng của bạn trống.");
        } else {
            int id;
            while (true) {
                try {
                    System.out.println("Nhập vào id của sản phẩm bạn cần xoá:");
                    id = Integer.parseInt(scanner.nextLine());
                    checkIdInBill(id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            for (Product product : billProductList) {
                if (product.getId() == id) {
                    int quantity = product.getQuantity() - 1;
                    product.setQuantity(quantity);
                    totalMoney -= product.getPrice();
                    break;
                }
            }
            for (Product product : billProductList) {
                if (product.getId() == id) {
                    if (product.getQuantity() == 0) {
                        billProductList.remove(product);
                        break;
                    }
                }
            }
            MenuUser.bill.setProductList(billProductList);
            MenuUser.bill.setTotalMoney(totalMoney);
        }
    }
    public void payBill() {
        List<Product> billProductList = MenuUser.bill.getProductList();
        ProductManage productManage = new ProductManage();
        productManage.read();
        double totalMoney = MenuUser.bill.getTotalMoney();
        if (billProductList.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            List<Product> productList = productManage.getProductList();
            for (Product product: billProductList) {
                for (Product product1: productList) {
                    if (product.getId() == product1.getId()) {
                        int quantity1 = product.getQuantity();
                        int quantity2 = product1.getQuantity();
                        product1.setQuantity(quantity2 - quantity1);
                        break;
                    }
                }
            }
            List<Product> products = new ArrayList<>();
            MenuUser.bill.setProductList(products);
            MenuUser.bill.setTotalMoney(0);
            System.out.println("Bạn đã thanh toán thành công hoá đơn sau: ");
            System.out.printf("%-17s %-20s %-20s %-17s %-30s %-13s %-17s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                    "SỐ LƯỢNG TRONG GIỎ HÀNG", "MÔ TẢ", "TÊN DANH MỤC");
            for (Product product : billProductList) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###.#");
                String formattedPrice = decimalFormat.format(product.getPrice());
                System.out.printf("%-15s %-20s %-17s %-15s %-26s %-13s %-17s\n", product.getId(), product.getName(),
                        product.getManufacturer(), formattedPrice, product.getQuantity(), product.getDescription(),
                        product.getCategory().getName());
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###.#");
            String formattedPrice = decimalFormat.format(totalMoney);
            System.out.println("Tổng tiền cần thanh toán: " + formattedPrice + " VND");
            productManage.write(productList);
            System.out.println("Cảm ơn bạn!");
        }
    }
}