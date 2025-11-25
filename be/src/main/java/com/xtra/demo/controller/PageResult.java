package com.xtra.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResult<T> {

    private List<T> data;
    private long total;

}
