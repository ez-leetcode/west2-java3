
import java.util.ArrayList;
//请在connect_mysql类中把数据库路径，用户名，密码修改成自己的再进行测试！！！！！！！！！！！
public class test {
    public static String [] country_list = {"US","China","Japan","United Kingdom"};   //默认测试中国(1)
    public static void main(String[] args) {             //这个API不稳定，没有响应的话，重新运行一下哈~
        int number = 1;                                  //代表查询国家代号  0:US 1:China 2:Japan 3:United Kingdom
        connect_mysql cnn = new connect_mysql();         //建立与mysql的连接
        connect_API ca = new connect_API();              //建立与API的连接
        String s = ca.get_data(country_list[number]);    //从API获取数据返回到字符串中(place是国家，另外三个国家改参数即可)
        System.out.println("--------测试输出从API处获得的未处理的数据-------");
        System.out.println(s);
        System.out.println("------测试从获取数据中提取国家疫情信息并输出------");  //从这个API上获取国家疫情信息不带总更新日期和lat还有country_long
        country c = ca.String_to_Country(s,country_list[number]);          //所以更新日期是null，lat是0，country_long也是0(不信看上一行的数据-_-)
        c.print_information();                                             //之前看github上给的样例写的类，白写了~
        System.out.println("------测试从获取数据中提取城市疫情信息并输出------");
        ArrayList <country_city> c2 = ca.String_to_city(s);
        for(country_city x:c2){
            x.print_information();                                         //输出城市信息
        }

        System.out.println("----------测试向mysql插入国家疫情数据-----------");
        cnn.insert_country_data(c);
        System.out.println("----------测试向mysql查询国家疫情数据-----------");
        cnn.get_data_only_country(c.country_name);                         //获取并输出城市疫情数据
        System.out.println("----------测试向mysql批量插入城市疫情数据--------");
        for(country_city x:c2){
            cnn.insert_city_data(x);                                       //插入疫情数据
        }
        System.out.println("----------测试向mysql查询城市疫情数据-----------");
        cnn.get_data_only_city("Fujian");                         //默认数据是中国，随便查询一个省(更改成别的国家的时候记得修改！！！！)

        System.out.println("---测试数据更新之前请把插入数据部分注释掉(防止重复插入冲突)并再次运行！(在第21行和第32行(空行处)添加“/*”和“*/”！！！)---");
        System.out.println("------------测试更新国家疫情数据---------------");
        cnn.get_data_only_country(country_list[number]);                   //先获取当前数据库中该国家疫情数据
        cnn.refresh_country_data(c);                                       //更新数据库中对应的国家疫情数据
        System.out.println("-------测试向mysql查询更新后的国家疫情数据-------");
        cnn.get_data_only_country(country_list[number]);                   //再次从数据库中获取该国的疫情数据进行对比(API更新频率太慢了，大概率是一样的hhh)
        System.out.println("------------测试更新城市疫情数据---------------");
        cnn.get_data_only_city(c2.get(0).city_name);                       //先获取当前数据库中该城市疫情数据
        cnn.refresh_city_data(c2.get(0));                                  //用API传来的第一个城市信息测试更新
        System.out.println("-------测试向mysql查询更新后的城市疫情数据-------");
        cnn.get_data_only_city(c2.get(0).city_name);                       //查询更新后的城市信息进行对比
        //大概就这么多吧~
    }
}
