package com.psf.core.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.psf.core.mapper.ResultMapper;
import com.psf.core.mapper.TargetMapper;
import com.psf.core.model.Result;
import com.psf.core.model.Target;
import com.psf.core.model.TargetExample;
import com.psf.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author PSF(52260506 @ qq.com)
 * @introduction 控制器类，根据需求编写接口
 */

@RestController
@RequestMapping("/tracing")
public class TracingController {

    @Autowired
    private StorageService storageService;
    //***********************************其佳的部分
    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private ResultMapper resultMapper;


    @GetMapping("/imageurl")
    @ResponseBody
    public ResponseEntity imageurl()
    {
        TargetExample targetExample=new TargetExample();
        List<Target> t=targetMapper.selectByExampleWithBLOBs(targetExample);
        JSONArray jsonArray=new JSONArray();
        for(Target target:t)
        {
            JSONObject jsonObject=new JSONObject();
            String tid=target.getTid().toString();
            String imageurl=target.getImgurl();
            jsonObject.put(tid,imageurl);
            jsonArray.add(jsonObject);
        }
        return ResponseEntity.ok().body(jsonArray);
    }

    @GetMapping("/eigenvalue")
    @ResponseBody
    public ResponseEntity<List<Target>> eigenvalue()
    {
        TargetExample targetExample=new TargetExample();
        List<Target> t=targetMapper.selectByExampleWithBLOBs(targetExample);
        return ok().body(t);
    }

    @PostMapping("/result")
    @ResponseBody
    public void result(Integer id, String rid, Integer tid, Date discoverytime)
    {
        Result result=new Result();
        result.setDiscoverytime(discoverytime);
        result.setId(id);
        result.setRid(rid);
        result.setTid(tid);
        resultMapper.insert(result);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    //***************************其佳的部分
    /*
     *
     *
     */
    @GetMapping("/upload")
    public ResponseEntity upload(Integer sum) {

        return ok("输入为" + sum + "");
    }

    /*
     *
     *
     */
    @PostMapping("/test")
    public ResponseEntity test(@RequestParam("image") MultipartFile image) {

        String url = storageService.storeImage(image);
        if (!StrUtil.isEmpty(url)) {
            System.out.println("上传成功");
            return ok("————————");
        }

        return ok("xxxxxx");
    }





}
