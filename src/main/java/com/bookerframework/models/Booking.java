package com.bookerframework.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {
  private String firstname;
  private String lastname;
  private int totalprice;
  private boolean depositpaid;
  private BookingDates bookingdates;
  private String additionalneeds;
}
