package com.lu.ming.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author:MingYie
 * @Description
 * @Date:Created in 14:54 2019/8/17
 * Modified By:
 */
@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * content_form的文件上传
     * @param dropFile Dropzone插件上传所需要的文件名
     * @param wangEditorFile wangEditor富文本编辑器上传所需要的文件名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, MultipartFile[] wangEditorFile,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();

        //Dropzone文件上传
        if (dropFile != null){
            //把UUID修改后的文件放入map集合,
            // 注意content_form中Dropzone的data里存的就是这里Map集合里的键值对，要按键的名称取值
            result.put("fileName",getFilePath(dropFile,request));
        }

        //wangEditor文件上传
        if (wangEditorFile != null && wangEditorFile.length > 0){

            List<String> fileNames = new ArrayList<>();
            //遍历wangEditorFile数组里的文件名，调用getFilePath方法得到一个个完整的图片文件路径，加入到集合中
            for (MultipartFile editorFile : wangEditorFile) {
                fileNames.add(getFilePath(editorFile,request));
            }
            result.put("errno",0);
            //注意fileNames是数组，里面是一个个完整的图片文件路径
            result.put("data",fileNames);
        }

        //返回给前端
        return result;
    }


    /**
     *
     * @param multipartFile
     * @param request
     * @return 返回文件完整路径
     */
    private String getFilePath(MultipartFile multipartFile,HttpServletRequest request){
        //拿到上传上来的图片名
        String filename = multipartFile.getOriginalFilename();
        //获取从.开始到后面的字符串，就是获取后缀名.jpg
        String fileSuffix = filename.substring(filename.lastIndexOf("."));
        //存放图片的路径
        String realPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断文件是否存在，不存在就创建
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        //将图片写入目标文件 使用UUID+后缀 就是全球唯一的文件名了，不会被覆盖
        file = new File(realPath, UUID.randomUUID()+fileSuffix);
        try {
            //spring的transferTo方法保存文件
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * request.getScheme:获取服务端协议 http/https
         * request.getServerName()：获取localhost/ip/域名
         * request.getServerPort()：获取服务器端口号
         */
        //返回文件完整路径
        String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        return serverPath + UPLOAD_PATH + file.getName();
    }
}
