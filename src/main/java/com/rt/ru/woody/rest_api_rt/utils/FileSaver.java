package com.rt.ru.woody.rest_api_rt.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileSaver {


    public void saveToFile(String response) {



            try {
                Path filePath = Paths.get("resources/data/countries.json");
                if(!Files.exists(filePath) && filePath.getFileName() != null) {

                    Files.createFile(filePath);

                    Files.write(filePath, response.getBytes());

                    log.info("Saving to file {}", filePath.getFileName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}