package com.example.geziGunlugu.controller;

import com.example.geziGunlugu.entity.GeziKaydi;
import com.example.geziGunlugu.service.GeziService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

    private final GeziService geziService;

    public ImageController(GeziService geziService) {
        this.geziService = geziService;
    }

    // Bu metod veritabanındaki byte[] verisini resim olarak döner
    @GetMapping("/gezi/resim/{id}")
    public ResponseEntity<byte[]> getResim(@PathVariable Long id) {
        GeziKaydi gezi = geziService.bul(id);
        if (gezi != null && gezi.getFotograf() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Resim tipini belirtiyoruz
                    .body(gezi.getFotograf());
        }
        return ResponseEntity.notFound().build();
    }
}