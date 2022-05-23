package com.moroz.persistence.repo;

import com.moroz.persistence.entities.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, Long> {
    Optional<PaymentStatusEntity> findByName(String name);
}
