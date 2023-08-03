package service;

import model.Product;

public interface IProductService extends IGenerateService<Product> {
    @Override
    void add();

    @Override
    void edit();

    @Override
    void delete();

    @Override
    void display();

    @Override
    default void checkMaxId() {

    }
}
