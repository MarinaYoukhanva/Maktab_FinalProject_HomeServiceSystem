package com.freshome.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

   @Column(nullable = false)
   String city;

   @Column(nullable = false)
   String street;

//   @Column(nullable = false)
//   String avenue;

   @Column(nullable = false)
   String plaque;

}
