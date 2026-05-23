package com.example.geziGunlugu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kullanicilar")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Boş Constructor (JPA için zorunlu)
    public Kullanici() {
    }

    // Parametreli Constructor
    public Kullanici(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- Diğer Getter ve Setter Metotları ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}