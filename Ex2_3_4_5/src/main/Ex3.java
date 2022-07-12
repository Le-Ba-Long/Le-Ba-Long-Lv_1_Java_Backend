package main;

import java.util.Scanner;

import global.Global;
import thread.ThreadOne;
import thread.ThreadTwo;

public class Ex3 {
	public static final Scanner sc = new Scanner(System.in);
	public static void meNu() {
		System.out.println("--------------------- MENU ---------------------" 
	      + "\n1.Nhập Thông Tin Sinh Viên."
		  + "\n2. Show Danh Sách Sinh Viên" 
	      + "\n3.Thoát Chương Trình.");
	}

	public static void main(String[] args) {
		Thread thread2 = new Thread(new ThreadTwo());
		thread2.start();
		int chose;
		do {

			meNu();
			System.out.println("Mời Bạn Nhập Sự Lựa Chọn");
			chose = sc.nextInt();
			switch (chose) {
			case 1:
             // Mở Coment Ở Thread One Để Dùng  Và Đóng Commet cua Phần Khác Lại 
			    ThreadOne threadOne = new ThreadOne(Global.addStudent(5));
				threadOne.start();

				break;
			case 2:
				Global.showListStudents(5);
				break;
			}

		} while (chose != 0);

	}

}