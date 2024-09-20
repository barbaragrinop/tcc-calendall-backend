package com.calendall.tcc.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.calendall.tcc.model.dto.SalaDTO;

public interface IController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> get(Long id);
    ResponseEntity<T> post(SalaDTO obj); 
    ResponseEntity<T> put(T obj);
    ResponseEntity<T> patch(T obj);
    ResponseEntity<T> delete(Long id);
}
