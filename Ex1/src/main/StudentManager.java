package main;

import java.util.Scanner;

import global.Global;

public class StudentManager {
	private static final Scanner sc = new Scanner(System.in);
	public static void displayMenu() {
		System.out.println(
				"\n---------------------------------------   MENU   ---------------------------------------------"
						+ "\n\t1.Nhap Danh Sach Hoc Vien."
						+ "\n\t2.Xuat Danh Sach Hoc Vien."
						+ "\n\t3.Tim Kiem Hoc Sinh Theo Khoang Diem Nhap Tu Ban Phim."
						+ "\n\t4.Tim Kiem Hoc Vien Theo Hoc Luc Nhap Tu Ban Phim."
						+ "\n\t5.Tim Hoc Vien Theo Ma So Va Cap Nhap."
						+ "\n\t6.Xap Xep Hoc Vien Theo Diem."
						+ "\n\t7.Xuat 5 Hoc Vien Co Diem Cao Nhat." 
						+ "\n\t8.Tinh Diem Trung Binh Cua Lop."
						+ "\n\t9.Xuat Danh Sach Hoc Vien Co Diem Tren Diem Trung Binh Cua Lop."
						+ "\n\t10.Tong Hop So Hoc Vien Theo Hoc Luc" 
						+ "\n\t11.Nhan 0 De Thoat Chuong Trinh.");

	}

	public static void main(String[] args) {
		int chose;
		Global global = new Global();
		do {
			displayMenu();
			System.out.println("Moi Ban Nhap Lua Chon: ");
			chose = sc.nextInt();
			sc.nextLine();

			switch (chose) {
			case 1:
				global.inputStudent("");
				break;
			case 2:
				System.out.print("Danh Sách Sinh Viên Đọc Từ File : ");
				global.outputStudent();
				break;
			case 3:
				global.findByAboutPoint();
				break;
			case 4:
				global.findByStudentRank();
				break;
			case 5:
				global.findCodeAndUpdateStudent();
				break;
			case 6:
				global.sortStudentByPoint("Sap Xep Sinh Vien Theo Diem Giam Dan: ");

				break;
			case 7:
				global.outputStudentLimitFiveMaxPoint();
				break;
			case 8:
				global.calculatePointAVG();
				break;
			case 9:
				global.outputStudentOnPointAVG();
				break;
			case 10:
				global.syntheticStudentGroupbyRank();
				break;
			case 11:
				global.outputStudent();
				break;
				
			default:
				System.out.println("Bạn Nhập Không Đúng Lựa Chọn Mời Bạn Nhập Lại !");
				break;

			}
			

		} while (chose != 0);

	}

}
