package com.qf.controller;

import com.qf.pojo.HouseInfo;
import com.qf.service.HouseInfoService;
import com.qf.utils.FastDFSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyController {
    @RequestMapping("/upload.json")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){//文件上传 添加依赖 在配置文件中添加文件上传解析器
       // System.out.println("dddddd");
        System.out.println("dddddd");
        try {
            //1.得到上传文件的名字
            String filename = file.getOriginalFilename();
            System.out.println("上传文件的名字"+filename);
            //2.后缀
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            //3.得到上传文件的byte数组
            byte[] bytes = file.getBytes();

            //4.使用工具类进行上传
            FastDFSUtil fastDFSUtil=new FastDFSUtil();
            String[] s = fastDFSUtil.upload(bytes, suffix);//返回文件上传的路径
            for (String s1 : s) {
                System.out.println(s1);
            }

            StringBuilder stringBuilder=new StringBuilder("http://192.168.44.73:82/");
            if (s!=null){
                //group1
                //M00/00/00/wKgsSV01tuOATLlHAAMqQHKRrMs680.jpg
                for (int i = 0; i < s.length; i++) {
                    stringBuilder.append(s[i]);
                    if (i==0){
                        stringBuilder.append("/");
                    }
                }
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/fileupload.json")
    @ResponseBody
    public Map<String,Object> fileupload(@RequestParam("file") MultipartFile file){//文件上传 添加依赖 在配置文件中添加文件上传解析器
        Map<String,Object> map=new HashMap<>();
        // System.out.println("dddddd");
        try {
            //1.得到上传文件的名字
            String filename = file.getOriginalFilename();
            System.out.println("上传文件的名字"+filename);
            //2.后缀
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            //3.得到上传文件的byte数组
            byte[] bytes = file.getBytes();

            //4.使用工具类进行上传
            FastDFSUtil fastDFSUtil=new FastDFSUtil();
            String[] s = fastDFSUtil.upload(bytes, suffix);//返回文件上传的路径

            StringBuilder stringBuilder=new StringBuilder("http://192.168.44.73:82/");
            if (s!=null){
                //group1
                //M00/00/00/wKgsSV01tuOATLlHAAMqQHKRrMs680.jpg
                for (int i = 0; i < s.length; i++) {
                    stringBuilder.append(s[i]);
                    if (i==0){
                        stringBuilder.append("/");
                    }
                }
            }
            String url = stringBuilder.toString();
            map.put("status",200);
            map.put("msg","success");
            map.put("url",url);
            return map ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("status",500);
        map.put("msg","fail");
        return  map;
    }

    @Resource
    private HouseInfoService houseInfoService;

    @RequestMapping("/addHouseInfo.json")
    @ResponseBody
    public Map<String,Object> addHouseInfo(HouseInfo houseInfo) {//文件上传 添加依赖 在配置文件中添加文件上传解析器
        System.out.println(houseInfo.getImages());
        Map<String,Object> map=new HashMap<>();
        int i = houseInfoService.saveHouseInfo(houseInfo);
        if (i>0){
           map.put("msg","success");
            map.put("status",200);
        }else {
            map.put("msg","success");
            map.put("status",500);
        }
        return map;
    }

}
