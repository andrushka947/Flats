import java.sql.*;
import java.util.Scanner;

public class Flat {
    private String region;
    private String address;
    private double square;
    private byte rooms;
    private int price;

    public Flat() {}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public byte getRooms() {
        return rooms;
    }

    public void setRooms(byte rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void addFlat(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter the region:");
        String region = sc.nextLine();
        System.out.println("Enter the address:");
        String address = sc.nextLine();
        System.out.println("Enter the square:");
        double square = Double.parseDouble(sc.nextLine());
        System.out.println("Enter room count:");
        byte rooms = Byte.parseByte(sc.nextLine());
        System.out.println("Enter the price:");
        int price = Integer.parseInt(sc.nextLine());

        PreparedStatement ps = conn.prepareStatement("INSERT INTO FLATS (region, address, square, rooms, price) VALUES (?,?,?,?,?)");

        try {
            ps.setString(1, region);
            ps.setString(2, address);
            ps.setDouble(3, square);
            ps.setByte(4, rooms);
            ps.setInt(5, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }

    }

    public void viewFlats(Connection conn) throws SQLException {
        String query = "SELECT * FROM FLATS";
        getResult(conn, query);
    }

    public static void getFlatByParameters(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose \"AND\" or \"OR\" statement");
        String statement = sc.nextLine();
        if (statement.equalsIgnoreCase("AND")) {
            statement = "and";
        } else if (statement.equalsIgnoreCase("OR")) {
            statement = "or";
        }
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Flats WHERE ");

        boolean flag = true;
        while (flag == true) {
            System.out.println("select parameters u want to use for search in order from 1 to 5:");
            System.out.println("1.region");
            System.out.println("2.address");
            System.out.println("3.square");
            System.out.println("4.room");
            System.out.println("5.price");
            System.out.println("0 to execute");
            System.out.print("->");

            String ans = sc.nextLine();
            if (ans.contains("1")) {
                System.out.println("Enter the value for region");
                query.append("region = \"" + sc.nextLine() + "\" " + statement + " ");
            }
            if (ans.contains("2")) {
                System.out.println("Enter the value for address");
                query.append("address = \"" + sc.nextLine() + "\" " + statement + " ");
            }
            if (ans.contains("3")) {
                System.out.println("Enter the value for square");
                query.append("square = \"" + sc.nextLine() + "\" " + statement + " ");
            }
            if (ans.contains("4")) {
                System.out.println("Enter the value for rooms");
                query.append("rooms = \"" + sc.nextLine() + "\" " + statement + " ");
            }
            if (ans.contains("5")) {
                System.out.println("Enter the value for price");
                query.append("price = \"" + sc.nextLine() + "\" " + statement + " ");
            }
            if (ans.contains("0")) {
                flag = false;
            }
        }
        String res = query.toString();
        if (statement.equals("and")) {
            if (res.substring(res.length() - 5, res.length()).equals(" and ")) {
                res = res.substring(0, res.length() - 5) + ";";
                System.out.println(res);
            } else {
                System.out.println("Wrong \"AND\" sql query");
            }
        } else if (statement.equals("or")) {
            if (res.substring(res.length() - 4, res.length()).equals(" or ")) {
                res = res.substring(0, res.length() - 4) + ";";
                System.out.println(res);
            } else {
                System.out.println("Wrong \"OR\" sql query");
            }
        }
        getResult(conn, res);

    }

    public static void getResult(Connection conn, String query) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query.toString());
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                if (rs != null) {
                    for (int i = 1; i < md.getColumnCount() + 1; i++) {
                        System.out.print(md.getColumnName(i) + "\t\t");
                    }
                    System.out.println();
                    System.out.println("_______________________________________________________________________");

                    while (rs.next()) {
                        for (int i = 1; i < md.getColumnCount() + 1; i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("No results found(");
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

}
