package kpi.ipt.organizer.frontend.config;

import feign.Logger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author olch0615
 *         Date: 5/30/2017
 *         Time: 1:50 PM
 */
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "kpi.ipt.organizer.frontend.client")
@Profile("production")
public class ProductionConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}