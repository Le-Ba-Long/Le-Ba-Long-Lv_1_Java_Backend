package main;

import java.util.Scanner;

import file.Xfile;
import global.Global;

public class Ex2 {
	private static final Scanner SC = new Scanner(System.in);
	Global global = new Global();

	public static void displayMenu() {
		System.out.println("-------------------------- MENU ------------------------------" 
				+ "\n1.Them Sinh Vien."
				+ "\n2.Hien Thi Danh Sach Sinh Vien Ra Man Hinh Console" 
				+ "\n3.Dung Chuong Trinh.");
	}

	public static void main(String[] args) {
		int chose;
		do {
			displayMenu();
			System.out.println("Moi Ban Nhap Lua Chon: ");
			chose = SC.nextInt();
			SC.nextLine();
			switch (chose) {
			case 1:
				// 5 là số lượng tham số được truyền vào Contructor
				Xfile.writeFile(Global.addStudent(5), 5);
				break;
			case 2:
				Global.showListStudents(6);
				break;
			case 3:
				chose=0;
				break;
			default:
				System.out.println("Bạn Nhập Không Đúng Lựa Chọn Mời Bạn Nhập Lại !");
				break;
			}

		} while (chose != 0);

	}
}
