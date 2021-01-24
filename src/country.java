public class country {
    protected String country_name;
    protected int confirmed;
    protected int recovered;
    protected int deaths;
    protected int population;
    protected int sq_km_area;
    protected double life_expectancy;
    protected double elevation_in_meters;
    protected String continent;
    protected String abbreviation;
    protected String location;
    protected int iso;
    protected String capital_city;
    protected double lat;
    protected double country_long;
    protected String updated;
    public country(String country_name,int confirmed,int recovered,int deaths,int population,int sq_km_area,double life_expectancy,double elevation_in_meters,String continent,String abbreviation,String location,int iso,String capital_city,double lat,double country_long,String updated){
        this.country_name = country_name;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.population = population;
        this.sq_km_area = sq_km_area;
        this.life_expectancy = life_expectancy;
        this.elevation_in_meters = elevation_in_meters;
        this.continent = continent;
        this.abbreviation = abbreviation;
        this.location = location;
        this.iso = iso;
        this.capital_city = capital_city;
        this.lat = lat;
        this.country_long = country_long;
        this.updated = updated;
    }
    public void print_information(){
        System.out.println("-----------国家疫情数据如下-------------");
        System.out.println("国家： " + country_name);
        System.out.println("确诊病例： " + confirmed);
        System.out.println("痊愈病例： " + recovered);
        System.out.println("死亡病例： " + deaths);
        System.out.println("总人口： " + population);
        System.out.println("感染率： " + (((double)confirmed)*100/((double)population)) + "%");
        System.out.println("死亡率： " + (((double)deaths)*100/((double)population)) + "%");
        System.out.println("数据更新日期： " + updated);
        System.out.println("------------------------------------");
    }
}
//建表如下
/*CREATE TABLE `covid`.`country_data`  (
  `country_name` varchar(255) NOT NULL,
  `confirmed` int NULL,
  `recovered` int NULL,
  `deaths` int NULL,
  `population` int NULL,
  `sq_km_area` int NULL,
  `life_expectancy` double(255, 8) NULL,
  `elevation_in_meters` double(255, 8) NULL,
  `continent` varchar(255) NULL,
  `abbreviation` varchar(255) NULL,
  `location` varchar(255) NULL,
  `iso` int NULL,
  `capital_city` varchar(255) NULL,
  `lat` double(255, 8) NULL,
  `country_long` double(255, 8) NULL,
  `updated` varchar(255) NULL,
  PRIMARY KEY (`country_name`)
);   */