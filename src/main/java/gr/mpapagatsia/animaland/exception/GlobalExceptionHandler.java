package gr.mpapagatsia.animaland.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AnimalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAnimalNotFoundException(AnimalNotFoundException ex) {
        log.error(ex.getDescription());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getDescription()));
    }

    @ExceptionHandler(TrickNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTrickNotFoundException(TrickNotFoundException ex) {
        log.error(ex.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse(HttpStatus.OK.value(), ex.getDescription()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleApiException(RuntimeException ex) {
        log.error("Generic error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
