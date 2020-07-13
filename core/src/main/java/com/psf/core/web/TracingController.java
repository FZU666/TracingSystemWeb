package com.psf.core.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.psf.core.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
