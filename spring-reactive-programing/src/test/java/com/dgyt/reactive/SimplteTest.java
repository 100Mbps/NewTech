package com.dgyt.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class SimplteTest {

    @Test
    public void testReact(){
        // old school
        String name = "Craig";
        String capitalName = name.toUpperCase();
        String greeting = "Hello, " + capitalName + "!";
        System.out.println(greeting);

        // new fashion
        Mono.just("Craig")
                .map(n -> n.toUpperCase())
                .map(cn -> "Hello, " + cn + "!")
                .subscribe(System.out::println);
    }


}
