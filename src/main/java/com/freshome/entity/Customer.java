package com.freshome.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//@Where(clause = "deleted = false")
//@WhereJoinTable(clause = "deleted = false")
//@SecondaryTable(name = "user", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
//@SecondaryRow
public class Customer extends User {

    @OneToOne(optional = false, orphanRemoval = true)
    Credit credit;
}
