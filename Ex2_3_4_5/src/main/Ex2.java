package main;

import java.util.Scanner;

import file.Xfile;
import global.Global;
public class Ex2 {
	public static final Scanner sc = new Scanner(System.in);
	Global global = new Global();

	public static void meNu() {
		System.out.println("-------------------------- MENU ------------------------------" 
	            + "\n1.Them Sinh Vien."
				+ "\n2.Hien Thi Danh Sach Sinh Vien Ra Man Hinh Console" 
	            + "\n3.Dung Chuong Trinh.");
	}

	public static void main(String[] args) {
		int chose;
		do {
			meNu();
			System.out.println("Moi Ban Nhap Lua Chon: ");
			chose = sc.nextInt();
			sc.nextLine();
			switch (chose) {
			case 1:
				Xfile.writeFile(Global.addStudent(5),5);
				break;
			case 2:
				Global.showListStudents(5);
				break;
			}
		} while (chose != 0);

	}
}
