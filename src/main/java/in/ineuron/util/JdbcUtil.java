package in.ineuron.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;


public class JdbcUtil {

	private JdbcUtil() {
	}

	static {
		// Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
//		return physicalConnection();
		
//		String fileLoc = "D:\\Java iNeuron\\2nd Feb- WODA, Request Object, MVC Design\\JDBCCRUDApp\\src\\main\\java\\in\\ineuron\\properties\\application.properties";
//		HikariConfig hikariConfig = new HikariConfig(fileLoc);
//		
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return physicalConnection();
		
	}

	private static Connection physicalConnection() throws FileNotFoundException, IOException, SQLException {
		String fileLoc = "D:\\Java iNeuron\\28th Feb CRUD APP with Servlet, JDBC, JSTL\\MVCCRUDAPP-JDBC-Servlet-JSTL\\src\\main\\java\\in\\ineuron\\properties\\application.properties";
		FileInputStream fis = new FileInputStream(fileLoc);
		Properties properties = new Properties();
		properties.load(fis);

		String url = properties.getProperty("jdbcUrl");
		String username = properties.getProperty("user");
		String password = properties.getProperty("password");

		return DriverManager.getConnection(url, username, password);
	}

}
