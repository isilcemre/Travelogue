package com.example.geziGunlugu.controller;

import com.example.geziGunlugu.entity.GeziKaydi; // Kendi sınıf ismini yaz
import com.example.geziGunlugu.entity.Kullanici;
import com.example.geziGunlugu.repository.GeziRepository;
import com.example.geziGunlugu.repository.KullaniciRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal; // Bu import mutlaka olmalı

@Controller
public class GeziController {

    private final GeziRepository geziRepository;
    private final KullaniciRepository kullaniciRepository;

    public GeziController(GeziRepository geziRepository, KullaniciRepository kullaniciRepository) {
        this.geziRepository = geziRepository;
        this.kullaniciRepository = kullaniciRepository;
    }


    // Yeni yer ekleme formunu açan metot (GET)
    @GetMapping("/save")
    public String yeniGeziFormu(Model model) {
        // Formun içinde kullanılacak boş bir GeziKaydi nesnesi oluşturup gönderiyoruz
        model.addAttribute("gezi", new GeziKaydi());
        return "form"; // templates altındaki form.html (veya dosya adın neyse) sayfasına gider
    }

    @GetMapping("/geziler/sil/{id}")
    public String geziSil(@PathVariable("id") Long id) {
        geziRepository.deleteById(id);
        return "redirect:/geziler";
    }

    // LİSTELEME: Sadece giriş yapanın gezilerini getirir
    @GetMapping("/geziler")
    public String listele(Model model, Principal principal) {
        // Principal sayesinde o an sisteme giriş yapmış kişinin adını alıyoruz
        String username = principal.getName();

        // Tüm gezileri değil, sadece bu kullanıcıya ait olanları modele ekliyoruz
        model.addAttribute("geziler", geziRepository.findByKullaniciUsername(username));
        return "list";
    }

    // KAYDETME: Yeni geziyi kullanıcıyla ilişkilendirir
    @PostMapping("/save")
    public String geziKaydet(@ModelAttribute("gezi") GeziKaydi gezi,
                             @RequestParam("file") org.springframework.web.multipart.MultipartFile file,
                             Principal principal) throws java.io.IOException {

        // 1. Kullanıcıyı bağla
        String username = principal.getName();
        Kullanici aktifKullanici = kullaniciRepository.findByUsername(username);
        gezi.setKullanici(aktifKullanici);

        // 2. Fotoğrafı işle
        if (!file.isEmpty()) {
            gezi.setFotograf(file.getBytes());
        }

        // 3. Kaydet
        geziRepository.save(gezi);

        return "redirect:/geziler";
    }

    @GetMapping("/profil")
    public String profilSayfasi(Model model, Principal principal) {
        model.addAttribute("currentUsername", principal.getName());
        return "profile";
    }

    @PostMapping("/profil/guncelle")
    public String profilGuncelle(@RequestParam String username,
                                 @RequestParam(required = false) String password,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        String currentUsername = principal.getName();

        // Aynı kullanıcı adı kontrolü
        if (username.trim().equals(currentUsername)) {
            redirectAttributes.addFlashAttribute("error",
                    "Yeni kullanıcı adı mevcut adınızdan farklı olmalıdır!");
            return "redirect:/geziler";
        }

        Kullanici kullanici = kullaniciRepository.findByUsername(currentUsername);

        // Başkası tarafından alınmış mı kontrolü
        if (kullaniciRepository.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error",
                    "Bu kullanıcı adı zaten alınmış!");
            return "redirect:/geziler";
        }

        kullanici.setUsername(username);

        if (password != null && !password.isEmpty()) {
            kullanici.setPassword(password);
        }

        kullaniciRepository.save(kullanici);

        return "redirect:/login?success";
    }

    @PostMapping("/profil/guncelle/username")
    public String updateUsername(@RequestParam String username, Principal principal, RedirectAttributes ra) {
        Kullanici user = kullaniciRepository.findByUsername(principal.getName());
        user.setUsername(username);
        kullaniciRepository.save(user);

        ra.addFlashAttribute("success", "Kullanıcı adı başarıyla değiştirildi. Lütfen tekrardan giriş yapınız.");
        return "redirect:/login";
    }

    @PostMapping("/profil/guncelle/password")
    @ResponseBody // Sayfa değil, düz metin dönmek için
    public ResponseEntity<String> updatePassword(@RequestParam String oldPassword,
                                                 @RequestParam String password,
                                                 Principal principal) {

        Kullanici user = kullaniciRepository.findByUsername(principal.getName());

        // 1. Eski şifre kontrolü
        if (!user.getPassword().equals(oldPassword)) {
            return ResponseEntity.badRequest().body("Mevcut şifreniz hatalı!");
        }

        // 2. Aynı şifre kontrolü
        if (oldPassword.equals(password)) {
            return ResponseEntity.badRequest().body("Yeni şifre eskisinden farklı olmalıdır!");
        }

        // Kaydet
        user.setPassword(password);
        kullaniciRepository.save(user);

        return ResponseEntity.ok("OK");
    }


}