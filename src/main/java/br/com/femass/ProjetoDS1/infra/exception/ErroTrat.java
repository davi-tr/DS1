package br.com.femass.ProjetoDS1.infra.exception;

import br.com.femass.ProjetoDS1.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

import java.sql.SQLIntegrityConstraintViolationException;

    @RestControllerAdvice
    public class ErroTrat {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarError404(){
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity tratarErro400(DataIntegrityViolationException ex){
            var erros = ex.getRootCause();
            if(erros.toString().contains("Duplicate")){
                return ResponseEntity.badRequest().body("Valor duplicado");
            }


            return ResponseEntity.badRequest().body(new DadosSQL(erros));
        }
        @ExceptionHandler(ValidacaoException.class)
        public ResponseEntity tratarErrorRegra(ValidacaoException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        private record DadosSQL(String mensagem){
            public DadosSQL(String mensagem2){
                this(mensagem2.toUpperCase());
            }
        }

    }

