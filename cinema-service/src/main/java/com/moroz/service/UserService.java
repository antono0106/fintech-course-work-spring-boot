package com.moroz.service;

import com.moroz.exceptions.CinemaNotFoundException;
import com.moroz.exceptions.UserNotFoundException;
import com.moroz.model.UserDTO;
import com.moroz.parsers.UserEntityToDTOParser;
import com.moroz.persistence.entities.UserEntity;
import com.moroz.persistence.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService {

    @Getter
    private final UserRepository userRepository;

    private final Pattern phoneNumberPattern = Pattern.compile("^(?:\\+38)?(0\\d{9})$");
    private final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

    public List<UserDTO> getUsers() {
        List<UserEntity> entities = userRepository.findAll();

        List<UserDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new UserDTO(x.getFullName(), x.getEmailPhoneNumber())));

        log.info("Find all users");

        return dtoList;
    }

    public List<UserDTO> getUsersByFullName(String fullName) {
        List<UserEntity> entities = userRepository.findAllByFullName(fullName);

        List<UserDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new UserDTO(x.getFullName(), x.getEmailPhoneNumber())));

        log.info("Find all users by email or phone number");

        return dtoList;
    }

    public UserDTO getUserByEmailPhoneNumber(String emailPhoneNumber) {
        UserEntity entity = userRepository.getUserEntityByEmailPhoneNumber(emailPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        log.info("Found :" + entity);

        return UserEntityToDTOParser.parse(entity);
    }

    public UserDTO registerUser(String fullName, String emailPhoneNumber) {
        UserEntity entity = null;

        if (fullName == null || emailPhoneNumber == null
                || !emailPattern.matcher(emailPhoneNumber).matches()
                || !phoneNumberPattern.matcher(emailPhoneNumber).matches()) {
            log.error("Cannot save user ", new RuntimeException());
            throw new RuntimeException();
        } else {
            entity = new UserEntity(fullName, emailPhoneNumber);
        }
        userRepository.save(entity);

        log.info("Saved " + entity);

        return UserEntityToDTOParser.parse(entity);
    }

    public void deleteUserByEmailPhoneNumber(String emailPhoneNumber) {
        if (emailPhoneNumber == null
                || !emailPattern.matcher(emailPhoneNumber).matches()
                || !phoneNumberPattern.matcher(emailPhoneNumber).matches()) {
            log.error("Cannot delete user", new RuntimeException());
            throw new RuntimeException("Wrong format of email or phone number");
        }

        UserEntity entity = userRepository.getUserEntityByEmailPhoneNumber(emailPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(entity);
        log.info("Deleted " + entity);
    }
}
