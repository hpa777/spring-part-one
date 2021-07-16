package ru.geekbrains.lesson04springboot.rest;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
