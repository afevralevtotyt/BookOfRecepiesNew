package me.fevralev.bookofrecepiesnew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NO_CONTENT)
public class FileDownloadException extends RuntimeException {
    public FileDownloadException() {
        super("Ошибка скачивания файла");
    }
}

