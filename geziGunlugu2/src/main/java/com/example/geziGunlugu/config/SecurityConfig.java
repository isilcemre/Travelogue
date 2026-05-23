package com.example.geziGunlugu.config;

import com.example.geziGunlugu.entity.Kullanici;
import com.example.geziGunlugu.repository.KullaniciRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // Herkese açık sayfalar (Ana sayfa, Kayıt, Login ve Statik Dosyalar)
                        .requestMatchers("/", "/register", "/login", "/css/**", "/js/**").permitAll()
                        // Diğer tüm sayfalar (Geziler vb.) giriş yapmayı zorunlu kılar
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Kendi hazırladığımız login.html
                        .defaultSuccessUrl("/geziler", true) // Giriş başarılıysa buraya git
                        .failureUrl("/login?error=true") // Giriş hatalıysa geri dön
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/") // Çıkış yapınca ana sayfaya dön
                        .permitAll());

        return http.build();
    }

    /**
     * Veritabanından kullanıcıyı bulan ve Spring Security'ye tanıtan metot.
     */
    @Bean
    public UserDetailsService userDetailsService(KullaniciRepository repo) {
        return username -> {
            Kullanici kullanici = repo.findByUsername(username);
            if (kullanici == null) {
                throw new UsernameNotFoundException("Kullanıcı adı bulunamadı: " + username);
            }

            // Veritabanındaki kullanıcıyı Spring Security'nin anladığı User formatına çeviriyoruz
            return User.withUsername(kullanici.getUsername())
                    .password(kullanici.getPassword())
                    .roles("USER") // Varsayılan rol
                    .build();
        };
    }

    /**
     * Şifreleri veritabanında şifrelemeden (düz metin) tuttuğumuz için
     * Spring Security'ye hata vermemesi için "şifreleme yapma" diyoruz.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}