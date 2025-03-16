package com.freshome.entity.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

   String city;
   String street;
   String avenue;
   Integer plaque;

}
