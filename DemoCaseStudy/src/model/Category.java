package model;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialUID = 1231313L;
    private int id;
    private static int idCategory = 1;
    private String name;

    public Category(String name) {
        this.name = name;
        this.id = idCategory;
        idCategory++;
    }
    public Category(int id, String name) {
        this.id = 0;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void setIdCategory(int idCategory) {
        Category.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-16d %-10s", id, name);
    }
}
