package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.model.users.User;
import system.model.users.UserType;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class StartupService {

    @Autowired
    FilesService filesService;
    @Autowired
    UserService userService;

    @PostConstruct
    public void postConstruct() {
        filesService.replaceServerLocationInJsFiles();
        userService.addAdminToDatabase();
    }

}
