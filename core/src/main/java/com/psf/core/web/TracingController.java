package com.psf.core.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author PSF(52260506 @ qq.com)
 * @introduction 控制器类，根据需求编写接口
 */

@RestController
@RequestMapping("/tracing")
public class TracingController {

    /*
     *
     *
     */
    @GetMapping("/upload")
    public ResponseEntity recommendedClub(Integer sum) {

        return ok("输入为" + sum + "");
    }



}
