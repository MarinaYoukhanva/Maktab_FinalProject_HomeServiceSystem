package com.freshome.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Expert extends User {

    byte[] profileImage;
    Long score;

    @ManyToMany
    @JoinTable(uniqueConstraints = @UniqueConstraint(
            columnNames = {"sub_services_id", "expert_id"}))
    List<SubService> subServices;

    @OneToOne
    Credit credit;

}
