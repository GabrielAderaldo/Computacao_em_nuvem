package com.gabrieladeraldo.trabalho_01_comp_nuvem.businessrule.user;

import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.User;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.UserRepository;
import com.gabrieladeraldo.trabalho_01_comp_nuvem.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
