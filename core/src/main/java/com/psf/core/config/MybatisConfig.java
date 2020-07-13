package com.psf.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author psf
 * @version 1.0
 */
@Configuration
@MapperScan({"com.psf.core.mapper"})
public class MybatisConfig {

}
