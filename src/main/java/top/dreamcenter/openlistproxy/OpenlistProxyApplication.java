package top.dreamcenter.openlistproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.dreamcenter.openlistproxy.model.UserConfigProperties;

@EnableConfigurationProperties(UserConfigProperties.class)
@SpringBootApplication
public class OpenlistProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenlistProxyApplication.class, args);
    }

}
