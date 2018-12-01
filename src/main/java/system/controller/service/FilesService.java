package system.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesService {

    private static final String JS_FILES_PATH = "/WEB-INF/static/html/js";

    @Autowired
    ServletContext servletContext;

    public void replaceServerLocationInJsFiles() {
        String realPath = servletContext.getRealPath(JS_FILES_PATH);
//        System.out.println(realPath);
        try (Stream<Path> paths = Files.walk(Paths.get(realPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(stringPath -> {
                        Path path = Paths.get(stringPath.toString());
                        Charset charset = StandardCharsets.UTF_8;
                        String content;
                        try {
                            content = new String(Files.readAllBytes(path), charset);
                            content = content.replaceAll("127.0.0.1:81", "127.0.0.1:80");
                            Files.write(path, content.getBytes(charset));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
