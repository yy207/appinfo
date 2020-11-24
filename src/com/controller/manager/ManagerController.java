package com.controller.manager;

import com.pojo.*;
import com.service.AppCategoryService;
import com.service.AppInfoService;
import com.service.BackendUserService;
import com.service.DataDictionaryService;
import com.utils.Contains;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private BackendUserService backendUserService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppCategoryService appCategoryService;


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
        appInfo.setCategoryLevel1(categoryLevel1 == null || categoryLevel3 == "" ? null :
                Integer.parseInt(categoryLevel1));
        appInfo.setCategoryLevel2(categoryLevel2 == null || categoryLevel3 == "" ? null :
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
        List<AppCategory> categoryLevel1List = appCategoryService.getList(1);
        List<AppCategory> categoryLevel2List = appCategoryService.getList(2);
        List<AppCategory> categoryLevel3List = appCategoryService.getList(3);

        model.addAttribute("appInfoList",appInfoList);
        //model.addAttribute("statusList",mapDataDictionary.get("APP_STATUS"));
        model.addAttribute("pages",page);
        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("categoryLevel2List",categoryLevel2List);
        model.addAttribute("categoryLevel3List",categoryLevel3List);

        return "backend/applist";
    }



    /**
     *
     * @return 审核功能 app/check?aid=51&vid=37
     */
    @RequestMapping("/backend/app/check")
    public String check(HttpSession session){
        session.removeAttribute(Contains.USER_SESSION);
        return "appcheck";
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
