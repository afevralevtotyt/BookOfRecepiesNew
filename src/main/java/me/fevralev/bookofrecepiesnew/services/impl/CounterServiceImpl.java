package me.fevralev.bookofrecepiesnew.services.impl;


import me.fevralev.bookofrecepiesnew.services.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    private int counter;

    @Override
    public int getRequestCount() {
        return ++counter;
    }
}
