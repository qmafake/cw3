package com.example.cw3;


import com.example.cw3.storage.services.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.example.cw3.storage.StorageProperties;
import com.example.cw3.storage.services.StorageService;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages="com.example.cw3.data")
@EnableConfigurationProperties(StorageProperties.class)
@EntityScan("data.models")
public class Cw3Application {
    @Resource
    FileSystemStorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(Cw3Application.class, args);
    }

    //FIXME I guess need to add some code to StorageService
    // and maybe FileSystemStorageService
    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }

}
