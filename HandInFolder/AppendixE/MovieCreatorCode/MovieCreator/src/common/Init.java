package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {
	private static Init instance;
	private int port;
	private int portDb;
	private String ipDb;
	private String ip;
	//Both tiers use the same txt file. When the applications will be deployed, each tier will have its own.
	private File initData = new File("initData.txt");
	
	private Init() {

	}

	public static Init getInstance() {

		if (instance == null) {
			instance = new Init();
		}
		return instance;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPortDb() {
		return portDb;
	}

	public void setPortDb(int port) {
		this.portDb = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIpDb() {
		return ipDb;
	}

	public void setIpDb(String ip) {
		this.ipDb = ip;
	}

	public void getData() {
		try {
			Scanner scanner = new Scanner(initData);
			String[] initData = scanner.nextLine().split(" ");
			Init.getInstance().setIp(initData[0]);
			Init.getInstance().setPort(Integer.parseInt(initData[1]));
			Init.getInstance().setIpDb(initData[2]);
			Init.getInstance().setPortDb(Integer.parseInt(initData[3]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
