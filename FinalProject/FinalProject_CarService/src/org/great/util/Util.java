package org.great.util;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import org.great.entity.ConnectionsImpl;

public class Util {
	private static Properties properties = new Properties();
	private static String connectionDriver = null;
	private static String jdbcUrl = null;
	private static String connectionUser = null;
	private static String connectionPwd = null;
	private static String webapppWork = null;
	private static int max = 5;
	private static LinkedList<ConnectionsImpl> list = new LinkedList<ConnectionsImpl>();
	private static Util instance = null;
	private Util() {
		
	}

	static {
		try {
			properties.load(Util.class.getClassLoader().getResourceAsStream("db.properties"));
			connectionDriver = properties.getProperty("jdbc.driver");
			jdbcUrl = properties.getProperty("jdbc.url");
			connectionUser = properties.getProperty("jdbc.user");
			connectionPwd = properties.getProperty("jdbc.password");
			webapppWork = properties.getProperty("webapppwork");
			Class.forName(connectionDriver);
			for (int i = 0; i < max; i++) {
				list.add(getConnetions());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(new CleanRun()).start();
	}

	public synchronized static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}

	private static ConnectionsImpl getConnetions() {
		try {
		Connection connection = DriverManager.getConnection(jdbcUrl, connectionUser,connectionPwd);
			return new ConnectionsImpl(connection, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getWebRoot(){
		return webapppWork;
	}

	public synchronized Connection getConnetionMirror() {
		if (list.size() > 0) {
			final ConnectionsImpl connections = list.removeFirst();
			return (Connection) Proxy.newProxyInstance(
					Util.class.getClassLoader(),
					new Class[] { Connection.class }, new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {

							if (!method.getName().equals("close")) {
								return method.invoke(connections.getConnection(), args);
							} else {
								connections.setTime(1);
								list.addFirst(connections);
							}
							return null;
						}
					});
		} else {
			list.add(getConnetions());
			return getConnetionMirror();
		}
	}

	public synchronized void remove() {
		for (int i = 0; i < list.size(); i++) {
			if (!(list.size() > max)) {
				for (int j = 0; j < list.size(); j++) {
					list.get(j).setTime(0);
				}
				break;
			}
			list.get(i).setTime(list.get(i).getTime() + 1);
			if (list.get(i).getTime() > 10) // 空闲超过10分钟则销毁连接
			{
				try {
					list.get(i).getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				list.remove(i);
				i--;
			}
		}
	}
}
