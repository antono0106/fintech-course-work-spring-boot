package com.moroz.persistence.repo;

import com.moroz.persistence.entities.PaymentEntity;
import com.moroz.persistence.entities.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findAllByPaymentStatus(PaymentStatusEntity paymentStatusEntity);
}
