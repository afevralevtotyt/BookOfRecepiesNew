package me.fevralev.bookofrecepiesnew.service.impl;


import me.fevralev.bookofrecepiesnew.service.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    private int counter;

    @Override
    public int getRequestCount() {
        return ++counter;
    }
}
