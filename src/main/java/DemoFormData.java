
import com.alibaba.fastjson.JSONObject;

public class DemoFormData {

    public static void main(String[] args) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("outer_project_id", 6161229506514983l);
        String response = SendSmsRequest.createUserCre(
                "http://qatx.qdingnet.com/brake_api/Brake_Machine_Api/get_have_brake_build_id_list_by_project_id/",
                jsonParam
        );

        System.out.println(response);

    }
}
