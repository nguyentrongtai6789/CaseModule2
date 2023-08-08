package model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialUID = 345342434L;
    private int id;
    private String name;
    private int age;
    private String gender;
    private String Address;
    private double mediumScore;

    public Student() {
    }

    public Student(int id, String name, int age, String gender, String address, double mediumScore) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        Address = address;
        this.mediumScore = mediumScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(double mediumScore) {
        this.mediumScore = mediumScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", Address='" + Address + '\'' +
                ", mediumScore=" + mediumScore +
                '}';
    }
}
