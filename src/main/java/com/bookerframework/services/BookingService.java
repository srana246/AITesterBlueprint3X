package com.bookerframework.services;

import com.bookerframework.constants.EndPoints;
import com.bookerframework.models.Booking;
import com.bookerframework.utils.RequestSpecUtil;
import io.restassured.response.Response;
import java.util.Map;

public class BookingService {
  public static Response createBooking(Booking booking) {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(booking)
        .when()
        .post(EndPoints.BOOKING);
  }

  public static Response getBookingIds() {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .when()
        .get(EndPoints.BOOKING);
  }

  public static Response getBooking(int id) {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .when()
        .get(EndPoints.BOOKING + "/" + id);
  }

  public static Response updateBooking(int id, Booking booking) {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecificationWithToken(AuthService.getToken()))
        .body(booking)
        .when()
        .put(EndPoints.BOOKING + "/" + id);
  }

  public static Response partialUpdateBooking(int id, Map<String, Object> patchData) {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecificationWithToken(AuthService.getToken()))
        .body(patchData)
        .when()
        .patch(EndPoints.BOOKING + "/" + id);
  }

  public static Response deleteBooking(int id) {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecificationWithToken(AuthService.getToken()))
        .when()
        .delete(EndPoints.BOOKING + "/" + id);
  }

  public static Response healthCheck() {
    return io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .when()
        .get(EndPoints.PING);
  }
}
