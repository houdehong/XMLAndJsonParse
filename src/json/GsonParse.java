package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houdehong
 * @date 2019/5/20
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class GsonParse {
    /**
     * GSON是Google提供的用来在Java对象和JSON数据之间进行映射的Java类库。
     * 可以将一个Json字符转成一个Java对象，或者将一个Java转化为Json字符串。
     * 特点：
     * a、快速、高效
     * b、代码量少、简洁
     * c、面向对象
     * d、数据传递和解析方便
     */

    /**
     * 原理：基于事件驱动
     *
     * 优点：解析方法简单、解析效率高、占存少、灵活性高
     *
     * 使用情境:适用于需要处理大型 JSON文档、JSON文档结构复杂的场合
     */
    public static void main(String[] args) {
        useGsonParser1();
        useGsonParser2();
        useGsonParser3();

    }
    /**
     * Gson解析时TypeToken<T>的泛型参数只能使用时传入确切的类型才能获取正确的Type,
     * 这也是TypeToken设计成抽象类的巧妙之处和原因（改为只有protected构造方法的普通类原理一样）.
     * 一旦将TypeToken改成普通类, 根据上面的分析, 一切类型信息都被擦除, Gson解析将得不到预期的类型.
     */

    /**
     * fromJson(String ,clazz)
     */
    public static void  useGsonParser1(){
        String jsonStr = "{\"name\":\"zhangsan\",\"age\":\"123\",\"sex\":\"man\"}";
        Gson gson=new Gson();
        //把json数据解析成Person1对象
        Student student = gson.fromJson(jsonStr, Student.class);
        System.out.println(student.toString());
    }
    public static void  useGsonParser2(){
        String json2="{\"names\" : [\"jack\", \"rose\", \"jim\"]}";
        Gson gson=new Gson();
        Person2 person2=gson.fromJson(json2,Person2.class);
        List<String> names = person2.getNames();
        for(int i=0;i<names.size();i++){
            System.out.println("getName:"+names.get(i));
        }
    }
    public  static void useGsonParser3(){
        String json3="[{\"name\" : \"jack\", \"age\" : 10},{\"name\" : \"rose\", \"age\" : 11}]";
        Gson gson=new Gson();
        // TypeToken的使用实际上是用来通过泛型获取数据类型
        List<Student> person2=gson.fromJson(json3,new TypeToken<ArrayList<Student>>(){}.getType());
        for(int i=0;i<person2.size();i++){
            //根据i的位置获取JSONObject
            Student stu = person2.get(i);
            String name = stu.getName();
            int age=stu.getAge();
            System.out.println("name:"+name);
            System.out.println("age:"+age);
        }
    }

}
class Person2{

        private List<String> names;

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }
    }
