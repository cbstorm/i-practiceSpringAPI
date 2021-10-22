package com.ipractice.springApi.Entities;


import com.ipractice.springApi.Schemas.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="joined")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinedEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID _id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="class_id")
    private ClassEntity classEntity;

    @Column(name="role")
    private Role role;
}
