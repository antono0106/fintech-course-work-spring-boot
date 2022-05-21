package com.moroz.persistence.repo;

import com.moroz.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByFullName(String name);

    Optional<UserEntity> getUserEntityByEmailPhoneNumber(String emailPhoneNumber);
}
