package com.controller.file;

import com.utils.R;
import com.utils.Contains;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

@Controller
@Configuration
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    //获取当前IP地址
    public String getIp() {
        InetAddress localhost = null;
        try {
            localhost = Inet4Address.getLocalHost();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return localhost.getHostAddress();
    }
//    /AppInfoSystem/statics/uploadfiles/com.bithack.apparatus.jpg
    @ResponseBody
    @RequestMapping(value = "/upload", consumes = {"multipart/form-data"},method = RequestMethod.POST)
    public R upload(HttpServletRequest request , R response) {
        log.info( "=====>" + request.getServletPath());
        request.getRealPath(request.getContextPath());
//        http://localhost:8080/appinfo/statics/
        //服务器路径
        //String fileAddress ="http://"+ getIp()+ ":" + Contains.SERVER_PORT + File.separator;
        String fileAddress =Contains.FILE_DIR;
        //服务器路径集合
        ArrayList<String> imgUrls = new ArrayList<String>();
        //
        StringBuilder builder = new StringBuilder();
        //
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) { //如果是文件请求
            response.setCode(0);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile((String) iter.next());
                if (file != null) {
                    // old file name
                    String fileName = file.getOriginalFilename();
                    // new filename uuid随机名称
                    //String generateFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
                    //原名字
                    String generateFileName = fileName;
                    // store filename
                    String distFileAddress = fileAddress  + generateFileName;
                    builder.append(distFileAddress+",");
                    imgUrls.add(distFileAddress);
                    // generate file to disk
                    System.out.println(fileAddress+ generateFileName);
                    try {
                        file.transferTo(new File(fileAddress + generateFileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                response.setMsg("success");
                log.info(builder.toString());
                response.setData(imgUrls);
                }
            }else {
            response.setCode(-100);
        }

        return response;
    }

    @RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
    public Object downloadFile(@PathVariable(value = "fileName") String fileName, R r, HttpServletResponse response) throws FileNotFoundException {
        //待下载文件名
        //String fileName = "1.png";
        //设置为png格式的文件
        response.setHeader("content-type", "image/png");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            // bis = new BufferedInputStream(new FileInputStream(new File("D:/upload/" + fileName )));
            bis = new BufferedInputStream(new FileInputStream(new File(Contains.FILE_DIR + fileName )));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            r.setMsg("exception");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //成功后返回成功信息
        r.setMsg("success");

        return   r;
    }
}
