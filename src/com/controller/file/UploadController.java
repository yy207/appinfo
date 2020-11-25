package com.controller.file;

import com.utils.R;
import com.utils.Contains;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @ResponseBody
    @RequestMapping(value = "/upload", consumes = {"multipart/form-data"},method = RequestMethod.POST)
    public R upload(HttpServletRequest request,R response) {
        //服务器路径
        String fileAddress ="http://"+ getIp()+ ":" + Contains.SERVER_PORT + File.separator;
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
                    // new filename
                    String generateFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
                    // store filename
                    String distFileAddress = fileAddress  + generateFileName;
                    builder.append(distFileAddress+",");
                    imgUrls.add(distFileAddress);
                    // generate file to disk
                    System.out.println(Contains.FILE_DIR+ generateFileName);
                    try {
                        file.transferTo(new File(Contains.FILE_DIR + generateFileName));
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
}
