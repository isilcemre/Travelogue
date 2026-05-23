package com.example.geziGunlugu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "geziler")
public class GeziKaydi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ulkeAdi; // Yeni eklediğimiz alan
    private String sehirAdi;

    @Column(length = 1000)
    private String aciklama;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fotograf;

    @ManyToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    // Getter ve Setter metotlarını ekle:
    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public GeziKaydi() {}

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUlkeAdi() { return ulkeAdi; } // Getter
    public void setUlkeAdi(String ulkeAdi) { this.ulkeAdi = ulkeAdi; } // Setter

    public String getSehirAdi() { return sehirAdi; }
    public void setSehirAdi(String sehirAdi) { this.sehirAdi = sehirAdi; }

    public String getAciklama() { return aciklama; }
    public void setAciklama(String aciklama) { this.aciklama = aciklama; }

    public byte[] getFotograf() { return fotograf; }
    public void setFotograf(byte[] fotograf) { this.fotograf = fotograf; }
}