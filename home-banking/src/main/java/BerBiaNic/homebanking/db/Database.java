package BerBiaNic.homebanking.db;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 
 * @authors Antonino Bertuccio, Giuseppe Bianchino, Giovanni Nicotera
 *
 */
public class Database {


	private final static String DELIMITATOR = System.getProperty("file.separator");
	private final static String PATH_PROPERTIES = "src" + DELIMITATOR + "main" + DELIMITATOR + "resources" + DELIMITATOR + "config.properties";
	private static String DB_HOST;
	private static String DB_USER;
	private static String DB_PASS;


	public Database() throws IOException {

	}

	public static Connection getConnection() {

		Connection conn = null;
		try {
			Properties p;
			InputStream input = new FileInputStream(PATH_PROPERTIES);
			p = new Properties();
			try {
				p.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			DB_HOST = p.getProperty("db.host");
			DB_USER = p.getProperty("db.user");
			DB_PASS = p.getProperty("db.password");
			conn = DriverManager.getConnection("jdbc:mysql://" + Database.DB_HOST + ":3306/sql7347775", Database.DB_USER, Database.DB_PASS);
//			conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7347775?user=sql7347775&password=Pp6ZDM3sQi");
			return conn;
		}catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
