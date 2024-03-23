package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "REG_DATE", nullable = false)
    private Timestamp regDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AVATAR_ID")
    private Image avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getFirstName(), user.getFirstName())
                && Objects.equals(getLastName(), user.getLastName())
                && Objects.equals(getPhone(), user.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getPhone());
    }
}
