package com.controller.dev;

import com.pojo.*;
import com.service.*;
import com.utils.Contains;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dev")
public class DevController {
    @Autowired
    private DevUserService devUserService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private AppVersionService appVersionService;
    //功能实现

    /**
     *
     * @return 登录
     */
    @RequestMapping("/dologin")
    public String dologin(HttpSession session, HttpServletRequest request){
        String devCode = request.getParameter("devCode");
        String devPassword = request.getParameter("devPassword");
        DevUser devUser = devUserService.login(devCode,devPassword);
        if (devUser != null){
            session.setAttribute(Contains.DEV_USER_SESSION,devUser);
            return "developer/main";
        }
        request.setAttribute(Contains.ERROR_MSG,"用户名或密码错误!");
        return "devlogin";
    }

    /**
     *
     * @return 注销
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Contains.DEV_USER_SESSION);
        return "redirect:/index.jsp";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/flatform/app/list")
    public String flatform(Model model, HttpServletRequest request){
        String softwareName = request.getParameter("querySoftwareName");
        String status = request.getParameter("queryStatus");
        String flatformId = request.getParameter("queryFlatformId");
        String categoryLevel1 = request.getParameter("queryCategoryLevel1");
        String categoryLevel2 = request.getParameter("queryCategoryLevel2");
        String categoryLevel3 = request.getParameter("queryCategoryLevel3");
        String pageIndex = request.getParameter("pageIndex");

        AppInfo appInfo = new AppInfo();
        appInfo.setSoftwareName(softwareName);
        appInfo.setFlatformId(flatformId == null || flatformId == "" ? null :Integer.parseInt(flatformId));
        appInfo.setStatus(status == null || status == "" ? null :Integer.parseInt(status));
        appInfo.setCategoryLevel1(categoryLevel1 == null || categoryLevel1 == "" ? null :
                Integer.parseInt(categoryLevel1));
        appInfo.setCategoryLevel2(categoryLevel2 == null || categoryLevel2 == "" ? null :
                Integer.parseInt(categoryLevel2));
        appInfo.setCategoryLevel3(categoryLevel3 == null || categoryLevel3 == "" ? null :
                Integer.parseInt(categoryLevel3));

        //App
        List<AppInfo> appInfoList = appInfoService.getList(appInfo,null);
        //page
        Page page = new Page();
        page.setTotalCount(appInfoList.size());
        page.setCurrentPageNo(pageIndex == null ? 1 : Integer.parseInt(pageIndex));
        appInfoList = appInfoService.getList(appInfo,page);
        //data
        Map<String, List<DataDictionary>> mapDataDictionary = dataDictionaryService.getMapDataDictionary();
        //
        List<AppCategory> categoryLevel1List = appCategoryService.getList();
        List<AppCategory> categoryLevel2List = appCategoryService.getListByPid(
                    categoryLevel1 == null || categoryLevel1 == "" ? null :
                            Integer.parseInt(categoryLevel1));
        List<AppCategory> categoryLevel3List = appCategoryService.getListByPid(
                    categoryLevel2 == null || categoryLevel2 == "" ? null :
                        Integer.parseInt(categoryLevel2));

        model.addAttribute("appInfoList",appInfoList);
        model.addAttribute("flatFormList",mapDataDictionary.get("APP_FLATFORM"));
        model.addAttribute("statusList",mapDataDictionary.get("APP_STATUS"));
        model.addAttribute("pages",page);
        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("categoryLevel2List",categoryLevel2List);
        model.addAttribute("categoryLevel3List",categoryLevel3List);

        model.addAttribute("queryCategoryLevel1",categoryLevel1);
        model.addAttribute("queryCategoryLevel2",categoryLevel2);
        model.addAttribute("queryCategoryLevel3",categoryLevel3);

        return "developer/appinfolist";
    }
    /**
     * ajax获取分类
     * @param request
     * @return /flatform/app/categorylevellist.json
     */
    @RequestMapping("/flatform/app/categorylevellist.json")
    @ResponseBody
    public Object categorylevellist(HttpServletRequest request){
        String pid = request.getParameter("pid");
        //Integer parentId = pid == null || pid == "" ? 0 :
        return pid == null || pid == "" ?
                appCategoryService.getList() : appCategoryService.getListByPid(Integer.parseInt(pid));
    }
    /**
     *  ajax获取字典
     * @param request
     * @return /flatform/app/datadictionarylist.json
     */
    @RequestMapping("/flatform/app/datadictionarylist.json")
    @ResponseBody
    public Object datadictionarylist(HttpServletRequest request){
        String tcode = request.getParameter("tcode");
        return dataDictionaryService.getMapDataDictionary().get(tcode);
    }
    /**
     *  ajax判读APK的Name是否唯一
     * @param request
     * @return /flatform/app/apkexist.json？APKName=dsds
     */
    @RequestMapping("/flatform/app/apkexist.json")
    @ResponseBody
    public Object apkexist(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        String tcode = request.getParameter("APKName");
        if(tcode==null||tcode == ""){
            map.put("APKName","empty");
            return map;
        }
        if (appInfoService.getListByName(tcode)!=null){
            map.put("APKName","exist");
            return map;
        }
        map.put("APKName","noexist");
        return map;
    }
    /**
     *
     * @return APP添加
     */
    @RequestMapping("/flatform/app/appinfoaddsave")
    public String appinfoaddsave(HttpServletRequest request,HttpSession session){
        DevUser devUser = (DevUser) session.getAttribute(Contains.DEV_USER_SESSION);
        String softwareName = request.getParameter("softwareName") ;
        String APKName = request.getParameter("APKName") ;
        String supportROM = request.getParameter("supportROM") ;
        String interfaceLanguage = request.getParameter("interfaceLanguage") ;
        String softwareSize = request.getParameter("softwareSize") ;
        String downloads = request.getParameter("downloads") ;
        String flatformId = request.getParameter("flatformId") ;
        String  categoryLevel1 = request.getParameter("categoryLevel1") ;
        String categoryLevel2 = request.getParameter("categoryLevel2") ;
        String categoryLevel3 = request.getParameter("categoryLevel3") ;
        String appInfo = request.getParameter("appInfo") ;
        String logoPicPath = request.getParameter("logoPicPath") ;
        String logoLocPath = request.getParameter("logoLocPath") ;

        AppInfo appInfoObj = new AppInfo();
        appInfoObj.setSoftwareName(softwareName);
        appInfoObj.setCategoryLevel1(categoryLevel1 ==null || categoryLevel1 == ""?null:
                Integer.parseInt(categoryLevel1));
        appInfoObj.setCategoryLevel2(categoryLevel2 ==null || categoryLevel2 == ""?null:
                Integer.parseInt(categoryLevel2));
        appInfoObj.setCategoryLevel3(categoryLevel3 ==null || categoryLevel3 == ""?null:
                Integer.parseInt(categoryLevel3));
        appInfoObj.setSoftwareSize(softwareSize ==null || softwareSize == ""?null:
                Double.parseDouble(softwareSize));
        appInfoObj.setFlatformId(flatformId ==null || flatformId == ""?null:
                Integer.parseInt(flatformId));
        appInfoObj.setDownloads(downloads ==null || downloads == ""?null:
                Integer.parseInt(downloads));
        appInfoObj.setSupportROM(supportROM);
        appInfoObj.setAPKName(APKName);
        appInfoObj.setDevId(devUser.getId());
        appInfoObj.setInterfaceLanguage(interfaceLanguage);
        appInfoObj.setAppInfo(appInfo);
        appInfoObj.setLogoPicPath(logoPicPath);
        appInfoObj.setLogoLocPath(logoLocPath.substring(logoLocPath.lastIndexOf("/")+1));

        if (appInfoService.appinfoAddSave(appInfoObj)>0){
            return "developer/appinfolist";
        }
        request.setAttribute("fileUploadError","添加失败！");
        return "developer/appinfoadd";
    }

