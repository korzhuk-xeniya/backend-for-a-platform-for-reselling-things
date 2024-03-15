package ru.skypro.homework.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Size(max=32)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    @Length(min = 4)
    @Length(max = 32)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    @Length(min = 3)
    @Length(max = 16)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @Length(min = 3)
    @Length(max = 16)
    private String lastName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Column(name = "PASSWORD", nullable = false)
    @Length(min = 8)
    @Length(max = 16)
    private String password;

    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate;

    @OneToOne
    @JoinColumn(name = "id")
    @Column(name = "AVATAR", nullable = true)
    private Image avatar;

}
