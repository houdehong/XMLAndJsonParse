package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houdehong
 * @date 2019/5/20
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class GsonParse {
    /**
     * GSON��Google�ṩ��������Java�����JSON����֮�����ӳ���Java��⡣
     * ���Խ�һ��Json�ַ�ת��һ��Java���󣬻��߽�һ��Javaת��ΪJson�ַ�����
     * �ص㣺
     * a�����١���Ч
     * b���������١����
     * c���������
     * d�����ݴ��ݺͽ�������
     */

    /**
     * ԭ�������¼�����
     *
     * �ŵ㣺���������򵥡�����Ч�ʸߡ�ռ���١�����Ը�
     *
     * ʹ���龳:��������Ҫ������� JSON�ĵ���JSON�ĵ��ṹ���ӵĳ���
     */
    public static void main(String[] args) {
        useGsonParser1();
        useGsonParser2();
        useGsonParser3();

    }
    /**
     * Gson����ʱTypeToken<T>�ķ��Ͳ���ֻ��ʹ��ʱ����ȷ�е����Ͳ��ܻ�ȡ��ȷ��Type,
     * ��Ҳ��TypeToken��Ƴɳ����������֮����ԭ�򣨸�Ϊֻ��protected���췽������ͨ��ԭ��һ����.
     * һ����TypeToken�ĳ���ͨ��, ��������ķ���, һ��������Ϣ��������, Gson�������ò���Ԥ�ڵ�����.
     */

    /**
     * fromJson(String ,clazz)
     */
    public static void  useGsonParser1(){
        String jsonStr = "{\"name\":\"zhangsan\",\"age\":\"123\",\"sex\":\"man\"}";
        Gson gson=new Gson();
        //��json���ݽ�����Person1����
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
        // TypeToken��ʹ��ʵ����������ͨ�����ͻ�ȡ��������
        List<Student> person2=gson.fromJson(json3,new TypeToken<ArrayList<Student>>(){}.getType());
        for(int i=0;i<person2.size();i++){
            //����i��λ�û�ȡJSONObject
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
