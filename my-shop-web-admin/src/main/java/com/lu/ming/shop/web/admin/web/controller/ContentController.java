package com.lu.ming.shop.web.admin.web.controller;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.commons.dto.pageInfo;
import com.lu.ming.shop.domain.TbContent;
import com.lu.ming.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 17:55 2019/8/15
 * Modified By:
 */
@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;

    //@ModelAttribute比@RequestMapping先执行，它会把传进来的对象自动帮你封装好，用来和user_form中的modelAttribute="tbUser"
//进行绑定，相当于model.addAttribute("tbUsers",tbUsers);所以其他方法有返回tbUser对象的Modle modle都可以去掉了
    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        //有id传过来就返回个tbUser对象
        if (id != null){
            tbContent = tbContentService.findById(id);
        }
        //没有就new1个tbUser对象
        else {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 请求的是资源路径value ="/list"
     * 跳转到内容列表页
     * Model 是帮我们封装到request域中 注意request只能在网址不变的情况下转发数据
     * @return 返回的是"content_list"字符串 然后到spring-mvc.xml中的视图文件解析器中拼装添加路径/WEB-INF/views/ 和.jsp后缀
     */
    @RequestMapping(value ="list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 内容表单
     * @return 内容表单页面 新增页面 添加用户的
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     *
     * 保存内容信息之前要在后台进行验证，前台的js验证是没有用的
     * param tbContent 内容对象
     * @param redirectAttributes 重定向的时候用的 spring帮我们封装的重定向
     *                           数据只会提交1次用完就没了（缓解内存）  而如果存在session中数据会存在很久
     * @param model 用来转发的 存在request域中的
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        //返回的是BaseResult类类中的消息   tbUserService的save方法传过来的消息
        BaseResult baseResult = tbContentService.save(tbContent);
        System.out.println(baseResult.getStatus());

        //（保存成功）返回信息是200就重定向到用户列表页
        if (baseResult.getStatus() == 200){
            //把消息显示提交到页面上
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            //保存完重定向到内容列表  网址变了所以重定向
            return "redirect:/content/list";
        }
        //（保存失败）不是的话就还是返回到内容表单页 并Modle提示信息
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }

    }

    /**
     * 批量删除内容信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        //如果ids不是空
        if (StringUtils.isNotBlank(ids)){
//            把传过来的ids以逗号分割为数组
            String[] idArray = ids.split(",");
            //把数组传入调用底层删除
            tbContentService.deleteArray(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }
        //否则就删除失败
        else {
            baseResult =BaseResult.fail("删除内容失败");
        }

        return baseResult;
    }

    /**
     *分页查询
     * @param request 获取jsp中的对应属性的值
     * @return 把封装好的pageInfo对象返回
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public pageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){


        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0:Integer.parseInt(strDraw);
        int start = strStart == null ? 0:Integer.parseInt(strStart);
        int length = strLength == null ? 10:Integer.parseInt(strLength);
        //封装DataTables必需的结果 返回json的数据
        pageInfo<TbContent> tbContentPageInfo = tbContentService.page(start, length, draw, tbContent);

        //返回给前端
        return tbContentPageInfo;
    }

    /**
     * 显示内容详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }

}
