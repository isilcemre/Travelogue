package com.example.geziGunlugu.repository;

import com.example.geziGunlugu.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Kullanici findByUsername(String username);
}