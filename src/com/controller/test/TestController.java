package com.controller.test;

import com.controller.file.UploadController;
import com.dao.PruductCategoryMapper;
import com.pojo.PruductCategory;
import com.utils.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.json.*;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private PruductCategoryMapper pruductCategoryMapper;

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    // /test/test?opt_id=9490

    /**
     * 产品分类的数据
     * @param request
     * @param opt_id
     */
    @ResponseBody
    @RequestMapping("/test")
    public void test_1(HttpServletRequest request,String opt_id){
        if (opt_id  == null )
            opt_id = request.getParameter("opt_id");
        String url = "https://mobile.yangkeduo.com/proxy/api/api/search/opt/999998/";
        String catUrl = "https://mobile.yangkeduo.com/proxy/api/api/search/operation/detail/group?pdduid" +
                "=4868776367593&opt_id="+opt_id+"&link_id=8d03f5d9-cb33-4d10-a939-02fb6ac85a37&anti_content" +
                "=0aoAfxnUFjGYY9dV1mpe26a3rJGUdsU3zfneKGXetPd177vR_Zz7DmzDrPUMp_qqum-kDhKwkrgGRofRV2hR0Ri0uXzetRg18XJb8FbJpjMyTP9RC08EGzgS9h2v342iLi22Zmii9vMJmiS1hGxKzgU-kNZ1umZMmLUefLKTeEET_qtdSd1gf7td5j1UhVyPdneAG-E_ZrvZAqBkZBN_h5Lnae1YMdPOaxQXXoHwVU8B_uAxA8sSF0E0-R4eD10NLyIIJagHevQXtdjbeeQXDvnj8p090n-mZAYD4Ps9i9l54Xnk2YnOfDCtvpxZAFCUaB6cKe5crp-GR33lJb5CqYEiw1J0m9q-XwP4loLGC3a_Ya14Htb_yxGpsVC4vN_4-L2132vnjdF-IP6I3rYrX968jgFhojxFEK5Ev9uSLEkwFU1736ir9GOAhpa626ZEw_I-dT0gzoBJoQdxRnEWKDiZkSXV-ju32e6LeXQMokMMw8L7CkqL94nesgjCwcgn9k0LzdXTMczE3fqgEK4LA_C9icxQmJQHb_lGruYLtuJ7tuNLb4NQvKW6QW7GVJXAAjhhaqyGaO37hYRpzgwUMNiVx15DlUy7pfQvvvh8oXrVQMrIHY8-pWeDJJ26UXaWN_WYAv1NnoJQFOkyhpn3DcqgAFncNYRLUp4v9";
        HttpService pxservice = new HttpService(request);

        //path = path+"/"+dir;
        String uri = url + "groups";
        log.info("==>" + uri);
        String strRequest = pxservice.isGet(catUrl, new TreeMap<>());
        //System.out.println("strRequest"+strRequest);
        log.info("==>" + strRequest);
        JSONObject jsonObject = new JSONObject(strRequest);

        Map data = jsonObject.toMap();
        String opt_idStr =   data.get("opt_id").toString();
        String opt_name = (String) data.get("opt_name");
        String fid = opt_id;
        PruductCategory pruductCategory = null;
        List<Map>  children = (List) data.get("children");

        for (Map map:children) {
          List<Map>  children_sub = (List) map.get("children");
          for (Map sub:children_sub) {
            fid =  map.get("opt_id").toString();
            opt_idStr =  sub.get("opt_id").toString();
            opt_name = (String) sub.get("opt_name");
            String image_url = (String) sub.get("image_url");
            pruductCategory = new PruductCategory(
                  UUID.randomUUID().toString().replaceAll("-", ""),
                  opt_idStr,
                  fid,
                  opt_name,
                  opt_name,image_url,"3") ;
            //添加
              pruductCategoryMapper.insert(pruductCategory);
          }
          fid = opt_id;
          opt_idStr =  map.get("opt_id").toString();
          opt_name = (String) map.get("opt_name");
          //添加
            pruductCategory = new PruductCategory(
                    UUID.randomUUID().toString().replaceAll("-", ""),
                    opt_idStr,
                    fid,
                    opt_name,
                    opt_name,"","2") ;
            //添加
            pruductCategoryMapper.insert(pruductCategory);
        }
        System.out.println(data);

    }

    @ResponseBody
    @RequestMapping("/down")
    public  String down(){
        List<PruductCategory> list = pruductCategoryMapper.getPruductList();
        for (PruductCategory p:list) {
            String imgStr = p.getImg();
            log.info(imgStr);
            if (imgStr!=null &&imgStr != ""&&imgStr.trim().length()>1){
                String fileName =imgStr.substring(imgStr.lastIndexOf("/")+1);
                log.info(fileName);
                try {
                    URL url = new URL(imgStr);
                    File file = new File("D:\\desktop\\product\\");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    Down.URLToFile(url,new File(file,fileName));
                    Thread.sleep(2000);
                } catch (MalformedURLException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

}













