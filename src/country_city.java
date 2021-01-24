public class country_city {
    protected String city_name;
    protected double lat;
    protected int city_long;
    protected int confirmed;
    protected int recovered;
    protected int deaths;
    protected String updated;
    public country_city(String city_name,double lat,int city_long,int confirmed,int recovered,int deaths,String updated){
        this.city_name = city_name;
        this.lat = lat;
        this.city_long = city_long;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.updated = updated;
    }
    public void print_information(){
        System.out.println("-----------城市疫情数据如下-----------");
        System.out.println("城市： " + city_name);
        System.out.println("确诊病例： " + confirmed);
        System.out.println("痊愈病例： " + recovered);
        System.out.println("死亡病例： " + deaths);
        System.out.println("数据更新日期： " + updated);
        System.out.println("-----------------------------------");
    }
}
//建表如下
/*CREATE TABLE `covid`.`city_data`  (
        `city_name` varchar(255) NOT NULL,
        `lat` double(255, 8) NULL,
        `city_long` int NULL,
        `confirmed` int NULL,
        `recovered` int NULL,
        `deaths` int NULL,
        `updated` varchar(255) NULL,
        PRIMARY KEY (`city_name`)
        );   */