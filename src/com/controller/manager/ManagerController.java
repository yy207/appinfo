package com.controller.manager;

import com.controller.file.UploadController;
import com.pojo.*;
import com.service.*;
import com.utils.Contains;
import com.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
//manager/
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private BackendUserService backendUserService;
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
     * @return 登录功能
     */
    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest request, HttpSession session){
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        BackendUser devUser = backendUserService.login(userCode,userPassword);
        if (devUser != null){
            session.setAttribute(Contains.USER_SESSION,devUser);
            return "backend/main";
        }
        request.setAttribute(Contains.ERROR_MSG,"用户名或密码错误!");
        return "backendlogin";
    }
    /**
     *
     * @return 注销功能
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Contains.USER_SESSION);
        return "redirect:/index.jsp";
    }

    /**
     * app 列表
     * @return
     */
    @RequestMapping("/backend/app/list")
    public String applist(Model model,HttpServletRequest request){

        String softwareName = request.getParameter("querySoftwareName");
        String flatformId = request.getParameter("queryFlatformId");
        String categoryLevel1 = request.getParameter("queryCategoryLevel1");
        String categoryLevel2 = request.getParameter("queryCategoryLevel2");
        String categoryLevel3 = request.getParameter("queryCategoryLevel3");
        String pageIndex = request.getParameter("pageIndex");

        AppInfo appInfo = new AppInfo();
        appInfo.setSoftwareName(softwareName);
        appInfo.setFlatformId(flatformId == null || categoryLevel3 == "" ? null :Integer.parseInt(flatformId));
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

        model.addAttribute("appInfoList",appInfoList);
        model.addAttribute("flatFormList",mapDataDictionary.get("APP_FLATFORM"));
        model.addAttribute("pages",page);
        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        return "backend/applist";
    }



    /**
     *
     * @return 审核功能 app/check?aid=51&vid=37
     */

    @RequestMapping("/backend/app/check")
    public String check(Model model, HttpSession session, HttpServletRequest request,
                        @RequestParam("aid")Integer aaid,
                        @RequestParam("vid")Integer vvid){
        log.info(aaid+"");
        log.info(vvid+"");
        session.removeAttribute(Contains.USER_SESSION);

        String aid = request.getParameter("aid");
        Integer appId = Integer.parseInt(aid);

        String vid = request.getParameter("vid");
        Integer versionId = Integer.parseInt(vid);

        // 获取指定版本信息
//        AppVersion appV = appVersionService.getAppVersionListByVersionId(aaid,vvid);
//
//        AppVersion appVersion = appVersionService.getAppVersionListByVersionId(appId,versionId);

        AppInfo appInfo  = appInfoService.getById(appId);

        request.setAttribute("appInfo",appInfo);
        return "backend/appcheck";
    }

    /**
     *
     * @param request
     * @return backend/app/categorylevellist.json?pid=14
     */
    @RequestMapping("/backend/app/categorylevellist.json")
    @ResponseBody
    public Object categorylevellist(HttpServletRequest request){
       String pid = request.getParameter("pid");
       Integer parentId = Integer.parseInt(pid);
       return appCategoryService.getListByPid(parentId);
    }

    //页面 跳转

    /**
     *
     * @return 登录页
     */
    @RequestMapping("/login")
    public String login(){
        return "backendlogin";
    }

}
