import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendSmsRequest {

    public static String sendSmsByGet(String path, String postContent){
        return sendSms(path,postContent,"GET");
    }

    public static String sendSmsByPost(String path, String postContent){
        return sendSms(path,postContent,"POST");
    }

    public static String sendSms(String path, String postContent, String method) {

        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method);// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
            if (method.equals("POST")){
                // 发送POST请求必须设置如下两行
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                printWriter.write(postContent);
                printWriter.flush();

                httpURLConnection.connect();
                OutputStream os=httpURLConnection.getOutputStream();
                os.write(postContent.getBytes("UTF-8"));
                os.flush();
            }

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createUserCre(String url, JSONObject jsonParam) {
//        String url = "http://admin:11a8ff57440f35baead7a3cc8a21ec2c44@172.16.91.121:8888/jenkins/credentials/store/system/domain/_/createCredentials?";
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

//        JSONObject credentialsJsonParam = new JSONObject();
//
//        credentialsJsonParam.put("scope", "GLOBAL");
//        //注意，如果ID一样的话，插入失败
//        credentialsJsonParam.put("id", id);
//        credentialsJsonParam.put("username", "abc520");
//        credentialsJsonParam.put("password", "123456");
//        credentialsJsonParam.put("description", "hello world jenkins hellow");
//        credentialsJsonParam.put("$class", "com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl");
//
//        jsonParam.put("credentials", credentialsJsonParam);
//        jsonParam.put("", "0");

        System.out.println("请求参数为:\t" + jsonParam.toString());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("json", jsonParam.toString(), ContentType.MULTIPART_FORM_DATA);
        builder.addTextBody("outer_project_id", "6161229506514983");

        HttpEntity multipart = builder.build();

        HttpResponse resp = null;
        try {
            httpPost.setEntity(multipart);
            resp = client.execute(httpPost);

            if (resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                System.out.println("返回测试参数");
                respContent = EntityUtils.toString(he, "UTF-8");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("返回结果为:\t" + respContent);
        System.out.println("返回状态值为:\t" + resp.getStatusLine().getStatusCode());
        return respContent;
    }
}
