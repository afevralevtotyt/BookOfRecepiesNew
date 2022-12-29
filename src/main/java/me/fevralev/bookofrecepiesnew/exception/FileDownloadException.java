package me.fevralev.bookofrecepiesnew.exception;

public class FileDownloadException extends RuntimeException {
    public FileDownloadException() {
        super("Ошибка скачивания файла");
    }
}

