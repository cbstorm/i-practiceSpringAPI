package com.ipractice.springApi.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name="id",unique = true,nullable = false,updatable = false)
    private String _id;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<JoinedEntity> joinedClasses;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<InvitedEntity> invitedClasses;

}