    /**
     * flatform/app/delapp.json?id=60
     * @return 删除APP以及其版本信息
     */
    @ResponseBody
    @RequestMapping("/flatform/app/delapp.json")
    public Object delapp(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        String id = request.getParameter("id");

        if (id == null || id == "" || appInfoService.getById(Integer.parseInt(id)) == null){
            map.put("delResult","notexist");
            return map;
        }
        if (appInfoService.appinfoDelById(Integer.parseInt(id))>0){
            map.put("delResult","true");
            return map;
        }
        map.put("delResult","false");
        return map;
    }
    /**
     * flatform/app/appview/59
     * @return App查看页
     */
    //TODO SA
    @RequestMapping("/flatform/app/appview/{id}")
    public String appinfoadd(@PathVariable("id")Integer id,HttpServletRequest request){
        AppInfo appInfo = appInfoService.getById(id);
        request.setAttribute("appInfo",appInfo);
        return "developer/appinfoview";
    }
    /**
     * flatform/app/appversionadd?id=60
     * @return App版本添加页
     */
    @RequestMapping("/flatform/app/appversionadd")
    public String appversionadd(HttpServletRequest request){
        String idStr =request.getParameter("id");
        Integer appId = Integer.parseInt(idStr);
        List<AppVersion> appVersionList = appVersionService.getAppVersionList(appId);

        request.setAttribute("appVersionList",appVersionList);
        return "developer/appversionadd";
    }
    /**
     * flatform/app/appversionadd?id=60
     * @return App版本添加页
     */
    @RequestMapping("/flatform/app/addversionsave")
    public String addversionsave(HttpServletRequest request){
        String appId = request.getParameter("appId");
        String versionNo = request.getParameter("versionNo");
        String versionSize = request.getParameter("versionSize");
        String publishStatus = request.getParameter("publishStatus");
        String versionInfo = request.getParameter("versionInfo");
        String fileUploadError = request.getParameter("fileUploadError");
        String apkLocPath = request.getParameter("apkLocPath");
        return "developer/appversionadd";
    }
    /**
     * flatform/app/appversionmodify?vid=37&aid=51
     * @return App版本添加页
     */
    @RequestMapping("/flatform/app/appversionmodify")
    public String appversionmodify(HttpServletRequest request) throws Exception {
        Integer vid = null;
        Integer aid = null;
        try {
            vid = Integer.parseInt(request.getParameter("vid"));
            aid = Integer.parseInt(request.getParameter("aid"));
        }catch (Exception e){
            throw  new Exception("参数传递错误");
        }
        AppVersion appVersion = appVersionService.getAppVersionListByVersionId(aid,vid);
        request.setAttribute("appInfo",appVersion);
        return "developer/appversionmodify";
    }
    /**
     * flatform/app/58/sale.json
     * @return App下架
     */
    @ResponseBody
    @RequestMapping("/flatform/app/{pid}/sale.json")
    public Object sale(HttpServletRequest request,@PathVariable("pid")Integer pid){
        Integer status =  appInfoService.getById(pid).getStatus();
        if (status == 4)
            appInfoService.appSale(pid,5);
        if (status == 5)
            appInfoService.appSale(pid,4);

        Map<String,String> map = new HashMap<>();
        map.put("resultMsg","success");
        return map;
    }



    //跳转页面
    /**
     *
     * @return 开发者登录页
     */
    @RequestMapping("/login")
    public String login(){
        return "devlogin";
    }
    /**
     *
     * @return App添加页
     */
    @RequestMapping("/flatform/app/appinfoadd")
    public String appinfoadd(){
        return "developer/appinfoadd";
    }







}
