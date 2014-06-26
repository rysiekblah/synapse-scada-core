package com.synapse.scada.core.db.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by tomek on 6/25/14.
 */

@Entity
@Table(name = "account")
public class Account {

    private Long id;
    private String lasetName;
    private String firstName;
    private String login;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Length(min = 1, max = 40)
    @Column(name = "last_name")
    public String getLasetName() {
        return lasetName;
    }

    public void setLasetName(String lasetName) {
        this.lasetName = lasetName;
    }

    @NotNull
    @Length(min = 1, max = 40)
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
}

