package org.alexsorin.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="product")
@Getter @Setter @ToString
public class ProductServiceConfig {
    private String msg;
    private int buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;

}
