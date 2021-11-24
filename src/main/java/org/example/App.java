package org.example;
//Nathan Field

import javax.xml.stream.Location;
import java.time.LocalDateTime;


/**
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

        // create PassengerStore and load it with passenger records from text file
        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        System.out.println("List of all passengers:");
        passengerStore.displayAllPassengers();

        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        System.out.println("List of all Vehicles:");
        vehicleManager.displayAllVehicles();

        BookingManager bookingManager = new BookingManager();

        System.out.println("Find Vehicle by Registration Number");
        Vehicle v = vehicleManager.findVehicleByRegNumber("181MN6538107");
        if(v != null)
        {
            System.out.println(v);

        }

        System.out.println();



        passengerStore.addPassenger("Michael Flint","mf@gmail.com","0834121314", 0,0);
        passengerStore.displayAllPassengers();
        System.out.println(" ");
        passengerStore.addPassenger("Michael Flint","mf@gmail.com","0834121314", 0,0);
        passengerStore.displayAllPassengers();

        System.out.println("Print 1 passenger");

        passengerStore.printPassenger(101);
        System.out.println("Delete Passenger");
        passengerStore.deletePassenger("Michael Flint","");

        passengerStore.displayAllPassengers();

        System.out.println("Edit Passenger");
        passengerStore.editPassenger("Alice", "na@gmail.com","",0.0,1.0);
        passengerStore.displayAllPassengers();





        Vehicle v1 = vehicleManager.findVehicleByRegNumber("181MN6538107");
        if(v1 != null)
        {

            bookingManager.addBooking(101,102, v1.getId(), 2021,11,24,12,27,10,54.0, 50.0,20.00, 20.00, 200);
        }

        System.out.println();




        System.out.println("List of all bookings:");
        bookingManager.displayAllBookings();
        System.out.println();


        bookingManager.deleteBooking(101);
        System.out.println("List of all bookings:");
        bookingManager.displayAllBookings();
        System.out.println();






        System.out.println("Program exiting... Goodbye");
    }
}
