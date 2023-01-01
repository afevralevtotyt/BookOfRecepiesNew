package me.fevralev.bookofrecepiesnew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FileReadException extends RuntimeException{
    public FileReadException() {
        super("Ошибка чтения файла");
    }
}
