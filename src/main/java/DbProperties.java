import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Properties;

public class DbProperties {
    private String url;
    private String user;
    private String password;

    public DbProperties() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("C:\\Users\\Andrushka\\IdeaProjects\\jdbcFlats\\src\\main\\resources\\db.properties");

        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        password = props.getProperty("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
