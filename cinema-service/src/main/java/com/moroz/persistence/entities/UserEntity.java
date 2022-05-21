package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserEntity  {

    @Column(name = "full_name", nullable = false)
    @Setter
    private String fullName;

    @Id
    @Column(name = "email_or_phone_number", nullable = false)
    private String emailPhoneNumber;
}
