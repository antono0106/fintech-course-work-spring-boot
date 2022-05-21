package com.moroz.parsers;

import com.moroz.model.UserDTO;
import com.moroz.persistence.entities.UserEntity;

public class UserEntityToDTOParser {
    public static UserDTO parse(UserEntity entity) {
        return new UserDTO(entity.getFullName(), entity.getEmailPhoneNumber());
    }
}
