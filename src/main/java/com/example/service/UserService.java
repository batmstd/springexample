package com.example.service;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.NotFoundException;
import com.example.model.UserDTO;
import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final static int MAX_LENGTH = 20;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<UserEntity> optional = repository.findById(id);
        return optional.map(this::map).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public UserDTO save(UserDTO dto) {
        String login = dto.getLogin();
        checkLengthLogin(login);
        checkUniqueLogin(login);
        UserEntity entity = new UserEntity(login);
        return map(repository.save(entity));
    }

    private void checkLengthLogin(String login) {
        if (login.length() > MAX_LENGTH) {
            throw new BadRequestException("max length is " + MAX_LENGTH);
        }
    }

    private void checkUniqueLogin(String login) {
        if (repository.findByLogin(login).isPresent()) {
            throw new BadRequestException("login is not unique");
        }
    }

    private UserDTO map(UserEntity e) {
        return UserDTO.of(e.getId(), e.getLogin());
    }
}
