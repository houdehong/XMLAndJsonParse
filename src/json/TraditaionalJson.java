package json;

import net.sf.json.JSONObject;

/**
 * @author houdehong
 * @date 2019/5/14
 * @org �����Ű����ͿƼ��ɷ����޹�˾
 * @description
 * @since NetSignServer_5.5.40.12_patch
 */
public class TraditaionalJson {
    public static void main(String[] args) {
        String jsonStr = "{\"name\":\"zhangsan\",\"id\":\"123\",\"age\":\"18\"}";
        String jsonString = createJsonString("name", "zhangsan");
        System.out.println(jsonString);
    }
    public static String createJsonString(String key, Object value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject.toString();
    }

}
