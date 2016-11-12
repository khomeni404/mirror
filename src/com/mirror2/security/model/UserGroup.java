

package com.mirror2.security.model;

import org.hibernate.annotations.CollectionOfElements;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "user_group")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 7389991231663740924L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    @Column (name="group_name")
    private String groupName;

    @ManyToMany
    @CollectionOfElements
    private Set<User> userList = new HashSet<User>();


}
