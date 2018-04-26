package org.great.entity;

public class ConnectionsImpl {

	private java.sql.Connection connection ;
	private int time;
	
	public ConnectionsImpl(java.sql.Connection connection, int time) {
		super();
		this.connection = connection;
		this.time = time;
	}
	public java.sql.Connection getConnection() {
		return connection;
	}
	public void setConnection(java.sql.Connection connection) {
		this.connection = connection;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	

}
