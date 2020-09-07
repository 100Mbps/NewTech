package com.dgyt.reactive;

import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;


public class SimpleTest {

    @Test
    public void testReact() {
        // old school
        String name = "Craig";
        String capitalName = name.toUpperCase();
        String greeting = "Hello, " + capitalName + "!";
        System.out.println(greeting);

        // new fashion
        Mono.just("Craig")
                .map(String::toUpperCase)
                .map(cn -> "Hello, " + cn + "!")
                .subscribe(System.out::println);
    }

    @Test
    public void createAFluxJust() {
        Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape");
        fruitFlux.subscribe(element -> System.out.println("Here's some fruit:" + element));
        StepVerifier.create(fruitFlux).
                expectNext("Apple").
                expectNext("Orange").
                expectNext("Grape").
                verifyComplete();
         Stream<String> fruitStream =
                Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
         Optional<String> result =  fruitStream.map(f->"hell0"+f).findFirst();
         result.ifPresent(System.out::println);
    }
    @Test
    public void intervalOfDataFlux() throws InterruptedException {

        Flux<Long> intervalFlux =
                Flux.interval(Duration.ofSeconds(0)).take(5);
        intervalFlux.subscribe(System.out::println);
        // Reactor用到的thread.daemon = true;
        // 否则无法正常输入内容，随着主线程退出，Reactor所用到的线程一并退出。
        for(;;){
            Thread.sleep(10000000);
        }
    }


    public static void main(String[] args) {
        Flux<Long> intervalFlux =
                Flux.interval(Duration.ofMillis(1)).take(100000);
        intervalFlux.subscribe(System.out::println);
    }

}
