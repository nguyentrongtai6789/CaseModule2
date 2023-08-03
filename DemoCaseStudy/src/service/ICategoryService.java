package service;

import model.Category;

public interface ICategoryService extends IGenerateService<Category> {
    @Override
    void add();

    @Override
    void edit();

    @Override
    void delete();

    @Override
    void display();

}
