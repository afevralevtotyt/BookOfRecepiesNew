package me.fevralev.bookofrecepiesnew.exception;

public class FileDownloadException extends RuntimeException {

    final private String MESSAGE= "Ошибка скачивания файла";
    public FileDownloadException() {
        super();
        getMESSAGE();
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}
