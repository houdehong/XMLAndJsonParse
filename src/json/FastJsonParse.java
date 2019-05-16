package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author houdehong
 * @date 2019/5/15
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class FastJsonParse {
    /**
     * 1) FastJson���ݴ����ٶȿ죬�������л�����JavaBean����ת����Json��ʽ���ַ�����
     *    �ͷ����л�����JSON��ʽ���ַ���ת��ΪJava Bean���󣩣����ǵ�֮������fast
     * 2) ����ǿ��֧����ͨJDK�࣬����javaBean, Collection, Date ����enum��
     * 3) ��������û�������������κ���⣩
     */
    /**
     * FastJson����JSON��ʽ���ַ����Ľ�����Ҫ���õ������������ࣺ
     *
     * 1�� JSON��FastJson�Ľ�����������JSON��ʽ�ַ�����JSON����JavaBean֮���ת����
     *     Ҳ���������һ���࣬��Ϊ����Դ��֮��ᷢ�֣������������̳���JSON�࣬���кܶ෽����ʵ��Ҳ�ǻ���JSON���е�parse()������
     * 2�� JSONObject�� FastJson�ṩ��json�������ڽ�String����javaBean��Collection�Ƚ���ΪJSON��ʽ�Ķ���
     * 3�� JSONArray�� FastJson�ṩjson�������
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
     * json�ַ���-�򵥶������͵�JSONObject��ת��
     * @param jsonStr
     */
    private static JSONObject testJsonStrToJSON(String jsonStr) {
        //��Json��ʽ���ַ���ת��ΪJSON���������ܰ��ղ㼶��ȡJSON�����е�ֵ
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.getInteger("errno"));
        System.out.println(jsonObject.getJSONObject("data"));
        System.out.println(jsonObject.getString("error"));

        return jsonObject;
    }

    /**
     * JSONObject��json�ַ���-�򵥶����͵�ת��
     * @param jsonObject
     */
    private static void testJsonObjectToJsonStr(JSONObject jsonObject) {
        String jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);
    }

    /**
     *JsonArray��Json�ַ���-�������͵�ת��
     * @param jsonArray
     */
    private static void testJSONArrayToJsonStr(JSONArray jsonArray) {
        String arrStr = JSONArray.toJSONString(jsonArray);
        System.out.println(arrStr);
    }

    /**
     * Json�ַ���-�������͵�JSONArray��ת��
     * @param jsonString
     */
    private static JSONArray testJsonStrToJSONArray(String jsonString) {

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //��ȡdata���ݣ�Ȼ���ȡdata������datastreams�ؼ��ֶ�Ӧ�����飨��[]��ʾ��Ϊ���飩
        JSONArray jsonArray1 = jsonObject.getJSONObject("data").getJSONArray("datastreams");
        //��ȡJsonArray�±��Ӧ��JsonObject
        JSONObject jsonArray2 = jsonArray1.getJSONObject(0);
        //���еĹؼ���datapoints��Ӧ��Ҳ��һ��JsonObject����
        JSONArray jsonArray3 = jsonArray2.getJSONArray("datapoints");
        System.out.println(jsonArray3);

     /*   int size = jsonArray3.size();
        //����1
        for(int i = 0; i < size; i++) {
            System.out.println(jsonArray3.getJSONObject(i));
        }*/

        //����2
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
    //javabeanתjson����
    public static void jsonBenToJsonObject() {
        Student student = new Student();
        student.setName("gzj");
        student.setSex("women");
        student.setAge(18);
        JSONObject jsonObj = (JSONObject) JSON.toJSON(student);
        JSON json = (JSON) JSON.toJSON(student);
        System.out.println("jsonObj"+jsonObj);
        System.out.println("json����"+json);
        //jsonObj{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
        //json����{"parent":"0","organUnitFullName":"testJSON","action":"add","id":"1","suborderNo":"58961","ordinal":8}
    }

}
