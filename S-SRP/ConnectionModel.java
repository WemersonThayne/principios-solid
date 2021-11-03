public abstract class ConnectionModel{

	private static final String JDBC = "jdbc:";
    private String dbms;
	private String dbName;
	private String serverName;
	private String portNumber;

	public ConnectionModel(String dbms, String dbName, String serverName, String portNumber){
		this.dbms = dbms;
		this.dbName = dbName;
		this.serverName = serverName;
		this.portNumber = portNumber;
	}

    public String getDbms() {
		return dbms;
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
}