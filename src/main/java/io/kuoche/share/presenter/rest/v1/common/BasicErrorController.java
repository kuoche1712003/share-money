package io.kuoche.share.presenter.rest.v1.common;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BasicErrorController  extends org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController {

    public BasicErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> map = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        Map<String, Object> customMap = new HashMap<>();
        customMap.put("code", map.get("status"));
        customMap.put("message", map.get("error"));
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(customMap, status);
    }
}
