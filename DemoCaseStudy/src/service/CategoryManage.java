package service;

import io.IOFile;
import model.Category;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManage implements ICategoryService, IOFile<Category> {
    private List<Category> categoryList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String PATH = "D:\\module2\\DemoCaseStudy\\src\\io\\Category";
    private Category category1 = new Category("Điện thoại di động");
    private Category category2 = new Category("Máy tính bảng");
    private Category category3 = new Category("Máy tính xách tay");
    private Category category4 = new Category("Tai nghe bluetooth");

    public void setCategoryList() {
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        write(categoryList);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public void write(List<Category> categoryList) {
        File file = new File(PATH);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(categoryList);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        try {
            File file = new File(PATH);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            categoryList = (List<Category>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkMaxId() {
        if (categoryList.isEmpty()) {
            Category.setIdCategory(1);
        } else {
            int id = categoryList.get(0).getId();
            for (Category category : categoryList) {
                if (category.getId() > id) {
                    id = category.getId();
                }
            }
            id++;
            Category.setIdCategory(id);
        }
    }

    @Override
    public void add() {
        System.out.println("Nhập tên mới của danh mục");
        String name = scanner.nextLine();
        checkMaxId();
        Category category = new Category(name);
        categoryList.add(category);
        write(categoryList);
        System.out.println("Thêm danh mục thành công.");
    }

    public void checkId(int id) throws Exception {
        boolean check = false;
        for (Category category : categoryList) {
            if (category.getId() == id) {
                check = true;
                break;
            }
        }
        if (!check) throw new Exception("Id danh mục không có trong danh sách.");
    }

    @Override
    public void edit() {
        int id = 0;
        if (categoryList.isEmpty()) {
            System.out.println("Danh mục sản phẩm trống, không thể sửa.");
        } else {
            while (true) {
                try {
                    System.out.println("Nhập id của category bạn muốn sửa:");
                    id = Integer.parseInt(scanner.nextLine());
                    checkId(id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            for (Category category : categoryList) {
                if (category.getId() == id) {
                    System.out.println("Nhập tên mới của danh mục:");
                    String name = scanner.nextLine();
                    category.setName(name);
                    write(categoryList);
                    ProductManage productManage = new ProductManage();
                    productManage.read();
                    for (Product product : productManage.getProductList()) {
                        if (product.getCategory().getId() == id) {
                            product.setCategory(category);
                        }
                    }
                    productManage.write(productManage.getProductList());
                    System.out.println("Sửa danh mục thành công.");
                    break;
                }
            }
        }
    }

    @Override
    public void delete() {
        do {
            if (categoryList.isEmpty()) {
                System.out.println("Danh mục sản phẩm trống, không thể xoá.");
                break;
            }
            int id = 0;
            while (true) {
                try {
                    System.out.println("Nhập id của danh mục bạn muốn xoá:");
                    id = Integer.parseInt(scanner.nextLine());
                    checkId(id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            for (Category category : categoryList) {
                if (category.getId() == id) {
                    categoryList.remove(category);
                    write(categoryList);
                    ProductManage productManage = new ProductManage();
                    productManage.read();
                    for (Product product : productManage.getProductList()) {
                        if (product.getCategory().getId() == id) {
                            product.setCategory(new Category(0, "Chưa cài đặt danh mục"));
                        }
                    }
                    productManage.write(productManage.getProductList());
                    System.out.println("Xoá danh mục thành công.");
                    break;
                }
            }
            break;
        } while (!categoryList.isEmpty());
    }

    @Override
    public void display() {
        if (categoryList.isEmpty()) {
            System.out.println("Danh mục sản phẩm trống, không có gì để hiển thị.");
        } else {
            System.out.println("Cửa hàng có " + categoryList.size() + " danh mục sản phẩm như sau:");
            System.out.printf("%-17s %-10s\n", "ID DANH MỤC", "TÊN DANH MỤC");
            for (Category category : categoryList) {
                System.out.printf("%s\n", category);
            }
        }
    }

}
