package com.example.geziGunlugu.repository;

import com.example.geziGunlugu.entity.GeziKaydi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GeziRepository extends JpaRepository<GeziKaydi, Long> {

    List<GeziKaydi> findBySehirAdiContainingIgnoreCase(String sehirAdi);

    // Sadece giriş yapan kullanıcının gezilerini getirmek için lazım olan metot:
    List<GeziKaydi> findByKullaniciUsername(String username);
}