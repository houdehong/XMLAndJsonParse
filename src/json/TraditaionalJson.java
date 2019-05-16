package json;

import net.sf.json.JSONObject;

import java.util.Set;

/**
 * @author houdehong
 * @date 2019/5/14
 * @org 北京信安世纪科技股份有限公司
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class TraditaionalJson {
    /**
     * 官方的json解析
     */
    public static void main(String[] args) {
        String jsonStr = "{\"name\":\"zhangsan\",\"id\":\"123\",\"age\":\"18\"}";
        String jsonString = createJsonString("name", "zhangsan");
        System.out.println(jsonString);
        String2JsonObject(jsonStr);
        getValue(jsonStr,"id");
        getKeys(jsonStr);
    }
    public static String createJsonString(String key, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }
    private static void String2JsonObject(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        System.out.println(jsonObject);
    }
    private static void getValue(String json,String key){
        JSONObject jsonObject = JSONObject.fromObject(json);
        System.out.println(jsonObject);
        String id = (String) jsonObject.get(key);
        System.out.println(id);
    }
    private static void getKeys(String json){
        JSONObject jsonObject = JSONObject.fromObject(json);
        jsonObject.element("sex","man");
        System.out.println(jsonObject);
        Set set = jsonObject.entrySet();
        if(set.size()>0){
            for(Object str: set){
                System.out.println(str);
            }
        }
    }
}
