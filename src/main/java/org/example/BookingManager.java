package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    //TODO implement functionality as per specification

    public void addBooking(int bookingId, int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, int second,
                           double latStart, double longStart,double latEnd, double longEnd, double cost)
    {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocationGPS locationStart = new LocationGPS(latStart,longStart);
        LocationGPS locationEnd = new LocationGPS(latEnd,longEnd);
        Booking b1 = new Booking(bookingId,passengerId,vehicleId,localDateTime,locationStart,locationEnd,cost);
        boolean found = false;
        for(Booking b: bookingList) {
            if (b.equals(b1)) {
                found = true;
                break;
            }
        }
        if(!found)
        {
            bookingList.add(b1);
        }

    }

    public void displayAllBookings()
    {
        for (Booking b : bookingList) {
            System.out.println(b.toString());
        }
    }
    public void deleteBooking(int bId)
    {
        for (Booking b : bookingList) {
            if(b.getBookingId() == bId) {
                bookingList.remove(b);
                break;
            }
        }
    }















}
