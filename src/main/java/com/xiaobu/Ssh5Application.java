package com.xiaobu;

import com.xiaobu.base.repository.BaseRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
//@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.xiaobu.repository"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@Slf4j
public class Ssh5Application {

    public static void main(String[] args) {
        SpringApplication.run(Ssh5Application.class, args);
    }
}
