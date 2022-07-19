package thread;

import org.apache.log4j.Logger;

import file.Xfile;

public class ThreadTwo implements Runnable {
	private static volatile Logger LOGGER = Logger.getLogger(ThreadTwo.class.getName());

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Số Lượng Sinh Viên Đọc Từ File Là : " + Xfile.readFile(6).size());
				Thread.sleep(5000);
			} catch (Exception e) {
				 LOGGER.error(e);
			}

		}
	}

}
