package com.example.cw3;



import com.example.storage.services.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.storage.StorageProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages="com.example")
@EnableConfigurationProperties(StorageProperties.class)
@EntityScan("com.example.data.models")
@EnableJpaRepositories("com.example.data.repositories")


public class Cw3Application implements CommandLineRunner{
    @Resource
    FileSystemStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Cw3Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();

    }

}
