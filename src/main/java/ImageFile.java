import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageFile {

    public static void readImage(String targeUrl,String originUrl) throws IOException{
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(originUrl));
        //原先图片所在路径
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targeUrl));
        //你要保存在哪个目录下面
        int i;
        while((i=in.read())!=-1){
            out.write(i);
        }

        out.flush();
        out.close();
        in.close();
    }

    //把从服务器获得图片的输入流InputStream写到本地磁盘
    public static void saveImageToDisk(String imageUrl,String targeUrl) {

        InputStream inputStream = getInputStream(imageUrl);
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targeUrl);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    // 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
    public static InputStream getInputStream(String imageUrl) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(imageUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();

            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inputStream;

    }
}
