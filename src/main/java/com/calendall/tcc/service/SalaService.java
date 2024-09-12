package com.calendall.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendall.tcc.model.Sala;
import com.calendall.tcc.repository.SalaRepository;

@Service
public class SalaService implements IService<Sala> {

    @Autowired
    private SalaRepository salaRepository;

    public SalaService(){
    }

    @Override
    public Sala create(Sala sala) {
        salaRepository.save(sala);
        return sala;
    }

    @Override
    public Sala findById(Long id) {
        Optional<Sala> sala = salaRepository.findById(id);
        return sala.orElse(null);
    }

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    @Override
    public boolean update(Sala sala) {
        if(salaRepository.existsById(sala.getId_sala())){
            salaRepository.save(sala);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
       if (salaRepository.existsById(id)){
            salaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
