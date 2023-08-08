package model;

import service.StudentManage;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        int choice;
        do {
            StudentManage studentManage = new StudentManage();
            studentManage.readFormFile();
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ----");
            System.out.println("Chọn chức năng theo chỉ số (để tiếp tục)");
            System.out.println("1. Xem danh sách sinh viên");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xoá");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");
            while (true) {
                try {
                    System.out.println("Chọn chức năng:");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 &&
                            choice != 7 && choice != 8) throw new Exception("Lựa chọn không được hỗ trợ!");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu nhập vào không đúng!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (choice) {
                case 1:
                    studentManage.displayStudentList();
                    break;
                case 2:
                    studentManage.addStudent();
                    break;
                case 3:
                    studentManage.updateStudent();
                    break;
                case 4:
                    studentManage.deleteStudent();
                    break;
                case 5:
                    studentManage.arrangeStudent();
                    break;
            }
        } while (choice != 8);
    }
}
