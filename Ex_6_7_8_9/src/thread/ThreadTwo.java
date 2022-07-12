package thread;

import org.apache.log4j.Logger;

import file.Xfile;

public class ThreadTwo implements Runnable {
	 public static volatile Logger logger = Logger.getLogger(ThreadTwo .class.getName());
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Số Lượng Sinh Viên Đọc Từ File Là : " + Xfile.readFile().size());
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.error(e);
			}

		}
	}

}
