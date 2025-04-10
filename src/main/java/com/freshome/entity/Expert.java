package com.freshome.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//@SecondaryTable(name = "user", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Expert extends User {

    @Lob
//    @Column(columnDefinition = "BYTEA")
    byte[] profileImage;

    // in getter -> average based on ratings
//    @Transient
    Double score;


    @ManyToMany
    @JoinTable(uniqueConstraints = @UniqueConstraint(
            columnNames = {"sub_services_id", "expert_id"}))
    List<SubService> subServices = new ArrayList<>();

    @OneToOne(optional = false, orphanRemoval = true)
    Credit credit;

}
