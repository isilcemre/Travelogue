package com.example.geziGunlugu.service;

import com.example.geziGunlugu.entity.GeziKaydi;
import com.example.geziGunlugu.repository.GeziRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeziService {

    private final GeziRepository geziRepository;

    public GeziService(GeziRepository geziRepository) {
        this.geziRepository = geziRepository;
    }

    public List<GeziKaydi> kullanicininGezileriniGetir(String username) {
        return geziRepository.findByKullaniciUsername(username);
    }

    public void kaydet(GeziKaydi gezi) {
        geziRepository.save(gezi);
    }

    public GeziKaydi bul(Long id) {
        return geziRepository.findById(id).orElse(null);
    }

    public void sil(Long id) {
        geziRepository.deleteById(id);
    }

    public List<GeziKaydi> ara(String sehirAdi) {
        if (sehirAdi != null && !sehirAdi.isEmpty()) {
            return geziRepository.findBySehirAdiContainingIgnoreCase(sehirAdi);
        }
        return geziRepository.findAll();
    }
}