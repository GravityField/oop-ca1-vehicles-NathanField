package org.example;
//Nathan Field

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 * <p>
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 * <p>
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */

public class App {
    public static void main(String[] args) {

//        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");
//
//        // create PassengerStore and load it with passenger records from text file
//        PassengerStore passengerStore = new PassengerStore("passengers.txt");
//        System.out.println("List of all passengers:");
//        passengerStore.displayAllPassengers();
//
//        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
//        System.out.println("List of all Vehicles:");
//        vehicleManager.displayAllVehicles();
//
//        BookingManager bookingManager = new BookingManager();
//
//        System.out.println("Find Vehicle by Registration Number");
//        Vehicle v = vehicleManager.findVehicleByRegNumber("181MN6538107");
//        if(v != null)
//        {
//            System.out.println(v);
//
//        }
//
//        System.out.println();
//
//
//
//        passengerStore.addPassenger("Michael Flint","mf@gmail.com","0834121314", 0,0);
//        passengerStore.displayAllPassengers();
//        System.out.println(" ");
//        passengerStore.addPassenger("Michael Flint","mf@gmail.com","0834121314", 0,0);
//        passengerStore.displayAllPassengers();
//
//        System.out.println("Print 1 passenger");
//
//        passengerStore.findPassengerByName("John Smith");
//        System.out.println("Delete Passenger");
//        passengerStore.deletePassenger("Michael Flint","");
//
//        passengerStore.displayAllPassengers();
//
//        System.out.println("Edit Passenger");
//        passengerStore.editPassenger("Alice", "na@gmail.com","",0.0,1.0);
//        passengerStore.displayAllPassengers();
//
//
//
//
//
//        Vehicle v1 = vehicleManager.findVehicleByRegNumber("181MN6538107");
//        if(v1 != null)
//        {
//
//            bookingManager.addBooking(101,102, v1.getId(), 2021,11,24,12,27,10,54.0, 50.0,20.00, 20.00, 200);
//        }
//
//        System.out.println();
//
//        System.out.println("List of all bookings:");
//        bookingManager.displayAllBookings();
//        System.out.println();
//
//
//        bookingManager.deleteBooking(101);
//        System.out.println("List of all bookings:");
//        bookingManager.displayAllBookings();
//        System.out.println();


        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        BookingManager bookingManager = new BookingManager();

        mainApp(passengerStore, vehicleManager);


        System.out.println("Program exiting... Goodbye");
    }

    public static void mainApp(PassengerStore passengerStore, VehicleManager vehicleManager) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

            printMainMenu();
            int option = inputValidInt(1, 5, "Please Enter Option", "Incorrect Value Entered, Please try again");
            if (option == 1) {
                passengerMenu(passengerStore);
            } else if (option == 2) {
                vehicleMenu(vehicleManager);
            } else if (option == 5) {
                quit = true;
            }


        }


    }

    public static void printMainMenu() {
        System.out.println("\n    Main Menu    ");
        System.out.println("1. Passengers");
        System.out.println("2. Vehicles");
        System.out.println("3. Bookings");
        System.out.println("4. TBD");
        System.out.println("5. Exit");
    }

    public static void printMenu(String selected) {
        System.out.println("\n   " + selected + " Menu    ");
        System.out.println("1. Display " + selected);
        System.out.println("2. Add " + selected);
        System.out.println("3. Edit " + selected);
        System.out.println("4. Delete " + selected);
        System.out.println("5. Exit");
    }

    public static void passengerMenu(PassengerStore passengerStore) {
        boolean quit = false;
        while (!quit) {

            printMenu("Passenger");
            int option = inputValidInt(1, 5, "Please Enter Option", "Incorrect Value Entered, Please try again");
            if (option == 1) {
                displayPassengerMenu(passengerStore);
            } else if (option == 2) {
                addPassengerMenu(passengerStore);
            } else if (option == 5) {
                quit = true;
            }
        }
    }

    public static void vehicleMenu(VehicleManager vehicleManager) {
        boolean quit = false;
        while (!quit) {

            printMenu("Vehicle");
            int option = inputValidInt(1, 5, "Please Enter Option", "Incorrect Value Entered, Please try again");
            if (option == 1) {
                displayVehicleMenu(vehicleManager);
            } else if (option == 2) {
//            addVehicleMenu(vehicleManager);
            } else if (option == 5) {
                quit = true;
            }
        }
    }

    public static void printPassengerDisplayOptions() {
        System.out.println("\n    Display Passenger Menu    ");
        System.out.println("1. Display All Passengers");
        System.out.println("2. Display Passenger By Name");
        System.out.println("3. Exit");
    }

    public static void printVehicleDisplayOptions() {
        System.out.println("\n    Display Vehicle Menu    ");
        System.out.println("1. Display All Vehicles");
        System.out.println("2. Display Vehicle By Type");
        System.out.println("3. Exit");
    }

    public static void displayPassengerMenu(PassengerStore passengerStore) {
        boolean quit = false;
        System.out.println("Display Passenger Chosen");
        while (!quit) {

            printPassengerDisplayOptions();
            Scanner kb = new Scanner(System.in);
            int option = inputValidInt(1, 3, "Please Enter Option", "Incorrect Value Entered, Please try again");
            if (option == 1) {
                passengerStore.displayAllPassengers();
            }
            if (option == 2) {
                System.out.println("Enter Name to Search for");
                String name = kb.nextLine();

                Passenger p = passengerStore.findPassengerByName(name);
                if (p != null) {
                    System.out.println(p);
                } else {
                    System.out.println("Passenger Not Found");
                }
            }

            quit = true;
        }


    }

    public static void addPassengerMenu(PassengerStore passengerStore) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Add a Passenger Chosen");

        System.out.println("Enter Name:");
        String name = kb.nextLine();
        System.out.println("Enter Email:");
        String email = kb.nextLine();
        System.out.println("Enter Phone Number:");
        String phone = kb.nextLine();
        System.out.println("Enter Latitude");
        double latitude = kb.nextDouble();
        System.out.println("Enter longitude");
        double longitude = kb.nextDouble();
        boolean found = passengerStore.addPassenger(name, email, phone, latitude, longitude);
        if (!found) {
            System.out.println("Passenger was added");
        } else {
            System.out.println("Passenger already exists");
        }
    }

    public static void displayVehicleMenu(VehicleManager vehicleManager) {
        boolean quit = false;
        System.out.println("Display Vehicle Chosen");
        while (!quit) {

            printVehicleDisplayOptions();
            Scanner kb = new Scanner(System.in);
            int option = inputValidInt(1, 3, "Please Enter Option", "Incorrect Value Entered, Please try again");
            if (option == 1) {
                vehicleManager.displayAllVehicles();
            } else if (option == 2) {
                System.out.println("Enter Type of Vehicle to Search for");
                String type = kb.nextLine();

                ArrayList<Vehicle> vehicles = vehicleManager.findVehiclesByType(type);

                if (vehicles != null) {
                    System.out.println(vehicles);
                } else {
                    System.out.println("No vehicles of type " + type + " found");
                }
            }

            quit = true;
        }


    }

    public static int inputValidInt(int min, int max, String prompt, String error) {
        Scanner kb = new Scanner(System.in);
        System.out.println(prompt);
        int num = kb.nextInt();

        while (num < min || num > max) {
            System.out.println(error);
            System.out.println(prompt);
            num = kb.nextInt();
        }

        return num;
    }
}
