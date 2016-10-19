import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection conn;

    //static DataBase dataBase = new DataBase();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Flat flat = new Flat();
        try {
            conn = DataBase.initDB();

            while (true) {
                System.out.println("-> 1. Add Client");
                System.out.println("-> 2. View Flats");
                System.out.println("-> 3. Get Flats by parameters");
                System.out.println("-> ");
                String ans = sc.nextLine();

                switch (ans) {
                    case "1": {
                        flat.addFlat(conn, sc);
                        break;
                    }
                    case "2": {
                        flat.viewFlats(conn);
                        break;
                    }
                    case "3": {
                        flat.getFlatByParameters(conn);
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}






























