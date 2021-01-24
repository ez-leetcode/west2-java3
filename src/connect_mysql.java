import java.sql.*;
//两个建表分别在country类和country_city类下方注释处！！！！！！
public class connect_mysql {
    public static final String driver = "com.mysql.cj.jdbc.Driver";   //启动驱动
    public static final String Url = "jdbc:mysql://localhost:3306/covid";   //数据库链接路径
    public static final String uname = "root";   //数据库用户名
    public static final String keyword = "yqc3585435854";    //数据库连接密码
    public static Connection my_connect = null;
    static {
        try{
            Class.forName(driver);   //加载驱动
            System.out.println("-------驱动加载成功！-----");
            my_connect = (DriverManager.getConnection(Url,uname,keyword));
            if(!my_connect.isClosed()){
                System.out.println("-----数据库链接成功！-----");
            }
        }catch (ClassNotFoundException e){
            System.out.println("------驱动加载失败！-----");
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("-----数据库链接失败！-----");
            e.printStackTrace();
        }
        System.out.println(my_connect);
    }      //连接数据库
    public void refresh_country_data(country c){
        refresh_country_data_Int(c.country_name,c.confirmed,0);
        refresh_country_data_Int(c.country_name,c.recovered,1);
        refresh_country_data_Int(c.country_name,c.deaths,2);
        refresh_country_data_Int(c.country_name, c.population, 3);
        refresh_country_data_Int(c.country_name,c.sq_km_area,4);
        refresh_country_data_Double(c.country_name,c.life_expectancy,0);
        refresh_country_data_Double(c.country_name,c.elevation_in_meters,1);
        refresh_country_data_String(c.country_name,c.continent,0);
        refresh_country_data_String(c.country_name,c.abbreviation,1);
        refresh_country_data_String(c.country_name,c.location,2);
        refresh_country_data_Int(c.country_name,c.iso,5);
        refresh_country_data_String(c.country_name,c.capital_city,3);
        refresh_country_data_Double(c.country_name,c.lat,2);
        refresh_country_data_Double(c.country_name,c.country_long,3);
        refresh_country_data_String(c.country_name,c.updated,4);
        System.out.println("-----------国家疫情数据更新成功！-------------");
    }   //根据解析到的国家信息更新国家疫情数据
    public void refresh_city_data(country_city c){
        refresh_city_data_Double(c.city_name,c.lat);
        refresh_city_data_Int(c.city_name,c.city_long,0);
        refresh_city_data_Int(c.city_name,c.confirmed,1);
        refresh_city_data_Int(c.city_name,c.recovered,2);
        refresh_city_data_Int(c.city_name,c.deaths,3);
        refresh_city_data_String(c.city_name,c.updated);
        System.out.println("------------城市疫情数据更新成功！------------");
    }    //根据解析到的城市信息更新城市疫情数据
    public void refresh_country_data_Int(String country_name,int data,int flag){  //更新修改INT类型数据
        String [] data_name = {"confirmed","recovered","deaths","population","sq_km_area","iso"};  //根据flag获取数据类型
        String s = data_name[flag];
        String sql = "update country_data set " + s + " = ? where country_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,data);
            pst.setString(2,country_name);
            pst.executeUpdate();
        }catch (SQLException e){           //出现异常
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);           //关闭
            DbUtil.close(conn);
        }
    }
    public void refresh_country_data_Double(String country_name,double data,int flag){  //更新修改DOUBLE类型数据
        String [] data_name = {"life_expectancy","elevation_in_meters","lat","country_long"};     //同理
        String s = data_name[flag];
        String sql = "update country_data set "+ s + " = ? where country_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst =  conn.prepareStatement(sql);
            pst.setDouble(1,data);
            pst.setString(2,country_name);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }   //更新Double类型的国家疫情数据
    public void refresh_country_data_String(String country_name,String data,int flag){
        String [] data_name  = {"continent","abbreviation","location","capital_city","updated"};
        String s = data_name[flag];
        String sql = "update country_data set " + s + " = ? where country_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setString(1,data);
            pst.setString(2,country_name);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }   //更新String类型的国家疫情数据
    public void refresh_city_data_Int(String city_name,int data,int flag){
        String [] data_name = {"city_long","confirmed","recovered","deaths"};
        String s = data_name[flag];
        String sql = "update city_data set " + s + " = ? where city_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,data);
            pst.setString(2,city_name);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }      //更新Int类型的城市疫情数据
    public void refresh_city_data_String(String city_name,String data){
        String s = "updated";
        String sql = "update city_data set " + s + " = ? where city_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setString(1,data);
            pst.setString(2,city_name);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }     //更新String类型的城市疫情数据
    public void refresh_city_data_Double(String city_name,double data){
        String s= "lat";
        String sql = "update city_data set " + s + " = ? where city_name = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setDouble(1,data);
            pst.setString(2,city_name);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }    //更新Double类型的城市疫情数据
    public void get_data_only_city(String city_names){
        String sql = "select * from city_data where city_name = ?";
        int city_long = 0, confirmed = 0,recovered = 0,deaths = 0;
        double lat = 0;
        String updated = "a",city_name = "b";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet r;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setString(1,city_names);
            r = pst.executeQuery();
            while(r.next()){
                city_name = r.getString("city_name");
                lat = r.getDouble("lat");
                city_long = r.getInt("city_long");
                confirmed = r.getInt("confirmed");
                recovered = r.getInt("recovered");
                deaths = r.getInt("deaths");
                updated = r.getString("updated");
            }
            country_city c = new country_city(city_name,lat,city_long,confirmed,recovered,deaths,updated);
            c.print_information();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }      //获取城市疫情数据
    public void get_data_only_country(String country_names){
        String sql = "select * from country_data where country_name = ?";
        int confirmed = 0,recovered = 0,deaths = 0,population = 0,sq_km_area = 0,iso = 0;
        double life_expectancy = 0,elevation_in_meters = 0,lat = 0,country_long = 0;
        String country_name = "a",continent = "b",abbreviation = "c",location = "d",capital_city = "e",updated = "f";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet r;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setString(1,country_names);
            r = pst.executeQuery();
            while(r.next()){
                country_name = r.getString("country_name");
                confirmed = r.getInt("confirmed");
                recovered = r.getInt("recovered");
                deaths = r.getInt("deaths");
                population = r.getInt("population");
                sq_km_area = r.getInt("sq_km_area");
                life_expectancy = r.getDouble("life_expectancy");
                elevation_in_meters = r.getDouble("elevation_in_meters");
                continent = r.getString("continent");
                abbreviation = r.getString("abbreviation");
                location = r.getString("location");
                iso = r.getInt("iso");
                capital_city = r.getString("capital_city");
                lat = r.getDouble("lat");
                country_long = r.getDouble("country_long");
                updated = r.getString("updated");
            }
            country c = new country(country_name,confirmed,recovered,deaths,population,sq_km_area,life_expectancy,elevation_in_meters,continent,abbreviation,location,iso,capital_city,lat,country_long,updated);
            c.print_information();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
    }     //获取国家疫情数据
    public void insert_country_data(country c){
    /*  int confirmed,recovered,deaths,population,sq_km_area,iso;
        double life_expectancy,elevation_in_meters,lat,country_long;
        String country_name,continent,abbreviation,location,capital_city,updated;   */
        String sql = "insert into country_data(confirmed,recovered,deaths,population,sq_km_area,iso,life_expectancy,elevation_in_meters,lat,country_long,country_name,continent,abbreviation,location,capital_city,updated) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setInt(1,c.confirmed);
            pst.setInt(2,c.recovered);
            pst.setInt(3,c.deaths);
            pst.setInt(4,c.population);
            pst.setInt(5,c.sq_km_area);
            pst.setInt(6,c.iso);
            pst.setDouble(7,c.life_expectancy);
            pst.setDouble(8,c.elevation_in_meters);
            pst.setDouble(9,c.lat);
            pst.setDouble(10,c.country_long);
            pst.setString(11,c.country_name);
            pst.setString(12,c.continent);
            pst.setString(13,c.abbreviation);
            pst.setString(14,c.location);
            pst.setString(15,c.capital_city);
            pst.setString(16,c.updated);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
        System.out.println("------------国家疫情数据插入成功！--------------");
        c.print_information();
    }       //向mysql增加国家疫情数据
    public void insert_city_data(country_city c){
     /* String city_name,updated;
        int city_long,recovered,confirmed,deaths;
        double lat; */
        String sql = "insert into city_data(city_name,updated,city_long,recovered,confirmed,deaths,lat) values(?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pst = null;
        try{
            conn = DriverManager.getConnection(Url,uname,keyword);
            pst = conn.prepareStatement(sql);
            pst.setString(1,c.city_name);
            pst.setString(2,c.updated);
            pst.setInt(3,c.city_long);
            pst.setInt(4,c.recovered);
            pst.setInt(5,c.confirmed);
            pst.setInt(6,c.deaths);
            pst.setDouble(7,c.lat);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.close(pst);
            DbUtil.close(conn);
        }
        System.out.println("------------城市疫情数据插入成功！--------------");
        c.print_information();
    }       //向mysql增加城市疫情数据
}