package com.example.MovieWebsite.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.text.ParseException;

public class BaseController {
    @Autowired
    protected ModelMapper modelMapper;
}
