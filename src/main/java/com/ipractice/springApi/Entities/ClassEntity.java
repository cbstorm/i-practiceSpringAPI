package com.ipractice.springApi.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "classes")
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "_id",
        scope = UUID.class)
public class ClassEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID _id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "sharing")
    private String sharing;

    @Column(name = "admin_user")
    private String adminUser;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<JoinedEntity> joined;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<InvitedEntity> invited;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<JoinRequestEntity> joinRequest;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Date updatedAt;
}
