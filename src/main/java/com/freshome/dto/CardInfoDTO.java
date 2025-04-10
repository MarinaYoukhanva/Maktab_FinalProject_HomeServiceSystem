package com.freshome.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CardInfoDTO(

        @NotBlank(message = "cardNumber can not be null or blank! ")
        @Size(min = 16, max = 16, message = "cardNumber must have 16 digits! ")
        @Pattern(regexp = "^\\d{16}$", message = "invalid format for cardNumber! ")
        String cardNumber,


        @NotBlank(message = "cvv2 can not be null or blank! ")
        @Size(min = 3, max = 3, message = "cvv2 must have 3 digits! ")
        @Pattern(regexp = "^\\d{3}$", message = "invalid format for cvv2! ")
        String cvv2,

        @FutureOrPresent(message = "credit card is expired! ")
        LocalDate expiryDate,

        @NotBlank(message = "cardPassword can not be null or blank! ")
        @Size(min = 4, max = 8, message = "cardPassword must have 4-8 digits! ")
        @Pattern(regexp = "^\\d{4,8}$", message = "invalid format for cardPassword! ")
        String cardPassword

) {
}
