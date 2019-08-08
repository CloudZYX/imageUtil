import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendSmsByPost {

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
}
