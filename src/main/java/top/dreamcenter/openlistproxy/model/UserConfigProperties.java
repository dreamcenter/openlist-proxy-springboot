package top.dreamcenter.openlistproxy.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "proxy")
public class UserConfigProperties {

    /**
     * OpenList 后台 的 TOKEN
     * <p>
     * e.g. openlist-2590f023-c612-4a55-9b6b-******
     */
    private String token;

    /**
     * OpenList 后台 的 地址
     * <p>
     * e.g. https://openlist.dreamcenter.top
     */
    private String address;




}
