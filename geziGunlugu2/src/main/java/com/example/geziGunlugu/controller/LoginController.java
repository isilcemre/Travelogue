package com.example.geziGunlugu.controller;

import com.example.geziGunlugu.entity.Kullanici; // BURASI ÇOK ÖNEMLİ: Kırmızılığı bu satır bitirir
import com.example.geziGunlugu.repository.KullaniciRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final KullaniciRepository kullaniciRepository;

    // Constructor Injection
    public LoginController(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    // Ana sayfa (Hoş geldiniz yazısı olan yer)
    @GetMapping("/")
    public String anaSayfa() {
        return "index";
    }

    // Login sayfası
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Kayıt olma formu (GET)
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new Kullanici()); // "user" isminde boş bir kalıbı register.html'e gönderiyoruz
        return "register";
    }

    // Kayıt olma işlemi (POST)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Kullanici kullanici, Model model) {
        // 1. Bu kullanıcı adı zaten var mı kontrol et
        Kullanici mevcutKullanici = kullaniciRepository.findByUsername(kullanici.getUsername());

        if (mevcutKullanici != null) {
            // 2. Eğer varsa, hata mesajı ekle ve tekrar kayıt sayfasına gönder
            model.addAttribute("hata", "Bu kullanıcı adı zaten alınmış!");
            return "register";
        }

        // 3. Yoksa kaydet ve login'e yönlendir
        kullaniciRepository.save(kullanici);
        return "redirect:/login?success=true";
    }

    
}