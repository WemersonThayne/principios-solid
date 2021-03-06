
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionDAO {
	
	private Properties connectionProps;
	private Connection conn;

	
	private static final Logger logger = Logger.getLogger(ConnectionDAO.class);
	
	public ConnectionDAO (){
		super();
	}
	
	public ConnectionDAO (String username, String password){
		super();
		connectionProps = new Properties();
	    connectionProps.put("user", username);
	    connectionProps.put("password", password);
	}
	
	public Properties getConnectionProps() {
		return connectionProps;
	}

	public void setConnectionProps(Properties connectionProps) {
		this.connectionProps = connectionProps;
	}
	
	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	
	
	public Connection createConnection() {
		Connection newConnection = null;
        try {
        	
        	if (getDbms().equals("mysql")) {
				newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
				+ "/" + getDbName() + "?useSSL=false", getConnectionProps());
        	}else if (getDbms().equals("postgreSQL")){
    	    	newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber() 
    	    	+ "/" + getDbName() + "?useSSL=false", getConnectionProps());
    	    }else if (getDbms().equals("derby")) {
    	        newConnection = DriverManager.getConnection(JDBC + getDbms() + ":" + getDbName() + ";create=true", getConnectionProps());
    	    }
        	setConnection(newConnection);
        	logger.info("Connected to database");
		} catch (SQLException e) {
			logger.error(e);
		}
	    
	    return newConnection;
	}
}
