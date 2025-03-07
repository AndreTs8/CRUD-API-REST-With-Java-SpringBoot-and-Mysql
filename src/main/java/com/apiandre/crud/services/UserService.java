
package com.apiandre.crud.services;

import com.apiandre.crud.models.UserModel;
import com.apiandre.crud.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔐 Encripta la contraseña
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id) {
        UserModel user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // 🔐 Vuelve a encriptar la nueva contraseña

        return userRepository.save(user);
    }

    public Boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario con ID " + id + " no encontrado");
        }
        userRepository.deleteById(id);
        return true;
    }
    
}