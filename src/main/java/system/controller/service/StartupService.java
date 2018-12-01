package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StartupService {

    @Autowired
    FilesService filesService;

    @PostConstruct
    public void postConstruct() {
        filesService.replaceServerLocationInJsFiles();
    }


}
