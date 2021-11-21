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

    public void addBooking(int bookingId, int passengerId, int vehicleId, LocalDateTime bookingDateTime,
                           LocationGPS startLocation, LocationGPS endLocation, double cost)
    {
        Booking B = new Booking(bookingId,passengerId,vehicleId,bookingDateTime,startLocation,endLocation,cost);
        boolean found = false;
        for(Booking b: bookingList) {
            if (b.equals(B)) {
                found = true;
                break;
            }
        }
        if(!found)
        {
            bookingList.add(B);
        }

    }















}
