package org.alexsorin.product;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Properties {
    private String msg;
    private int buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;
}
