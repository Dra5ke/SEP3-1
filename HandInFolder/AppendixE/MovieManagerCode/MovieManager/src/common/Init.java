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
	private Scanner scanner;
	
	private Init() {

	}

	
	/**
	 * Init object that is created if it does not already exist
	 * @return instance 
	 */
	public static Init getInstance() {

		if (instance == null) {
			instance = new Init();
		}
		return instance;
	}

	/**
	 * Method gets port used
	 * @return Integer of port number
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Method sets port number
	 * @param Port number
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Method gets the port number of database
	 * @return Integer of database port number
	 */
	public int getPortDb() {
		return portDb;
	}

	/**
	 * Method sets port number of database
	 * @param Integer of database port number	
	 */
	public void setPortDb(int port) {
		this.portDb = port;
	}

	
	/**
	 * Method gets Ip used
	 * @return String with IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Method sets Ip used
	 * @param String with IP
	 */
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * Method gets the IP of database
	 * @return String with databse IP
	 */
	public String getIpDb() {
		return ipDb;
	}

	/**
	 * Method sets IP of database
	 * @param String of database I{
	 */
	public void setIpDb(String ip) {
		this.ipDb = ip;
	}

	
	/**
	 * Method receives file with IP, port, database IP, database port and sets values for the class fields
	 */
	public void getData() {
		try {
			scanner = new Scanner(initData);
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
