package banking;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Database {
    private String path;
    private Connection conn;

    public Database(String pathName){
        this.path = pathName;
        File db = new File(pathName);
        try{
            db.createNewFile();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        this.createDatabase();
    }


    public void createDatabase(){
        String url = "jdbc:sqlite:" + this.path;
        //System.out.println(url);
        try  {
            Connection newConnection = DriverManager.getConnection(url);
            this.conn = newConnection;
            if (this.conn != null) {
                DatabaseMetaData meta = conn.getMetaData();

                Statement statement = conn.createStatement();

                //table check - see if it exists or not

                String sqlStatement = "CREATE TABLE IF NOT EXISTS card ("
                        + "id INTEGER AUTO_INCREMENT,"
                        + "number TEXT,"
                        + "pin TEXT,"
                        + "balance INTEGER DEFAULT 0"
                        + ");";


                statement.execute(sqlStatement);



            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String number, String pin){
        String sql = "INSERT INTO card(number,pin) VALUES(?,?)";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
                    pstmt.setString(1, number);
                    pstmt.setString(2, pin);
                    pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNumber(String number, String pin){
        String sql = "SELECT number,pin from card WHERE number == ? AND pin == ?";
        try{
            PreparedStatement prepState = this.conn.prepareStatement(sql);
            prepState.setString(1,number);
            prepState.setString(2,pin);

            ResultSet rs = prepState.executeQuery();
            return rs.getString("number") + "\t" + rs.getString("pin");

        } catch (Exception e){
            //System.out.println(e.getMessage());
            return "";
        }

    }

    public String getBalance(String number){
        try{
            String sql = "SELECT balance FROM card WHERE number == ?";
            PreparedStatement prepState = this.conn.prepareStatement(sql);
            prepState.setString(1,number);


            ResultSet rs = prepState.executeQuery();
            return rs.getString("balance");
        } catch (Exception e){
            return "";
        }

    }
    public void addIncome(String number, int income){
        String sql = "UPDATE card SET balance = ?" +
                      "WHERE number = ?";

        try{
            int newBalance = Integer.valueOf(getBalance(number)) + income;
            PreparedStatement pstmt = this.conn.prepareStatement(sql);

            //set corresponding param
            pstmt.setInt(1,newBalance);
            pstmt.setString(2,number);


            //update
            pstmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public boolean cardExists(String cardNum){
        String sql = "SELECT balance FROM card WHERE number == ?";
        try{
            PreparedStatement prepState = this.conn.prepareStatement(sql);
            prepState.setString(1,cardNum);
            ResultSet rs = prepState.executeQuery();


            return rs.next();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void deleteCard(String cardNum, String pin){
        String sql = "DELETE FROM card WHERE number = ? AND pin = ?";
        try {
            PreparedStatement prepState = this.conn.prepareStatement(sql);
            prepState.setString(1,cardNum);
            prepState.setString(2,pin);

            prepState.executeUpdate();


        } catch(Exception e){
            System.out.println(e.getMessage());
        }



    }

    public void close(){
        try{
            this.conn.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


}




