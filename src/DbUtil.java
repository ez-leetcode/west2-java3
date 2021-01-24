import java.sql.*;

public class DbUtil {
    public static void close(PreparedStatement pst){
        if(pst!=null){
            try {
                pst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection c){
        if(c!=null){
            try{
                c.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
