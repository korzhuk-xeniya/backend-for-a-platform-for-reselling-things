package ru.skypro.homework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({AdsNotFoundException.class, CommentNotFoundException.class, ImageNotFoundException.class,
            UserNotFoundException.class, WrongPasswordException.class})
    public ResponseEntity<Object> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // По умолчанию используем статус 500

        if (ex instanceof AdsNotFoundException || ex instanceof CommentNotFoundException
               || ex instanceof ImageNotFoundException || ex instanceof UserNotFoundException ||
                ex instanceof WrongPasswordException) {
            status = HttpStatus.NOT_FOUND; // Если обнаружено одно из исключений для несуществующего объекта, используем статус 404
        }

        // Логируем ошибку
        logger.error("An error occurred: {}", ex.getMessage(), ex);

        // Возвращаем соответствующий статус ответа
        return new ResponseEntity<>(ex.getMessage(), status);
    }
}
