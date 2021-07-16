package ru.geekbrains.lesson04springboot.controller;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
