package com.bookerframework.tests;

import com.bookerframework.base.BaseTest;
import com.bookerframework.constants.EndPoints;
import com.bookerframework.models.Booking;
import com.bookerframework.models.BookingDates;
import com.bookerframework.services.BookingService;
import com.bookerframework.utils.RequestSpecUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class BookingTests extends BaseTest {
  @Test(groups = {"smoke", "regression"})
  public void createBookingValid() {
    Booking booking = new Booking("Jim", "Brown", 150, true, new BookingDates("2024-01-01", "2024-01-10"), "Breakfast");
    BookingService.createBooking(booking)
        .then()
        .statusCode(200)
        .body("booking.firstname", equalTo("Jim"));
  }

  @Test(groups = {"regression"})
  public void getBookingIdsValid() {
    BookingService.getBookingIds()
        .then()
        .statusCode(200);
  }

  @Test(groups = {"regression"})
  public void getBookingByIdValid() {
    Booking booking = new Booking("Emma", "Stone", 220, false, new BookingDates("2024-02-01", "2024-02-05"), "Lunch");
    int bookingId = BookingService.createBooking(booking).then().statusCode(200).extract().path("bookingid");
    BookingService.getBooking(bookingId)
        .then()
        .statusCode(200)
        .body("firstname", equalTo("Emma"));
  }

  @Test(groups = {"regression"})
  public void updateBookingValid() {
    Booking booking = new Booking("Olivia", "Parker", 300, true, new BookingDates("2024-03-01", "2024-03-07"), "Dinner");
    int bookingId = BookingService.createBooking(booking).then().statusCode(200).extract().path("bookingid");
    Booking updatedBooking = new Booking("Olivia", "Parker", 350, true, new BookingDates("2024-03-01", "2024-03-08"), "Breakfast");
    BookingService.updateBooking(bookingId, updatedBooking)
        .then()
        .statusCode(200)
        .body("totalprice", equalTo(350));
  }

  @Test(groups = {"regression"})
  public void partialUpdateBookingValid() {
    Booking booking = new Booking("Liam", "Jones", 180, false, new BookingDates("2024-04-01", "2024-04-04"), "Dinner");
    int bookingId = BookingService.createBooking(booking).then().statusCode(200).extract().path("bookingid");
    Map<String, Object> patchData = new HashMap<>();
    patchData.put("totalprice", 200);
    BookingService.partialUpdateBooking(bookingId, patchData)
        .then()
        .statusCode(200)
        .body("totalprice", equalTo(200));
  }

  @Test(groups = {"regression"})
  public void deleteBookingValid() {
    Booking booking = new Booking("Noah", "Davis", 125, true, new BookingDates("2024-05-01", "2024-05-03"), "Breakfast");
    int bookingId = BookingService.createBooking(booking).then().statusCode(200).extract().path("bookingid");
    BookingService.deleteBooking(bookingId)
        .then()
        .statusCode(201);
  }

  @Test(groups = {"regression"})
  public void healthCheckValid() {
    BookingService.healthCheck()
        .then()
        .statusCode(201);
  }

  @Test(groups = {"negative"})
  public void createBookingInvalidMissingField() {
    Booking booking = new Booking(null, "Smith", 120, true, new BookingDates("2024-06-01", "2024-06-05"), "Breakfast");
    BookingService.createBooking(booking)
        .then()
        .statusCode(500);
  }

  @Test(groups = {"negative"})
  public void getBookingByIdInvalid() {
    BookingService.getBooking(999999)
        .then()
        .statusCode(404);
  }

  @Test(groups = {"negative"})
  public void updateBookingWithoutAuth() {
    Booking booking = new Booking("Ava", "Lee", 210, true, new BookingDates("2024-07-01", "2024-07-05"), "Lunch");
    io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(booking)
        .when()
        .put(EndPoints.BOOKING + "/1")
        .then()
        .statusCode(403);
  }

  @Test(groups = {"negative"})
  public void partialUpdateBookingWithoutAuth() {
    Map<String, Object> patchData = new HashMap<>();
    patchData.put("totalprice", 250);
    io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(patchData)
        .when()
        .patch(EndPoints.BOOKING + "/1")
        .then()
        .statusCode(403);
  }

  @Test(groups = {"negative"})
  public void deleteBookingWithoutAuth() {
    io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .when()
        .delete(EndPoints.BOOKING + "/1")
        .then()
        .statusCode(403);
  }

  @Test(groups = {"negative"})
  public void healthCheckInvalidMethod() {
    io.restassured.RestAssured.given()
        .spec(com.bookerframework.utils.RequestSpecUtil.getRequestSpecification())
        .when()
        .post("/ping")
        .then()
        .statusCode(405);
  }
}
