package com.ipractice.springApi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="invited")
@Table(name="invited")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitedEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID _id;

    @Column(name="user_id",nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name="class_id")
    @JsonBackReference(value = "class")
    private ClassEntity classEntity;
}
