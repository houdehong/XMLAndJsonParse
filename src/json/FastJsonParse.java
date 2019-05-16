package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author houdehong
 * @date 2019/5/15
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class FastJsonParse {
    /**
     * 1) FastJson数据处理速度快，无论序列化（把JavaBean对象转化成Json格式的字符串）
     *    和反序列化（把JSON格式的字符串转化为Java Bean对象），都是当之无愧的fast
     * 2) 功能强大（支持普通JDK类，包括javaBean, Collection, Date 或者enum）
     * 3) 零依赖（没有依赖其他的任何类库）
     */
    /**
     * FastJson对于JSON格式的字符串的解析主要是用到了下面三个类：
     *
     * 1） JSON：FastJson的解析器，用于JSON格式字符串与JSON对象及JavaBean之间的转化。
     *     也是最基础的一个类，因为看过源码之后会发现，下面的两个类继承了JSON类，其中很多方法的实现也是基于JSON类中的parse()方法。
     * 2） JSONObject： FastJson提供的json对象，用于将String对象、javaBean、Collection等解析为JSON格式的对象
     * 3） JSONArray： FastJson提供json数组对象
     */
    public static void main(String[] args) {
        String jsonString = "{\"errno\":0,\"data\":{\"count\":6,\"datastreams\":[{\"datapoints\":" +
                "[{\"at\":\"2018-05-01 15:33:25.000\",\"value\":{\"lon\":\"104.07803838511\"," +
                "\"lat\":\"30.633662874767996\"}},{\"at\":\"2018-04-29 17:37:25.000\",\"value\":" +
                "{\"lon\":\"106.54504673673999\",\"lat\":\"29.555934913502\"}},{\"at\":" +
                "\"2018-04-29 17:28:47.000\",\"value\":{\"lon\":\"106.5450423966\",\"lat\":" +
                "\"29.555937892982\"}},{\"at\":\"2018-04-29 17:08:25.000\",\"value\":{\"lon\":\"106.54504104044\"," +
                "\"lat\":\"29.555938434163\"}},{\"at\":\"2018-04-29 17:03:28.000\",\"value\":" +
                "{\"lon\":\"104.07765307713001\",\"lat\":\"30.634041802113998\"}}," +
                "{\"at\":\"2018-04-26 15:02:20.000\",\"value\":{\"lon\":\"104.07796817860999\",\"lat\":" +
                "\"30.633655490910996\"}}],\"id\":\"GPS\"}]},\"error\":\"succ\"}";
        //JSONObject jsonObject = testJsonStrToJSON(jsonString);
       // testJsonObjectToJsonStr(jsonObject);
        //JSONArray jsonArray = testJsonStrToJSONArray(jsonString);
        //testJSONArrayToJsonStr(jsonArray);
        parseJavaBean();
        jsonBenToJsonObject();
    }

    /**
     * json字符串-简单对象类型到JSONObject的转换
     * @param jsonStr
     */
    private static JSONObject testJsonStrToJSON(String jsonStr) {
        //将Json格式的字符串转化为JSON，这样才能按照层级读取JSON数据中的值
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.getInteger("errno"));
        System.out.println(jsonObject.getJSONObject("data"));
        System.out.println(jsonObject.getString("error"));

        return jsonObject;
    }

    /**
     * JSONObject到json字符串-简单对象型的转换
     * @param jsonObject
     */
    private static void testJsonObjectToJsonStr(JSONObject jsonObject) {
        String jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);
    }

    /**
     *JsonArray与Json字符串-数组类型的转化
     * @param jsonArray
     */
    private static void testJSONArrayToJsonStr(JSONArray jsonArray) {
        String arrStr = JSONArray.toJSONString(jsonArray);
        System.out.println(arrStr);
    }

    /**
     * Json字符串-数组类型到JSONArray的转换
     * @param jsonString
     */
    private static JSONArray testJsonStrToJSONArray(String jsonString) {

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //获取data数据，然后读取data数据中datastreams关键字对应的数组（有[]标示的为数组）
        JSONArray jsonArray1 = jsonObject.getJSONObject("data").getJSONArray("datastreams");
        //获取JsonArray下标对应的JsonObject
        JSONObject jsonArray2 = jsonArray1.getJSONObject(0);
        //其中的关键字datapoints对应的也是一个JsonObject数组
        JSONArray jsonArray3 = jsonArray2.getJSONArray("datapoints");
        System.out.println(jsonArray3);

     /*   int size = jsonArray3.size();
        //遍历1
        for(int i = 0; i < size; i++) {
            System.out.println(jsonArray3.getJSONObject(i));
        }*/

        //遍历2
        for(Object object: jsonArray3){
            System.out.println(object);
        }
        return jsonArray3;
    }

    private static void parseJavaBean(){
        String jsonStr = "{\"name\":\"zhangsan\",\"age\":\"123\",\"sex\":\"man\"}";
        Student student = JSON.parseObject(jsonStr, Student.class);
        System.out.println(student.getSex());
        System.out.println(student.getAge());
    }
    //javabean转json对象
    public static void jsonBenToJsonObject() {
        Student student = new Student();
        student.setName("gzj");
        student.setSex("women");
        student.setAge(18);
        JSONObject jsonObj = (JSONObject) JSON.toJSON(student);
        JSON json = (JSON) JSON.toJSON(student);
        System.out.println("jsonObj"+jsonObj);
        System.out.println("json对象"+json);
        //jsonObj{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
        //json对象{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
    }

}
