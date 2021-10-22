package com.ipractice.springApi.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="invited")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitedEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID _id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="class_id")
    private ClassEntity classEntity;
}
