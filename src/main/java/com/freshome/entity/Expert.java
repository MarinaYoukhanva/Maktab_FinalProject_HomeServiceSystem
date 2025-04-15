package com.freshome.entity;

import com.freshome.entity.base.BaseEntity;
import com.freshome.entity.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SoftDelete
public class Expert extends BaseEntity<Long> {

    @Column(unique = true, length = 15)
    String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Lob
    byte[] profileImage;

    Double score;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    User user;

    @ManyToMany
    @JoinTable(uniqueConstraints = @UniqueConstraint(
            columnNames = {"sub_services_id", "expert_id"}))
    List<SubService> subServices = new ArrayList<>();

    @OneToOne(optional = false, orphanRemoval = true)
    Credit credit;

}
