package com.hty.iotprivate.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutaoying on 2019/11/30
 */

public class TestDemo {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("DEMO");
        eventBus.register(new EventListener());
        eventBus.post(new Event("HTY"));
    }
}
@Data
@Slf4j
class Event{
    private String message;
    public Event(String message){
        this.message = message;

    }
}
class EventListener{
    String name;
    @Subscribe
    void getName(Event event,String address){
        this.name = event.getMessage();
    }


}