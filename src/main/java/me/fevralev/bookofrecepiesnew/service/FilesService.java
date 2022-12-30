package me.fevralev.bookofrecepiesnew.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();

    void uploadFile(MultipartFile file) throws IOException;

    Path createTempFile(String suffix);
}


