package me.fevralev.bookofrecepiesnew.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException() {
        super("Ошибка выгрузки файла");
    }
}

