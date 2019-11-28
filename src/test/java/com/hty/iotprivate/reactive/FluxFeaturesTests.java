package com.hty.iotprivate.reactive;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import reactor.core.publisher.Flux;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class FluxFeaturesTests {
    private static Logger log = LoggerFactory.getLogger(FluxFeaturesTests.class);

    private static List<String> COLORS = Arrays.asList("red", "white", "blue");

    private Flux<String> flux;

    @Before
    public void generate() throws Exception {
        // this.flux = Flux.range(1, 10).map(i -> COLORS.get(random.nextInt(3)));
//        this.flux = Flux.fromIterable(COLORS);

//        log.info(this.flux.toString());
        Flux<String> flux = Flux.just("red", "white", "blue");
        this.flux = flux
                .log()
                .map(String::toUpperCase);
    }

    @Test
    public void operate() throws Exception {

        Flux<String> flux = Flux.just("red", "white", "blue").log().map(String::toUpperCase);

//        flux
//                .log()
//                .map(String::toUpperCase);
        flux.subscribe();
//        this.flux.log().map(String::toUpperCase);
        // Nothing happened. No logs, nothing.
    }

    @Test
    public void subscribe() throws Exception {
        this.flux.log().map(String::toUpperCase).subscribe();
        // Logs the subscription, an unbounded request, all elements and finally
        // completion.
    }

    @Test
    public void consume() throws Exception {
        this.flux.log().map(String::toUpperCase).subscribe(System.out::println);
        // Same as above but items are printed as they emerge from the end of
        // the operator
        // chain
    }
    @Test
    public void subscription() throws Exception {
        this.flux.log().map(String::toUpperCase).subscribe(
                new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
        // Logs the subscription, requests 2 at a time, all elements and
        // completion.
    }
}
