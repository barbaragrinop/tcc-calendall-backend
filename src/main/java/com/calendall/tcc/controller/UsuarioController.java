package com.calendall.tcc.controller;

import org.springframework.http.ResponseEntity;

public class UsuarioController implements IController {

    @Override
    public ResponseEntity getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ResponseEntity<String> get(Long id) {
        return ResponseEntity.ok("Sucesso!");
    }

    @Override
    public ResponseEntity post() {
        throw new UnsupportedOperationException("Unimplemented method 'post'");
    }

    @Override
    public ResponseEntity put(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public ResponseEntity patch(Object obj) {
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

    @Override
    public ResponseEntity delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
