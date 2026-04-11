package com.apple.inc.user.entities.jpa;

import com.apple.inc.user.constants.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = true)
public class User extends AuditableEntity {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private Section section;

    private String rollNo;
    private String employeeId;
    private String qualification;
}
