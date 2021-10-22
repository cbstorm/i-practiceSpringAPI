package com.ipractice.springApi.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID _id;

    @Column(name ="name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "classEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<JoinedEntity> joined;

    @OneToMany(mappedBy = "classEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<InvitedEntity> invited;

}
