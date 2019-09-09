package com.lu.ming.shop.web.admin.web.controller;

import com.lu.ming.shop.commons.dto.BaseResult;
import com.lu.ming.shop.domain.TbContentCategory;
import com.lu.ming.shop.web.admin.abstarct.AbstractBaseTreeController;
import com.lu.ming.shop.web.admin.service.TbContentCategoryService;
import com.lu.ming.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分配管理
 * @Author:MingYie
 * @Description
 * @Date:Created in 11:59 2019/8/15
 * Modified By:
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {
//
//    @Autowired
//    private TbContentCategoryService tbContentCategoryService;

    //@ModelAttribute比@RequestMapping先执行，它会把传进来的对象自动帮你封装好，用来和user_form中的modelAttribute="tbUser"
    //进行绑定，相当于model.addAttribute("tbUsers",tbUsers);所以其他方法有返回tbUser对象的Modle modle都可以去掉了
    @ModelAttribute
    public TbContentCategory getTbContent(Long id){
        TbContentCategory tbContentCategory = null;

        //有id传过来就返回个tbUser对象
        if (id != null){
            tbContentCategory = service.findById(id);
        }
        //没有就new1个tbUser对象
        else {
            tbContentCategory = new TbContentCategory();
        }

        //TODO 代办项目
        return tbContentCategory;
    }

    /**
     *内容分类列表
     * @param model 用来转发 往request中存数据的 方便jsp取值
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        //排序后的数据列表
        List<TbContentCategory> targetList = new ArrayList<>();
        //从数据库查出来的原数据列表
        List<TbContentCategory> sourceList = service.selectAll();

        //排序
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }

    /**
     * 内容分类表单
     * @return 内容分类表单页面 新增页面 添加内容分类的
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }

    /**
     * 保存
     * @param tbContentCategory
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = service.save(tbContentCategory);
        //保存成功
        System.out.println(baseResult.getStatus());
        if (baseResult.getStatus() == 200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return form(tbContentCategory);
        }
    }

    /**
     * 批量删除内容信息
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        //如果ids不是空
        if (StringUtils.isNotBlank(ids)){
            //把传过来的ids以逗号分割为数组
            String[] idArray = ids.split(",");
            //把数组传入调用底层删除
            service.deleteArray(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }
        //否则就删除失败
        else {
            baseResult =BaseResult.fail("删除内容失败");
        }

        return baseResult;
    }

    /**
     * @ResponseBody返回的数据不是 html 标签的页面，而是其他某种格式的数据时（如json、xml等）使用
     * 树形结构，content_form中zTree插件跳转的url
     * 第一次的ID肯定是null，赋值0，就能查到父节点LeeShop,这样就能以LeeShop为父节点，查到他的子节点，以此类推。
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "tree/data",method = RequestMethod.POST)
    public List<TbContentCategory> TreeData(Long id){
        if (id == null){
            id = 0L;
        }
        return service.selectByPid(id);
    }

//    /**
//     *排序用treeTable插件显示的树形结构数据 当前节点的parentId等于上一节点的ID，那就是上一节点的子节点
//     * @param sourceList 原来的数据列表
//     * @param targetList 排序后的数据列表
//     * @param Id 手动传进来的一个Id 其实就是数据表中的第一个的父节点为的0的数据
//     */
//    private void sortList(List<TbContentCategory> sourceList,List<TbContentCategory> targetList,Long Id){
//        for (TbContentCategory tbContentCategory : sourceList) {
//            //如果当前节点ParentId是否等于0L 是就就添加到排序后的数据列表
//            if (tbContentCategory.getParent().getId().equals(Id)){
//                targetList.add(tbContentCategory);
//
//                //判断有没有子节点，如果有就继续追加
//                if (tbContentCategory.getIsParent()){
//                    //那就继续遍历原来的sourceList
//                    for (TbContentCategory contentCategory : sourceList) {
//                        //再判断当前节点的ParentId是否等于上一个节点的Id
//                        if (contentCategory.getParent().getId().equals(tbContentCategory.getId())){
//                            //递归  注意是tbContentCategory的id
//                            sortList(sourceList,targetList,tbContentCategory.getId());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
}
