
package com.apiandre.crud.services;

import com.apiandre.crud.dto.LoginRequest;
import com.apiandre.crud.models.UserModel;
import com.apiandre.crud.repositories.IUserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private IUserRepository iuserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateUser(LoginRequest request) {
        Optional<UserModel> userOptional = iuserRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            return passwordEncoder.matches(request.getPassword(), user.getPassword());
        }
        return false;
    }  
}
