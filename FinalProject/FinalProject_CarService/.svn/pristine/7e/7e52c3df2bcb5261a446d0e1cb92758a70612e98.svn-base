package org.great.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

import org.great.entity.ConnectionsImpl;
import org.great.util.Util;

public class Test {
	private static Properties properties = new Properties();
	private static Properties proper = new Properties();
	private static String connectionDriver = null;
	private static String jdbcUrl = null;
	private static String connectionUser = null;
	private static String connectionPwd = null;
	private static int max = 5;
	private static LinkedList<ConnectionsImpl> list = new LinkedList<ConnectionsImpl>();
	private static Util instance = null;
	private static ArrayList<String[]> tableInfo = new ArrayList<String[]>();
	private static boolean shit;
	
	public static void main(String[] args) throws Exception {
		properties.load(Util.class.getClassLoader().getResourceAsStream("db.properties"));
		connectionDriver = properties.getProperty("jdbc.driver");
		jdbcUrl = properties.getProperty("jdbc.url");
		connectionUser = properties.getProperty("jdbc.user");
		connectionPwd = properties.getProperty("jdbc.password");
		Class.forName(connectionDriver);
		Connection connection = DriverManager.getConnection(jdbcUrl, connectionUser,connectionPwd);
		System.out.println(connection);
	}

}
