package service;

import io.IOFile;
import model.*;

import java.io.*;
import java.util.*;

public class ProductManage implements IOFile<Product>, IProductService {
    private List<Product> productList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String PATH = "D:\\module2\\DemoCaseStudy\\src\\io\\Product";

    public List<Product> getProductList() {
        return productList;
    }

    public void displayProductByPriceDown() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống. ");
        } else {
            Collections.sort(productList);
            System.out.println("Danh sách sản phẩm có giá từ cao xuống thấp như sau:");
            System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                    "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
            for (Product product : productList) {
                System.out.printf("%s\n", product);
            }
        }
    }

    public void displayProductByPriceUp() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            Collections.sort(productList, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return (int) (o1.getPrice() - o2.getPrice());
                }
            });
            System.out.println("Danh sách sản phẩm có giá từ thấp lên cao như sau:");
            System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                    "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
            for (Product product : productList) {
                System.out.printf("%s\n", product);
            }
        }

    }

    public void displayProductByCategory() {
        CategoryManage categoryManage = new CategoryManage();
        categoryManage.read();
        if (categoryManage.getCategoryList().isEmpty()) {
            System.out.println("Danh mục sản phẩm trống, không thể hiển thị theo danh mục.");
        } else if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không có gì để hiển thị.");
        } else {
            for (Category category : categoryManage.getCategoryList()) {
                System.out.println("Danh mục " + category.getName() + " có các sản phẩm như sau:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : productList) {
                    if (product.getCategory().getId() == category.getId()) {
                        System.out.printf("%s\n", product);
                    }
                }
            }
        }
    }

    public void searchProductByName() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            System.out.println("Nhập tên sản phẩm bạn muốn tìm:");
            String name = scanner.nextLine();
            boolean check = false;
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("Các sản phẩm có tên " + name + " như sau:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : productList) {
                    if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                        System.out.printf("%s\n", product);
                    }
                }
            } else {
                System.out.println("Không có sản phẩm nào có tên là " + name);
            }
        }
    }

    public void searchProductByPrice() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            double price;
            while (true) {
                try {
                    System.out.println("Nhập giá của sản phẩm bạn muốn tìm:");
                    price = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                }
            }
            Collections.sort(productList);
            boolean check = false;
            for (Product product : productList) {
                if (product.getPrice() == price) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("Sản phẩm có giá bạn đang tìm là:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : productList) {
                    if (product.getPrice() == price) {
                        System.out.printf("%s\n", product);
                    }
                }
            } else {
                System.out.println("Không có sản phẩm nào có giá bạn đang tìm.");
            }
            boolean check2 = false;
            for (Product product : productList) {
                if (product.getPrice() > price) {
                    check2 = true;
                }
            }
            if (check2) {
                System.out.println("Sản phẩm có giá cao hơn giá bạn đang tìm là:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : productList) {
                    if (product.getPrice() > price) {
                        System.out.printf("%s\n", product);
                    }
                }
            } else {
                System.out.println("Tất cả sản phẩm có giá thấp hơn hoặc bằng giá bạn đang tìm.");
            }
            boolean check3 = false;
            for (Product product : productList) {
                if (product.getPrice() < price) {
                    check3 = true;
                }
            }
            if (check3) {
                System.out.println("Sản phẩm có giá thấp hơn giá bạn đang tìm là:");
                System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                        "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
                for (Product product : productList) {
                    if (product.getPrice() < price) {
                        System.out.printf("%s\n", product);
                    }
                }
            } else {
                System.out.println("Tất cả sản phẩm có giá cao hơn hoặc bằng giá bạn đang tìm.");
            }
        }
    }


    @Override
    public void write(List<Product> productList) {
        File file = new File(PATH);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(productList);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToBillFile(List<Product> products) {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\BillFile");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> readToBillFile() {
        File file = new File("D:\\module2\\DemoCaseStudy\\src\\io\\BillFile");
        List<Product> productsInBill = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            productsInBill = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsInBill;
    }

    @Override
    public void read() {
        File file = new File(PATH);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            productList = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void checkMaxId() {
        if (productList.isEmpty()) {
            Product.setIdProduct(1);
        } else {
            int maxId = productList.get(0).getId();
            for (Product product : productList) {
                if (product.getId() > maxId) {
                    maxId = product.getId();
                }
            }
            maxId++;
            Product.setIdProduct(maxId);
        }
    }

    @Override
    public void add() {
        checkMaxId();
        Product product = new Product();
        System.out.println("Nhập tên của sản phẩm mới:");
        String name = scanner.nextLine();
        product.setName(name);
        System.out.println("Nhập hãng sản xuất của sản phẩm mới:");
        String manufacturer = scanner.nextLine();
        product.setManufacturer(manufacturer);
        double price;
        while (true) {
            try {
                System.out.println("Nhập giá của sản phẩm mới:");
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            }
        }
        product.setPrice(price);
        int quantity;
        while (true) {
            try {
                System.out.println("Nhập số lượng của sản phẩm mới:");
                quantity = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            }
        }
        product.setQuantity(quantity);
        System.out.println("Nhập mô tả của sản phẩm mới:");
        String description = scanner.nextLine();
        product.setDescription(description);
        int idCategory;
        CategoryManage categoryManage = new CategoryManage();
        categoryManage.read();
        if (categoryManage.getCategoryList().isEmpty()) {
            product.setCategory(new Category(0, "Chưa cài đặt danh mục"));
        } else {
            while (true) {
                try {
                    System.out.println("Nhập vào id danh mục của sản phẩm mới.");
                    idCategory = Integer.parseInt(scanner.nextLine());
                    if (idCategory == 0) {
                        product.setCategory(new Category(0, "Chưa cài đặt danh mục"));
                    } else {
                        categoryManage.checkId(idCategory);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            for (Category category : categoryManage.getCategoryList()) {
                if (category.getId() == idCategory) {
                    product.setCategory(category);
                    break;
                }
            }
        }
        productList.add(product);
        write(productList);

    }

    public void checkId(int id) throws Exception {
        boolean check = false;
        for (Product product : productList) {
            if (product.getId() == id) {
                check = true;
                break;
            }
        }
        if (!check) throw new Exception("Id sản phẩm không có trong danh sách.");
    }

    @Override
    public void edit() {
        int id;
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không thể sửa.");
        } else {
            while (true) {
                try {
                    System.out.println("Nhập id của sản phẩm bạn muốn sửa:");
                    id = Integer.parseInt(scanner.nextLine());
                    checkId(id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            for (Product product : productList) {
                if (product.getId() == id) {
                    System.out.println("Nhập tên mới của sản phẩm:");
                    String name = scanner.nextLine();
                    product.setName(name);
                    System.out.println("Nhập hãng sản xuất của sản phẩm:");
                    String manufacturer = scanner.nextLine();
                    double price;
                    while (true) {
                        try {
                            System.out.println("Nhập giá của sản phẩm:");
                            price = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Dữ liệu nhập vào không đúng.");
                        }
                    }
                    product.setPrice(price);
                    int quantity;
                    while (true) {
                        try {
                            System.out.println("Nhập số lượng của sản phẩm:");
                            quantity = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Dữ liệu nhập vào không đúng.");
                        }
                    }
                    product.setQuantity(quantity);
                    System.out.println("Nhập mô tả của sản phẩm:");
                    String description = scanner.nextLine();
                    product.setDescription(description);
                    CategoryManage categoryManage = new CategoryManage();
                    categoryManage.read();
                    if (categoryManage.getCategoryList().isEmpty()) {
                        product.setCategory(new Category(0, "Chưa cài đặt danh mục"));
                    } else {
                        int idCategory;
                        while (true) {
                            try {
                                System.out.println("Nhập id của danh mục sản phẩm:");
                                idCategory = Integer.parseInt(scanner.nextLine());
                                if (idCategory == 0) {
                                    product.setCategory(new Category(0, "Chưa cài đặt danh mục"));
                                } else {
                                    categoryManage.checkId(idCategory);
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Danh");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        for (Category category : categoryManage.getCategoryList()) {
                            if (category.getId() == idCategory) {
                                product.setCategory(category);
                                break;
                            }
                        }
                    }
                    write(productList);
                    System.out.println("Sửa sản phẩm thành công.");
                    break;
                }
            }
        }
    }

    @Override
    public void delete() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không thể xoá.");
        } else {
            int id;
            while (true) {
                try {
                    System.out.println("Nhập id của sản phẩm cần xoá:");
                    id = Integer.parseInt(scanner.nextLine());
                    checkId(id);
                    for (Product product : productList) {
                        if (product.getId() == id) {
                            productList.remove(product);
                            break;
                        }
                    }
                    write(productList);
                    System.out.println("Xoá sản phẩm thành công.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void display() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống, không có gì để hiển thị.");
        } else {
            System.out.println("Danh sách sản phẩm trong cửa hàng gồm có " + productList.size() + " sản phẩm, như sau:");
            System.out.printf("%-17s %-20s %-20s %-17s %-25s %-13s %-17s %-10s\n", "ID SẢN PHẨM", "TÊN", "HÃNG SẢN XUẤT", "GIÁ TIỀN",
                    "SỐ LƯỢNG TRONG KHO", "MÔ TẢ", "ID DANH MỤC", "TÊN DANH MỤC");
            for (Product product : productList) {
                System.out.printf("%s\n", product);
            }
        }
    }

}
