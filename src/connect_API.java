import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class connect_API {
    public static final String API_address = "https://covid-api.mmediagroup.fr/v1/cases?country="; //API链接
    public String get_data(String place){         //从API处获取数据
        StringBuilder s = new StringBuilder();
        try{
            URL urlObject = new URL(API_address + place);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String input;
            while((input = in.readLine()) != null){
                s.append(input);
            }
            in.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("连接失败！");
        }
        return s.toString();
    }
    public country String_to_Country(String s,String country){          //从输入数据中解析出国家疫情信息
      /*int confirmed,recovered,deaths,population,sq_km_area,iso;
        double life_expectancy,elevation_in_meters,lat,country_long;
        String country_name,continent,abbreviation,location,capital_city,updated; */
        String ck = "";
        int pos = s.indexOf('}');
        ck += s.substring(8,pos+1);
        country c = JSONObject.parseObject(ck,country.class);
        c.country_name = country;        //国家名字设置（前面写类的时候名称不一样，要自己加上）
        return c;
    }
    public ArrayList <country_city> String_to_city(String s){         //从输入数据中解析出各个城市疫情信息存入Arraylist中返回
        int pos2,pos3;
        int point = s.indexOf('}');
        ArrayList <country_city> city_s = new ArrayList<>();
        while(point+2<s.length()){
         // String temp = s.substring(point);      //大概算法就是先获取城市名字，再找到下一个右括号
            point += 4 ;
            String temp1 = s.substring(point);
            pos2 = temp1.indexOf('"');
            String city = temp1.substring(0,pos2);
            pos3 = temp1.indexOf('}');
            point += pos3;
            String temp2 = temp1.substring(pos2+3,pos3+1);
            country_city c1 = JSONObject.parseObject(temp2,country_city.class);  //解析成city
            c1.city_name = city;
            city_s.add(c1);         //放到数组中
        }
        return city_s;
    }
}