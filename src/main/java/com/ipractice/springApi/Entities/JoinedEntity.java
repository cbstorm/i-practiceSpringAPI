package com.ipractice.springApi.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "joined")
@Table(name = "joined")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "_id",
        scope = UUID.class)
public class JoinedEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID _id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;


    @Column(name = "role")
    private String role;
}
