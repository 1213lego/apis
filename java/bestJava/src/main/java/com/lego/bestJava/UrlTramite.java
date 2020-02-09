package com.lego.bestJava;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class UrlTramite {
    private Long idUrl;
    private Long idTramite;
    private String url;
    private boolean esVisible;
}
