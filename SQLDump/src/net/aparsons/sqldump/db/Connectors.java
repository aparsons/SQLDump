package net.aparsons.sqldump.db;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Connectors {

	public enum Connector {
		ORACLE("oracle.jdbc.driver.OracleDriver"),
		MYSQL("com.mysql.jdbc.Driver");
		
		private final String value;
		
		private Connector(final String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}
	
	public static void load(Connector c) throws ClassNotFoundException {
		Logger.getLogger(Connectors.class.getName()).log(Level.INFO, "Loading database driver [" + c.value + "]");
		Class.forName(c.value);
	}

}
