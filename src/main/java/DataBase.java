import java.sql.*;

public class DataBase {
    static final String DB_URL = "jdbc:mysql://localhost:3306/jdbcFlats";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "pass";
    static Connection conn;
    //1private static DbProperties props = new DbProperties();

    public static Connection initDB() throws SQLException {
      //1 conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword());
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE if EXISTS FLATS");
            st.execute("Create TABLE FLATS(id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY, region VARCHAR(20), " +
                    "address VARCHAR(40), square FLOAT, rooms TINYINT, price INT) ");
        } finally {
            st.close();
        }
        return conn;
    }
}
