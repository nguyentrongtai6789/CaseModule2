package service;

import model.Student;

import java.io.*;
import java.nio.channels.ClosedSelectorException;
import java.util.*;

public class StudentManage {
    private List<Student> studentList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private Student student1 = new Student(1, "Quang Anh", 20, "Nam", "Nghệ An", 7.5);
    private Student student2 = new Student(2, "Hoàn", 20, "Nam", "Nghệ An", 7.5);
    private Student student3 = new Student(3, "Tuyên", 20, "Nam", "Nghệ An", 7.5);
    private Student student4 = new Student(4, "Chiến", 20, "Nam", "Nghệ An", 7.5);
    private Student student5 = new Student(5, "Đông", 20, "Nam", "Nghệ An", 7.5);
    private Student student6 = new Student(6, "Trung", 20, "Nam", "Nghệ An", 7.5);

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList() {
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        writeToFile(studentList);
    }

    public void readFormFile() {
        try {
            File file = new File("src\\io\\StudentFile");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            studentList = (List<Student>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeToFile(List<Student> list) {
        try {
            File file = new File("src\\io\\StudentFile");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayStudentList() {
        if (studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên trống");
        } else {
            System.out.println("Danh sách sinh viên gồm có " + studentList.size() + " sinh viên, như sau:");
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println((i + 1) + ". " + studentList.get(i).toString());
            }
            System.out.println("---...---");
        }
    }

    private void checkIdExistInList(int id) throws Exception {
        for (Student student : studentList) {
            if (student.getId() == id) {
                throw new Exception("Mã sinh viên đã tồn tại.");
            }
        }
    }

    public void addStudent() {
        Student student = new Student();
        while (true) {
            try {
                System.out.println("Nhập mã sinh viên mới:");
                int id = Integer.parseInt(scanner.nextLine());
                if (id < 1) throw new Exception("Mã sinh viên phải lớn hơn 1.");
                checkIdExistInList(id);
                student.setId(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Nhập họ tên sinh viên mới:");
        String name = scanner.nextLine();
        student.setName(name);
        while (true) {
            try {
                System.out.println("Nhập tuổi sinh viên mới:");
                int age = Integer.parseInt(scanner.nextLine());
                if (age < 18) throw new Exception("Tuổi của sinh viên phải lớn hơn 18");
                student.setId(age);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Nhập giới tính sinh viên mới:");
        String gender = scanner.nextLine();
        student.setName(gender);
        System.out.println("Nhập địa chỉ của sinh viên mới:");
        String address = scanner.nextLine();
        student.setAddress(address);
        while (true) {
            try {
                System.out.println("Nhập điểm trung bình của sinh viên mới:");
                double score = Double.parseDouble(scanner.nextLine());
                if (score < 0) throw new Exception("Điểm phải lớn hơn 0.");
                student.setMediumScore(score);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        studentList.add(student);
        writeToFile(studentList);
        System.out.println("Thêm sinh viên thành công!");
    }

    private void checkIdNoExistInList(int id) throws Exception {
        boolean check = false;
        for (Student student : studentList) {
            if (student.getId() == id) {
                check = true;
                break;
            }
        }
        if (!check) throw new Exception("Không tìm được sinh viên với mã sinh viên trên.");
    }

    public void updateStudent() {
        String input = null;
        Student studentNew = new Student();
        int id;
        while (true) {
            try {
                System.out.println("Nhập vào mã sinh viên:");
                id = Integer.parseInt(scanner.nextLine());
                input = scanner.nextLine();
                checkIdNoExistInList(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Nhập mã mới của sinh viên:");
                int idNew = Integer.parseInt(scanner.nextLine());
                checkIdExistInList(idNew);
                studentNew.setId(idNew);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Nhập tên mới của sinh viên:");
        String name = scanner.nextLine();
        studentNew.setName(name);
        while (true) {
            try {
                System.out.println("Nhập tuổi mới của sinh viên:");
                int age = Integer.parseInt(scanner.nextLine());
                if (age < 18) throw new Exception("Tuổi của sinh viên phải lớn hơn 18");
                studentNew.setAge(age);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Nhập địa chỉ mới của sinh viên:");
        String address = scanner.nextLine();
        studentNew.setAddress(address);
        while (true) {
            try {
                System.out.println("Nhập điểm trung bình của sinh viên:");
                double score = Double.parseDouble(scanner.nextLine());
                if (score < 0) throw new Exception("Điểm phải lớn hơn 0.");
                studentNew.setMediumScore(score);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                break;
            }
        }
        studentList.add(studentNew);
        writeToFile(studentList);
        System.out.println("Sửa thông tinh sinh viên thành công!");
        System.out.println("---...---");
    }

    public void deleteStudent() {
        int id;
        while (true) {
            try {
                System.out.println("Nhập vào mã của sinh viên cần xoá.");
                id = Integer.parseInt(scanner.nextLine());
                checkIdNoExistInList(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu nhập vào không đúng.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                break;
            }
        }
        writeToFile(studentList);
        System.out.println("Xoá sinh viên thành công.");
    }

    public void arrangeStudent() {
        int choice;
        do {
            System.out.println("--- Sắp xếp sinh viên theo điểm trung bình ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Sắp xếp điểm trung bình tăng dần");
            System.out.println("1. Sắp xếp điểm trung bình giảm dần");
            System.out.println("3. Thoát");
            System.out.println("Chọn chức năng;");
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng.");
                }
            }
            switch (choice) {
                case 1:
                    Collections.sort(studentList, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            return (int) (o1.getMediumScore() - o2.getMediumScore());
                        }
                    });
                    System.out.println("Danh sách sinh viên có điểm từ thấp lên cao như sau:");
                    for (Student student : studentList) {
                        System.out.println(student);
                    }
                    break;
                case 2:
                    Collections.sort(studentList, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            return (int) (- o1.getMediumScore() + o2.getMediumScore());
                        }
                    });
                    System.out.println("Danh sách sinh viên có điểm từ cao xuống thấp như sau:");
                    for (Student student : studentList) {
                        System.out.println(student);
                    }
                    break;
            }
        } while (choice != 3);
    }

}
