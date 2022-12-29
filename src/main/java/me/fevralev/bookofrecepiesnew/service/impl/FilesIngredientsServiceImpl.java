package me.fevralev.bookofrecepiesnew.service.impl;

import me.fevralev.bookofrecepiesnew.exception.FileReadException;
import me.fevralev.bookofrecepiesnew.exception.FileUploadException;
import me.fevralev.bookofrecepiesnew.exception.FileWriteException;
import me.fevralev.bookofrecepiesnew.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class FilesIngredientsServiceImpl implements FilesService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.ingredients.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileWriteException("Ошибка записи в файл");
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла");
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return null;
    }

    @Override
    public void uploadFile(MultipartFile file) {
        Path filePath = Path.of(dataFilePath, dataFileName);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            bis.transferTo(bos);
        } catch (IOException e) {
            throw new FileUploadException("Ошибка выгрузки файла");
        }
    }
}