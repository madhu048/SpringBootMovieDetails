package com.demo.demo.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NotFoundEx.class)
    public ResponseEntity<CustomeEx> handleNotFoundEx(NotFoundEx ex) {
        CustomeEx customEx = new CustomeEx();
        customEx.setStatusMessage("Not Found");
        customEx.setStatusCode(404);
        customEx.setErrorMessage(ex.getMessage());
        customEx.setTimeStamp(java.time.LocalDateTime.now());
        return ResponseEntity.status(404).body(customEx);
    }

    @ExceptionHandler(UserAlreadyExit.class)
    public ResponseEntity<CustomeEx> handleUserAlreadyExitEx(UserAlreadyExit ex) {
        CustomeEx customEx = new CustomeEx();
        customEx.setStatusMessage("User Already Exists");
        customEx.setStatusCode(409);
        customEx.setErrorMessage(ex.getMessage());
        customEx.setTimeStamp(java.time.LocalDateTime.now());
        return ResponseEntity.status(409).body(customEx);
    }

}
