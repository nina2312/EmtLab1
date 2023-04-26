package com.example.labemt.service.impl;

import com.example.labemt.model.User;
import com.example.labemt.model.enumerations.Role;
import com.example.labemt.model.exceptions.*;
import com.example.labemt.repository.UserRepository;
import com.example.labemt.service.UserService;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= this.userRepository.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
       UserDetails userDetails=new  org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));

        return userDetails ;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || password == null)
            throw new InvalidArgumentsException();

        return this.userRepository
                .findUserByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String name, String surname, String email, String username, String password, String passwordRepeat, Role role) {
        if (name == null  || name.isEmpty() ||  surname == null || surname.isEmpty()){
            throw new InvalidUserCredentialsException();
        }

        if (!password.equals(passwordRepeat)) {
            throw new PasswordNotMatchingException();
        }

        if(this.userRepository.findUserByUsername(username).isPresent())
            throw new UserAlreadyExistsException(username);

        String encryptedPassword = this.passwordEncoder.encode(password);

        User user= new User(username,surname,email,username,encryptedPassword,role);
       return this.userRepository.save(user);

    }

    @Override
    public List<User> findAll() {
      return  this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
    this.userRepository.deleteById(id);
    }

    @Override
    public User edit(Long id, String name, String surname, String email, String username, Role role) {
        User user = this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(role);
        return this.userRepository.save(user);

    }
}
