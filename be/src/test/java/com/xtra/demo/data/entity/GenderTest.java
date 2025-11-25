package com.xtra.demo.data.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;

class GenderTest {

    @Test
    void asdasd(){

        long i = 1764041091135L;

        Instant instant = Instant.ofEpochMilli(i);
        String string = instant.toString();
        System.out.println(string);

    }

}