package me.fevralev.bookofrecepiesnew.service;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
}


