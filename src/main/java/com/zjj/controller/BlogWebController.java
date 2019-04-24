package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.exception.ArticleNotFoundException;
import com.zjj.model.BizArticle;
import com.zjj.service.BizArticleService;
import com.zjj.service.BizThemeService;
import com.zjj.util.CopyUtil;
import com.zjj.util.CoreConst;
import com.zjj.vo.ArticleConditionVo;
import com.zjj.vo.base.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: demo
 * @description: 首页Controller
 * @author: zjj
 * @create: 2019-04-23 22:03:42
 **/
@Controller
public class BlogWebController {


    @Autowired
    private BizArticleService bizArticleService;

    @Autowired
    private BizThemeService bizThemeService;

    private static final String THEME_PREFIX = "theme/";
    //localhost:8080 博客入口
    @GetMapping("/")
    public String index(Model model, ArticleConditionVo vo) {
        model.addAttribute("pageUrl", "blog/index");
        model.addAttribute("categoryId", "index");
        //轮播文章
        model.addAttribute("sliderList", bizArticleService.sliderList());
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /**
    * @Description: index
    * @Param: [pageNumber, vo, model]
    * @return: java.lang.String
    * @Author: zjj
    * @Date: 2019/4/24
    */
    @RequestMapping("/blog/index/{pageNumber}")
    public String index(@PathVariable("pageNumber") Integer pageNumber, ArticleConditionVo vo, Model model) {
        vo.setPageNumber(pageNumber);
        model.addAttribute("pageUrl", "blog/index");
        model.addAttribute("catagoryId", "index");
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /** 
    * @Description: categroy
    * @Param: [categoryId, model] 
    * @return: java.lang.String 
    * @Author: zjj
    * @Date: 2019/4/24 
    */ 
    @GetMapping("/blog/category/{categoryId}")
    public String categroy(@PathVariable("categoryId") Integer categoryId, Model model ) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setCategoryId(categoryId);
        model.addAttribute("pageUrl", "blog/category/" + categoryId);
        model.addAttribute("categoryId", categoryId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /** 
    * @Description: category
    * @Param: [categoryId, pageNumber, model] 
    * @return: java.lang.String 
    * @Author: zjj
    * @Date: 2019/4/24 
    */ 
    @GetMapping("/blog/category/{categoryId}/{pageNumber}")
    public String category(@PathVariable("categoryId") Integer categoryId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setCategoryId(categoryId);
        vo.setPageNumber(pageNumber);
        model.addAttribute("pageUrl", "blog/category/" + categoryId);
        model.addAttribute("categoryId",categoryId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName()+ "/index";
    }

    /**
    * @Description: 标签列表
    * @Param: [tagId, model]
    * @return: java.lang.String
    * @Author: zjj
    * @Date: 2019/4/24
    */
    @GetMapping("/blog/tag/{tagId}")
    public String tag(@PathVariable("tagId") Integer tagId, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setTagId(tagId);
        model.addAttribute("pageUrl", "blog/tag/" + tagId);
        loadMainPage(model,vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName()+ "/index";
    }

    @GetMapping("/blog/tag/{tagId}/{pageNumber}")
    public String tag(@PathVariable("tagId") Integer tagId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setTagId(tagId);
        vo.setPageNumber(pageNumber);
        model.addAttribute("pageUrl", "blog/tag/" + tagId);
        loadMainPage(model,vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName()+ "/index";
    }

    @GetMapping("/blog/article/{articleId}")
    public String article(HttpServletRequest request, Model model, @PathVariable("articleId") Integer articleId) {
        BizArticle article = bizArticleService.selectById(articleId);
        if (article == null || CoreConst.STATUS_INVALID.equals(article.getStatus()) ) {
            throw new ArticleNotFoundException();
        }
        model.addAttribute("article", article);
        model.addAttribute("categoryId",article.getCategoryId());
        return THEME_PREFIX + bizThemeService.selectCurrent().getName()+ "/article";
    }


    /**
     * 文章详情
     *
     * @param model
     * @return
     */
    @GetMapping("/blog/comment")
    public String comment(Model model) {
        model.addAttribute("categoryId","comment");
        return THEME_PREFIX + bizThemeService.selectCurrent().getName()+ "/comment";
    }
    private void loadMainPage(Model model, ArticleConditionVo vo) {
        vo.setStatus(CoreConst.STATUS_VALID);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticle> articleList = bizArticleService.findByCondition(vo);
        PageInfo<BizArticle> pageInfo = new PageInfo<>(articleList);
        PageVo pageVo = CopyUtil.getCopy(pageInfo, PageVo.class);
        model.addAttribute("page", JSONObject.toJSON(pageVo));
        //文章列表
        model.addAttribute("articleList", articleList);

    }

}
