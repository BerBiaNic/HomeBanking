package BerBiaNic.homebanking.db;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.LoaderHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {


	private static Connection conn = null;
	private final String DELIMITATOR = System.getProperty("file.separator");
	//private final String PATH_PROPERTIES = "src" + DELIMITATOR + "main" + DELIMITATOR + "resources" + DELIMITATOR + "config.properties";
	private static String DB_HOST;
	private static String DB_USER;
	private static String DB_PASS;
	
	
	public Database() throws IOException {
		Properties p;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream input = loader.getResourceAsStream("config.properties");
		p = new Properties();
		p.load(input);
		
		DB_HOST = p.getProperty("db.host");
		DB_USER = p.getProperty("db.user");
		DB_PASS = p.getProperty("db.password");
	}
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + Database.DB_HOST + ":3306/sql7347775", Database.DB_USER, Database.DB_PASS);
			return conn;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		}
	}
}
