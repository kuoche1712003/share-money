package io.kuoche.share.presenter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/{activityId}")
    public String activity(@PathVariable String activityId){
        return "activity";
    }

}
