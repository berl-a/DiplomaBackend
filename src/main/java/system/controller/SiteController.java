package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

    private static final String FILES_LOCATION = "/static/html/";

    @RequestMapping(value = "/")
    public String getIndex() {
        return FILES_LOCATION + "index.html";
    }

    @RequestMapping(value = "/{name}")
    public String getFile(@PathVariable(value = "name") String name) {
        return FILES_LOCATION + name + ".html";
    }

    @RequestMapping(value = "/{folder}/{name}")
    public String getResource(
            @PathVariable(value = "folder") String folder,
            @PathVariable(value = "name") String name
    ) {
        return FILES_LOCATION + folder + "/" + name + "." + folder;
    }

    @RequestMapping(value = "/favicon.ico")
    public String getFavicon() {
        return FILES_LOCATION + "ico/favicon.ico";
    }

}
