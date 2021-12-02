package org.example;
//Nathan Field

import java.io.IOException;
import java.util.*;

        public class App {
            // Components (objects) used in this App
            PassengerStore passengerStore;  // encapsulates access to list of Passengers
            VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
            BookingManager bookingManager;  // deals with all bookings

            public static void main(String[] args) {
                App app = new App();
                app.start();
            }

            public void start() {
                // create PassengerStore and load all passenger records from text file
                passengerStore = new PassengerStore("passengers.txt");

                // create VehicleManager, and load all vehicles from text file
                vehicleManager = new VehicleManager("vehicles.txt");

                bookingManager = new BookingManager("bookings.txt");
                try {
                    displayMainMenu();        // User Interface - Menu
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //pMgr.saveToFile();
                vehicleManager.saveVehiclesToFile("vehicles.txt");
                System.out.println("Program ending, Goodbye");
            }

            private void displayMainMenu() throws IOException {

                final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                        + "1. Passengers\n"
                        + "2. Vehicles\n"
                        + "3. Bookings\n"
                        + "4. Exit\n"
                        + "Enter Option [1,4]";

                final int PASSENGERS = 1;
                final int VEHICLES = 2;
                final int BOOKINGS = 3;
                final int EXIT = 4;



                Scanner keyboard = new Scanner(System.in);
                int option = 0;
                do {
                    System.out.println("\n" + MENU_ITEMS);
                    try {
                        String usersInput = keyboard.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option) {
                            case PASSENGERS:
                                System.out.println("Passengers option chosen");
                                displayPassengerMenu();
                                break;
                            case VEHICLES:
                                System.out.println("Vehicles option chosen");
                                displayVehicleMenu();
                                break;
                            case BOOKINGS:
                                System.out.println("Bookings option chosen");
                                displayBookingMenu();
                                break;
                            case EXIT:
                                System.out.println("Exit Menu option chosen");
                                break;
                            default:
                                System.out.print("Invalid option - please enter number in range");
                                break;
                        }

                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.print("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);

                System.out.println("\nExiting Main Menu, goodbye.");



            }



            // Sub-Menu for Passenger operations
            //
            private void displayPassengerMenu() {
                final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                        + "1. Show all Passengers\n"
                        + "2. Add Passenger\n"
                        + "3. Find Passenger by Name\n"
                        + "4. Delete Passenger\n"
                        + "5. Exit\n"
                        + "Enter Option [1,5]";

                final int SHOW_ALL = 1;
                final int ADD_PASSENGER = 2;
                final int FIND_BY_NAME = 3;
                final int DELETE_PASSENGER = 4;
                final int EXIT = 5;

                Scanner keyboard = new Scanner(System.in);
                int option = 0;
                do {
                    System.out.println("\n" + MENU_ITEMS);
                    try {
                        String usersInput = keyboard.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option) {
                            case SHOW_ALL:
                                System.out.println("Display ALL Passengers");
                                passengerStore.displayAllPassengers();
                                break;
                            case ADD_PASSENGER:
                                System.out.println("Add Passenger Chosen");
                                addPassengerMenu();
                                break;


                            case FIND_BY_NAME:
                                System.out.println("Find Passenger by Name");
                                System.out.println("Enter Passenger Name: ");
                                String name = keyboard.nextLine();
                                Passenger p = passengerStore.findPassengerByName(name);
                                if (p == null)
                                    System.out.println("No passenger matching the name \"" + name + "\"");
                                else
                                    System.out.println("Found Passenger: \n" + p);
                                break;
                            case DELETE_PASSENGER:
                                System.out.println("Delete Passenger Chosen");
                                System.out.println("Enter Passenger Name: ");
                                String delName = keyboard.nextLine();
                                System.out.println("Enter Passenger Email");
                                String delEmail = keyboard.nextLine();
                                if(delName != null && delEmail != null) {
                                    passengerStore.deletePassenger(delName, delEmail);
                                }
                                break;
                            case EXIT:
                                System.out.println("Exit Menu option chosen");
                                break;
                            default:
                                System.out.print("Invalid option - please enter number in range");
                                break;
                        }

                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.print("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);

            }




            private void displayVehicleMenu() {
                final String MENU_ITEMS = "\n*** VEHICLE MENU ***\n"
                        + "1. Show all Vehicles\n"
                        + "2. Add Vehicle\n"
                        + "3. Find Vehicle by Type\n"
                        + "4. Find Vehicle by Registration\n"
                        + "5. Exit\n"

                        + "Enter Option [1,5]";

                final int SHOW_ALL = 1;
                final int ADD_VEHICLE = 2;
                final int FIND_BY_TYPE = 3;
                final int FIND_BY_REGISTRATION = 4;
                final int EXIT = 5;

                ArrayList<Vehicle>  vehicles;

                Scanner keyboard = new Scanner(System.in);
                int option = 0;
                do {
                    System.out.println("\n" + MENU_ITEMS);
                    try {
                        String usersInput = keyboard.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option) {
                            case SHOW_ALL:
                                System.out.println("Display ALL Vehicles");
                                vehicleManager.displayAllVehicles();
                                break;
                            case ADD_VEHICLE:
                                System.out.println("Add Vehicle Chosen");
                                addVehicleMenu();
                                break;
                            case FIND_BY_TYPE:
                                System.out.println("Find Vehicles by Type");
                                System.out.println("Enter Vehicle Type: ");
                                String type = keyboard.nextLine();
                                vehicles = vehicleManager.findVehiclesByType(type);
                                if (vehicles == null)
                                    System.out.println("No Vehicles matching the type \"" + type + "\"");
                                else
                                    System.out.println("Found Vehicle: \n" + vehicles);
                                break;
                            case FIND_BY_REGISTRATION:
                                System.out.println("Find Vehicles by Registration");
                                System.out.println("Enter Vehicle Registration: ");
                                String registration = keyboard.nextLine();
                                vehicles = vehicleManager.findVehiclesByRegistration(registration);
                                if (vehicles == null)
                                    System.out.println("No Vehicles matching the registration \"" + registration + "\"");
                                else
                                    System.out.println("Found Vehicle: \n" + vehicles);
                                break;
                            case EXIT:
                                System.out.println("Exit Menu option chosen");
                                break;
                            default:
                                System.out.print("Invalid option - please enter number in range");
                                break;
                        }

                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.print("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);

            }
            private void displayBookingMenu() {
                final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                        + "1. Show all Bookings\n"
                        + "2. Add Booking\n"
                        + "3. Find Booking\n"
                        + "4. Find Booking\n"
                        + "5. Exit\n"

                        + "Enter Option [1,5]";

                final int SHOW_ALL = 1;
                final int ADD_BOOKING = 2;
                final int FIND_BY_ID = 3;
                final int EXIT = 5;

                Scanner keyboard = new Scanner(System.in);
                int option = 0;
                do {
                    System.out.println("\n" + MENU_ITEMS);
                    try {
                        String usersInput = keyboard.nextLine();
                        option = Integer.parseInt(usersInput);
                        switch (option) {
                            case SHOW_ALL:
                                System.out.println("Display ALL Bookings");
                                bookingManager.displayAllBookings();
                                break;
                            case ADD_BOOKING:
                                System.out.println("Add Bookings");
                                addBookingMenu();
                                break;
                            case FIND_BY_ID:
                                break;
                            case EXIT:
                                System.out.println("Exit Menu option chosen");
                                break;
                            default:
                                System.out.print("Invalid option - please enter number in range");
                                break;
                        }

                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.print("Invalid option - please enter number in range");
                    }
                } while (option != EXIT);

            }
            private void addPassengerMenu() {

                Scanner keyboard = new Scanner(System.in);

                try {
                    System.out.println("Enter Name:");
                    String name = keyboard.nextLine();
                    System.out.println("Enter Email:");
                    String email = keyboard.nextLine();
                    System.out.println("Enter Phone Number:");
                    String phone = keyboard.nextLine();
                    System.out.println("Enter Latitude");
                    double latitude = keyboard.nextDouble();
                    System.out.println("Enter longitude");
                    double longitude = keyboard.nextDouble();
                    if(name != null && email != null) {
                        boolean found = passengerStore.addPassenger(name, email, phone, latitude, longitude);

                        if (!found) {
                            System.out.println("Passenger was added");
                        } else {
                            System.out.println("Passenger already exists");
                        }
                    }
                    else
                    {
                        System.out.println("Name and Email is required, Please Try Again");
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter valid details");
                }
            }
            private void addVehicleMenu() {

                double additional;
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter Type:");
                String type = keyboard.nextLine();
                System.out.println("Enter Make:");
                String make = keyboard.nextLine();
                System.out.println("Enter Model:");
                String model = keyboard.nextLine();
                System.out.println("Enter milesPerKwH");
                String usersInput = keyboard.nextLine();
                double milesPerKwH = Double.parseDouble(usersInput);
                System.out.println("Enter Registration");
                String registration = keyboard.nextLine();
                System.out.println("Enter Cost Per Mile");
                double costPerMile = keyboard.nextDouble();
                System.out.println("Enter Last Service Year");
                int year = keyboard.nextInt();
                System.out.println("Enter Last Service Month");
                int month = keyboard.nextInt();
                System.out.println("Enter Last Service Day");
                int day = keyboard.nextInt();
                System.out.println("Enter Mileage");
                int mileage = keyboard.nextInt();


                System.out.println("Enter Latitude");
                double latitude = keyboard.nextDouble();
                System.out.println("Enter longitude");
                double longitude = keyboard.nextDouble();


                if(type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck") ) {
                    System.out.println("Enter LoadSpace");
                    additional = keyboard.nextDouble();
                }
                else
                {
                    System.out.println("Enter Number of Seats");
                    additional = keyboard.nextInt();

                }

                try {
//fix incorrect casting
                    vehicleManager.addVehicle(type,make,model,milesPerKwH,registration,costPerMile,year,month,day,mileage,latitude,longitude, (int) additional);
                    System.out.println("Vehicle added");

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter valid details");
                }
            }
            private void addBookingMenu() {

                Scanner kb = new Scanner(System.in);
                vehicleManager.displayAllVehicleId();
                passengerStore.displayAllPassengerId();
                try {
                    System.out.println("Enter Passenger ID");
                    int passengerId = kb.nextInt();
                    System.out.println("Enter Vehicle ID");
                    int vehicleId = kb.nextInt();
                    System.out.println("Enter Booking Year");
                    int year = kb.nextInt();
                    System.out.println("Enter Booking Month");
                    int month= kb.nextInt();
                    System.out.println("Enter Booking Day");
                    int day= kb.nextInt();
                    System.out.println("Enter Hour");
                    int hour = kb.nextInt();
                    System.out.println("Enter Minute");
                    int minute = kb.nextInt();
                    System.out.println("Enter Second");
                    int second= kb.nextInt();
                    System.out.println("Enter Start Latitude");
                    double latStart= kb.nextDouble();
                    System.out.println("Enter Start Longitude");
                    double longStart= kb.nextDouble();
                    System.out.println("Enter End Latitude");
                    double latEnd= kb.nextDouble();
                    System.out.println("Enter End Longitude");
                    double longEnd= kb.nextDouble();
                    System.out.println("Enter Cost");
                    double cost= kb.nextDouble();
                    if(passengerStore.findPassengerById(passengerId) == null) {
                        System.out.println("Passenger " + passengerId + " was not found");
                    }
                    else if ( vehicleManager.findVehicleById(vehicleId) == null)
                    {
                        System.out.println("Vehicle " + vehicleId + " was not found");
                    }
                    else{
                        boolean found = bookingManager.addBooking(passengerId, vehicleId, year, month, day, hour, minute, second, latStart, longStart, latEnd, longEnd, cost);
                        if (!found) {
                            System.out.println("Booking was added");
                        } else {
                            System.out.println("Booking already exists");
                        }
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter valid details");
                }
            }
            }

