package org.springframework.boot.java_sandbox.domain.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.java_sandbox.domain.exception.response.ApiErrorResponse;
import org.springframework.boot.java_sandbox.domain.exception.response.ValidationErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

/*
    Classe qui va gérer les exceptions de l'application de manière globale
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Méthode qui va gérer l'exception ContratNotFoundException => Si un contrat n'est pas trouvé dans la base de données (lors de la recherche par exemple)
    @ExceptionHandler(ContratNotFoundException.class)
    //On précise le type d'exception que la méthode va gérer, ici ContratNotFoundException
    public ResponseEntity<ApiErrorResponse> handleContratNotFoundException(
            ContratNotFoundException e,  //La méthode prend en paramètre l'exception et la requête HTTP
            HttpServletRequest request) {
        //Créer un objet ApiErrorJSON qui va contenir les informations de l'erreur
        ApiErrorResponse apiErrorJSON = new ApiErrorResponse(
                LocalDateTime.now(), //Date et heure actuelle
                HttpStatus.NOT_FOUND.value(), //Statut 404
                HttpStatus.NOT_FOUND.getReasonPhrase(), //Message associé au statut 404
                e.getMessage(), //Message d'erreur de l'exception, spécifié dans ContratNotFoundException
                request.getRequestURI() //Le path de la requête
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorJSON); //Renvoyer le statut 404 accompagné de l'objet ApiErrorJSON
    }

    //Méthode qui va gérer l'exception MethodArgumentNotValidException => Si les données reçues lors d'une requête ne respectent pas les contraintes de validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsResponse> handleValidationExceptions(
            MethodArgumentNotValidException e,
            WebRequest request) {
        List<ValidationErrorsResponse.FieldError> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationErrorsResponse.FieldError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ValidationErrorsResponse errorsResponse = new ValidationErrorsResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getDescription(false),
                fieldErrors
        );

        return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);

    }
}
