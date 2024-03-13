package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "TEXT")
    private String text;

    @JoinColumn(name = "ADS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ads;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
