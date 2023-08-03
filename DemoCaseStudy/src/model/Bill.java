package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {
    private static final long serialUID = 23432434L;
    private List<Product> productList = new ArrayList<>();
    private double totalMoney;

    public Bill(List<Product> productList, double totalMoney) {
        this.productList = productList;
        this.totalMoney = totalMoney;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
