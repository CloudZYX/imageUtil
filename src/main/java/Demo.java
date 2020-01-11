import com.alibaba.druid.support.json.JSONUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

        //post请求
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("outer_project_id", 6161229506514983l);  //参数
        //通过http获取图片信息
        String response = SendSmsRequest.sendSmsByPost(
                "http://qatx.qdingnet.com/brake_api/Brake_Machine_Api/get_have_brake_build_id_list_by_project_id/",
                JSONUtils.toJSONString(params));
        System.out.println(response);
//        byte[] bytes = response.getBytes();

        //获取图片并保存在到文件
//        String httpUrl = "https://img1.qdingnet.com/image-81a4d7b3-56ef-4ca0-aa84-d5a669854893.jpg";
//        String saveUrl = "E:/ZYX/5.jpg";
//        ImageFile.getInputStream(httpUrl);
//        ImageFile.saveImageToDisk(httpUrl,saveUrl);

//        //读取图片，写入到另一个文件
//        try{
//            ImageFile.readImage("E:/ZYX/4.jpg","E:/ZYX/1.jpg");
//            System.out.println("读取图片完成");
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("读取图片失败"+e.getMessage());
//        }
    }


}
