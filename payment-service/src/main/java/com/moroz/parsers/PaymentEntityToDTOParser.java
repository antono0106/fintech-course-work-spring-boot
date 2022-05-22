package com.moroz.parsers;

import com.moroz.model.PaymentDTO;
import com.moroz.persistence.entities.PaymentEntity;

public class PaymentEntityToDTOParser {
    public static PaymentDTO parse(PaymentEntity entity) {
        return new PaymentDTO(entity.getId(), entity.getAmount(),
                entity.getCard(), entity.getPaymentStatus().getId());
    }
}
