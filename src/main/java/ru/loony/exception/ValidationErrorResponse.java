package ru.loony.exception;


import java.util.List;

public record ValidationErrorResponse (List<Violation> violations){}

