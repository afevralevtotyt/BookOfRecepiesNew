package me.fevralev.bookofrecepiesnew.exception;

public class FileUploadException extends RuntimeException {

    final private String MESSAGE= "Ошибка выгрузки файла";
    public FileUploadException() {
        super();
        getMESSAGE();
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}

