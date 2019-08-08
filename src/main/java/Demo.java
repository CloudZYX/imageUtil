public class Demo {

    public static void main(String[] args) {

        //通过http获取图片信息
//        String response = SendSmsByPost.sendSmsByGet("https://img1.qdingnet.com/image-81a4d7b3-56ef-4ca0-aa84-d5a669854893.jpg","");
//        byte[] bytes = response.getBytes();

        String httpUrl = "https://img1.qdingnet.com/image-81a4d7b3-56ef-4ca0-aa84-d5a669854893.jpg";
        String saveUrl = "E:/ZYX/5.jpg";
        ImageFile.getInputStream(httpUrl);
        ImageFile.saveImageToDisk(httpUrl,saveUrl);

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
