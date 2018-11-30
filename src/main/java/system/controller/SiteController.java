package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    @RequestMapping(value = "/")
    public String getIndex() {
        return "/static/html/index.html";
    }

    @RequestMapping(value = "/{name}")
    public String getFile(@PathVariable(value = "name") String name) {
        return "/static/html/" + name + ".html";
    }

    @RequestMapping(value = "/{folder}/{name}")
    public String getResource(
            @PathVariable(value = "folder") String folder,
            @PathVariable(value = "name") String name
    ) {
        return "/static/html/" + folder + "/" + name + "." + folder;
    }

//    @RequestMapping(value = "/favicon.ico")
//    public String getFavicon() {
//        return "/static/html/favicon.ico";
//    }

}
