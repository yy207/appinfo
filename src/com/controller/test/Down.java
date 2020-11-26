package com.controller.test;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class Down {
    /**
     * 获取自定义的时间戳
     *
     * @author luckyriver
     *
     * @return 当天日期的字符串形式.如：当天为6月20日，则返回 “06-28”
     */
    public static String getSaveName() {
        SimpleDateFormat formater = new SimpleDateFormat("MM-dd");
        formater.applyPattern("MM-dd");
        String saveName = formater.format(new Date());
        return saveName;
    }

    /**
     * 拷贝URL资源到本地
     *
     * @param url  URL的字符串形式
     * @param file 将要保存的文件
     */
    public static void URLToFile(URL url, File file) {
        InputStream is = null;
        try {
            // 以必应首页图片为例
            is = url.openConnection().getInputStream();// 得到输入流
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 建立缓冲输入流，包装得到的普通输入流
        try (BufferedInputStream bif = new BufferedInputStream(is);

             // 建立缓冲输出流，包装文件输出流
             BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(file))) {// 读写流程
            int len;
            byte[] buffer = new byte[1024 * 10];
            while (-1 != (len = bif.read(buffer))) {
                bof.write(buffer, 0, len);
            }
            bof.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据URL获取资源文件后缀名
     *
     * @author luckyriver
     * @param url 目标URL
     * @return 后缀名，失败则返回""。 如 ".jpg", ".html"
     */
    public static String getSuffix(URL url) {
        String suffix = "";
        try {
            String type = url.openConnection().getContentType().split(";")[0];
            suffix = "." + type.split("/")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suffix;
    }

    /**
     * 获取必应每日一图的URL
     *
     * @author luckyriver
     * @return URL的字符串形式，失败则返回""
     */
    public static String getBingImageURL() {
        Document doc;
        String imageURL = "";
        try {
            doc = Jsoup.connect("https://cn.bing.com").get();
            Element imageLink = doc.getElementById("bgLink");
            imageURL = imageLink.absUrl("href");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageURL;
    }

    /**
     * 测试
     *
     */
    public static void main(String[] args) throws MalformedURLException {
        URL imageURL = new URL(getBingImageURL());
        String FileName = getSaveName() + getSuffix(imageURL);// "07-08.jpeg"
        File parent = new File("D:\\desktop\\images\\");// 保存路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        URLToFile(imageURL, new File(parent, FileName));// 获取文件
    }
}
