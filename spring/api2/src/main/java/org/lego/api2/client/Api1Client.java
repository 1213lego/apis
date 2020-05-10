package org.lego.api2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@FeignClient("api")
public interface Api1Client {
    @GetMapping("/apps")
    List<HashMap<String,Object>> apps();
}
