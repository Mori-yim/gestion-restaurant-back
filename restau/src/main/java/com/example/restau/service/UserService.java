package com.example.restau.service;


//import com.example.restau.dto.request.RegisterRequest;
//import com.example.restau.exception.BadRequestException;
//import com.example.restau.exception.ResourceNotFoundException;
//import com.example.restau.model.User;
//import com.example.restau.model.enums.Role;
//import com.example.restau.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + email));
//    }
//
//    public User register(RegisterRequest request) {
//        // Vérifier si l'email existe déjà
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new BadRequestException("Email déjà utilisé");
//        }
//
//        // Créer le nouvel utilisateur
//        User user = User.builder()
//                .nomComplet(request.getNomComplet())
//                .email(request.getEmail())
//                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
//                .telephone(request.getTelephone())
//                .adresse(request.getAdresse())
//                .role(Role.CLIENT)
//                .actif(true)
//                .dateInscription(LocalDateTime.now())
//                .build();
//
//        return userRepository.save(user);
//    }
//
//    public User getCurrentUser() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id));
//    }
//
//    public User updateUser(Long id, User userDetails) {
//        User user = getUserById(id);
//
//        if (userDetails.getNomComplet() != null) {
//            user.setNomComplet(userDetails.getNomComplet());
//        }
//        if (userDetails.getTelephone() != null) {
//            user.setTelephone(userDetails.getTelephone());
//        }
//        if (userDetails.getAdresse() != null) {
//            user.setAdresse(userDetails.getAdresse());
//        }
//
//        return userRepository.save(user);
//    }
//
//    public void changePassword(Long id, String oldPassword, String newPassword) {
//        User user = getUserById(id);
//
//        if (!passwordEncoder.matches(oldPassword, user.getMotDePasse())) {
//            throw new BadRequestException("Ancien mot de passe incorrect");
//        }
//
//        user.setMotDePasse(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//    }
//
//    public void toggleUserStatus(Long id) {
//        User user = getUserById(id);
//        user.setActif(!user.isActif());
//        userRepository.save(user);
//    }
//}





import com.example.restau.dto.request.RegisterRequest;
import com.example.restau.exception.BadRequestException;
import com.example.restau.exception.ResourceNotFoundException;
import com.example.restau.model.User;
import com.example.restau.model.enums.Role;
import com.example.restau.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {  // N'implémente plus UserDetailsService

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email déjà utilisé");
        }

        User user = User.builder()
                .nomComplet(request.getNomComplet())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .telephone(request.getTelephone())
                .adresse(request.getAdresse())
                .role(Role.CLIENT)
                .actif(true)
                .dateInscription(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
       return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
    }

    public void toggleUserStatus(Long id) {
        User user = getUserById(id);
        user.setActif(!user.isActif());
        userRepository.save(user);
    }
}