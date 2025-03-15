package com.koustav.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME", length = 25)
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Transient         //as we don't wanna store in db
    private List<Rating> ratings = new ArrayList<>();

}
