package application;

import db.DBConnection;

public class Program {

	public static void main(String[] args) {
		
		DBConnection.getConnection();
		
		DBConnection.closeConnection();
	}

}
