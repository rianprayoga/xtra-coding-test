package com.xtra.demo.data.entity;

import org.junit.jupiter.api.Test;

class GenderTest {

    @Test
    void asdasd(){
        Gender m = Gender.valueOf("M");
        assert Gender.valueOf("M").equals(Gender.M);
    }

}