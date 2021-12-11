package org.example;

import java.awt.print.Book;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    BookingManager.DateTimeComparator dateComparator = new BookingManager.DateTimeComparator();

    // Constructor
    public BookingManager(String fileName, PassengerStore passengerStore, VehicleManager vehicleManager) {
        this.bookingList = new ArrayList<>();
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
        loadBookingsDataFromFile(fileName);
    }

    //TODO implement functionality as per specification

    public void addBooking(int passengerId, int vehicleId, int year, int month, int day,
                           double latStart, double longStart, double latEnd, double longEnd, double cost) {
//        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
//        LocationGPS locationStart = new LocationGPS(latStart,longStart);
//        LocationGPS locationEnd = new LocationGPS(latEnd,longEnd);

        if (passengerStore.findPassengerById(passengerId) != null) {
            if (vehicleManager.findVehicleById(vehicleId) != null) {
                Booking b1 = new Booking(passengerId, vehicleId, year, month, day, latStart, longStart, latEnd, longEnd, cost);
                boolean found = false;
                for (Booking b : bookingList) {
                    if (b.equals(b1)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    bookingList.add(b1);
//email
                    String content = "Booking Confirmation for booking Number " + b1.getBookingId() + "\n"
                            + "Booking Date: " + b1.getBookingDate() + "\n"
                            + "Total Bill: " + b1.getCost();
                    Email email = new Email(passengerStore.findPassengerById(passengerId).getEmail(), "", LocalDate.now(), "Booking Confirmation", content);
                    System.out.println(email);
                }

            }
        }

    }

    private void loadBookingsDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                double latStart = sc.nextDouble();
                double longStart = sc.nextDouble();
                double latEnd = sc.nextDouble();
                double longEnd = sc.nextDouble();
                double cost = sc.nextDouble();

                // construct a Booking object and add it to the booking list
                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day,
                        latStart, longStart, latEnd, longEnd, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void saveBookingsToFile(String fileName) {
        try {
            FileWriter bookingWriter = new FileWriter(fileName);
            for (Booking b : bookingList) {


                bookingWriter.write(
                        b.getBookingId() + ","
                                + b.getPassengerId() + ","
                                + b.getVehicleId() + ","
                                + b.getBookingDate().getYear() + ","
                                + b.getBookingDate().getMonthValue() + ","
                                + b.getBookingDate().getDayOfMonth() + ","
                                + b.getStartLocation().getLatitude() + ","
                                + b.getStartLocation().getLongitude() + ","
                                + b.getEndLocation().getLatitude() + ","
                                + b.getEndLocation().getLongitude() + ","
                                + b.getCost() + "\n"
                );


            }
            System.out.println("Successfully wrote Bookings to the file.");
            bookingWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public void displayAllBookings() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("BookingID\tPassengerID\tVehicleID\tDate\t\t\tGPS Start\tGPS End");
        for (Booking b : bookingList) {
            System.out.printf("%-12s%-12s%-12s%-15s%-4.2f% -4.2f% -4.2f% -4.2f\n", b.getBookingId(), b.getPassengerId(), b.getVehicleId(), b.getBookingDate().toString(), b.getStartLocation().getLatitude(), b.getStartLocation().getLongitude(), b.getEndLocation().getLatitude(), b.getEndLocation().getLongitude());
        }
    }
    public void displayCurrentBookings() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("BookingID\tPassengerID\tVehicleID\tDate\t\t\tGPS Start\tGPS End");
        for (Booking b : bookingList) {
            if (b.getBookingDate().isAfter(LocalDate.now())  ) {
                System.out.printf("%-12s%-12s%-12s%-15s%-4.2f% -4.2f% -4.2f% -4.2f\n", b.getBookingId(), b.getPassengerId(), b.getVehicleId(), b.getBookingDate().toString(), b.getStartLocation().getLatitude(), b.getStartLocation().getLongitude(), b.getEndLocation().getLatitude(), b.getEndLocation().getLongitude());

            }
        }
    }

    public void displayAllBookingId() {
        for (Booking b : bookingList) {
            System.out.println(b.getBookingId());
        }
    }

    public Booking findBookingById(int findId) {
        for (Booking b : bookingList)
            if (b.getBookingId() == findId) {
                return b;
            }
        return null;


    }

    public ArrayList<Booking> findBookingByName(String name) {
        ArrayList<Booking> bookingResults = new ArrayList<>();
        for (Booking b : bookingList) {
            if (b.getPassengerId() == passengerStore.findPassengerByName(name).getId()) {
                bookingResults.add(b);
            }

        }
        Collections.sort(bookingResults, dateComparator);
        return bookingResults;
    }

    public static class DateTimeComparator implements Comparator<Booking> {

        public int compare(Booking b1, Booking b2) {
            return b1.getBookingDate().compareTo(b2.getBookingDate());
        }
    }

    public void editBooking(int bookingId, int passengerId, int vehicleId, int year, int month, int day,
                            double latStart, double longStart, double latEnd, double longEnd, double cost) {
        boolean found = false;
        for (Booking b : bookingList) {
            if (b.getBookingId() == bookingId) {
                found = true;
                b.setPassengerId(passengerId);
                b.setVehicleId(vehicleId);
                b.setBookingDate(LocalDate.of(year, month, day));
                b.setStartLocation(new LocationGPS(latStart, longStart));
                b.setEndLocation(new LocationGPS(latEnd, longEnd));
                break;
            }

        }
        if (!found) {
            System.out.println("not found");
        }

    }

    public void deleteBooking(int bId) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == bId) {
                bookingList.remove(b);
                break;
            }
        }
    }

    public void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show Current Bookings\n"
                + "2. Add Booking\n"
                + "3. Edit Booking\n"
                + "4. Find Booking by Passenger Name\n"
                + "5. Average Booking Length\n"
                + "6. Delete Booking\n"
                + "7. All Bookings\n"
                + "8. Exit\n"

                + "Enter Option [1,8]";

        final int SHOW_CURRENT = 1;
        final int ADD_BOOKING = 2;
        final int EDIT_BOOKING = 3;
        final int FIND_BY_NAME = 4;
        final int CALCULATE_AVERAGE_LENGTH = 5;
        final int DELETE_BOOKING = 6;
        final int SHOW_ALL = 7;
        final int EXIT = 8;


        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_CURRENT:
                        System.out.println("Display Current Bookings Chosen");
                        displayCurrentBookings();
                        break;
                    case SHOW_ALL:
                        System.out.println("Display ALL Bookings");
                        displayAllBookings();
                        break;
                    case ADD_BOOKING:
                        System.out.println("Add Bookings");
                        addBookingMenu();
                        break;
                    case EDIT_BOOKING:
                        System.out.println("Edit Bookings");
                        editBookingMenu();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Enter passenger name to search for bookings");
                        String findName = keyboard.nextLine();
                        ArrayList<Booking> results = findBookingByName(findName);
                        if (results != null) {
                            System.out.println(results);
                        }
                        break;
                    case CALCULATE_AVERAGE_LENGTH:
                        System.out.println("Average booking Length is " + calculateAverageLength());
                        break;
                    case DELETE_BOOKING:
                        System.out.println("Delete Booking Chosen");
                        int deleteId = keyboard.nextInt();
                        deleteBooking(deleteId);
                        System.out.println("Deleted Booking");
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

    public void addBookingMenu() {

        Scanner kb = new Scanner(System.in);
        System.out.println("Vehicle Ids");
        vehicleManager.displayAllVehicleId();
        System.out.println("Passenger Ids");
        passengerStore.displayAllPassengerId();
        try {
            System.out.println("Enter Passenger ID");
            int passengerId = kb.nextInt();
            System.out.println("Enter Vehicle ID");
            int vehicleId = kb.nextInt();
            System.out.println("Enter Booking Year");
            int year = kb.nextInt();
            System.out.println("Enter Booking Month");
            int month = kb.nextInt();
            System.out.println("Enter Booking Day");
            int day = kb.nextInt();
            System.out.println("Enter Start Latitude");
            double latStart = kb.nextDouble();
            System.out.println("Enter Start Longitude");
            double longStart = kb.nextDouble();
            System.out.println("Enter End Latitude");
            double latEnd = kb.nextDouble();
            System.out.println("Enter End Longitude");
            double longEnd = kb.nextDouble();
            double depotLat = vehicleManager.findVehicleById(vehicleId).getDepotGPSLocation().getLatitude();
            double depotLong = vehicleManager.findVehicleById(vehicleId).getDepotGPSLocation().getLongitude();
            double cost = haversine(depotLat, depotLong, latStart, longStart) + haversine(latStart, longStart, latEnd, longEnd) + haversine(latEnd, longEnd, depotLat, depotLong);


            if (passengerStore.findPassengerById(passengerId) == null) {
                System.out.println("Passenger " + passengerId + " was not found");
            } else if (vehicleManager.findVehicleById(vehicleId) == null) {
                System.out.println("Vehicle " + vehicleId + " was not found");
            } else {
                addBooking(passengerId, vehicleId, year, month, day, latStart, longStart, latEnd, longEnd, cost);
//                if (!found) {
//                    System.out.println("Booking was added");
//                } else {
//                    System.out.println("Booking already exists");
//                }
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter valid details");
        }
    }

    public void editBookingMenu() {

        Scanner kb = new Scanner(System.in);
        System.out.println("Booking Ids");
        displayAllBookingId();
        System.out.println("Vehicle Ids");
        vehicleManager.displayAllVehicleId();
        System.out.println("Passenger Ids");
        passengerStore.displayAllPassengerId();
        System.out.println("Enter Booking ID to change");
        int bookingId = kb.nextInt();
        if (findBookingById(bookingId) != null) {
            String MENU_ITEMS = "\n*** Edit Booking MENU ***\n"
                    + "1. Edit Passenger\n"
                    + "2. Edit Vehicle\n"
                    + "3. Edit Year\n"
                    + "4. Edit Month\n"
                    + "5. Edit Day\n"
                    + "6. Edit Hour\n"
                    + "7. Edit Minute\n"
                    + "8. Edit Seconds\n"
                    + "9. Edit Start Longitude\n"
                    + "10. Edit Start Latitude\n"
                    + "11. Edit End Longitude\n"
                    + "12. Edit End Latitude\n"
                    + "13. Exit\n"
                    + "Enter Option [1,13]";

            final int EDIT_PASSENGER = 1;
            final int EDIT_VEHICLE = 2;
            final int EDIT_YEAR = 3;
            final int EDIT_MONTH = 4;
            final int EDIT_DAY = 5;
            final int EDIT_HOUR = 6;
            final int EDIT_MINUTE = 7;
            final int EDIT_SECOND = 8;
            final int EDIT_START_LONGITUDE = 9;
            final int EDIT_START_LATITUDE = 10;
            final int EDIT_END_LONGITUDE = 11;
            final int EDIT_END_LATITUDE = 12;
            final int EXIT = 13;

            int option = 0;
            do {
                Booking b = findBookingById(bookingId);
                System.out.println("\n" + MENU_ITEMS);
                try {
                    option = kb.nextInt();
                    switch (option) {
                        case EDIT_PASSENGER:
                            System.out.println("Edit Passenger");
                            int newPassengerId = kb.nextInt();
                            b.setPassengerId(newPassengerId);
                            System.out.println("Passenger Updated");
                            break;
                        case EDIT_VEHICLE:
                            System.out.println("Edit Vehicle");
                            int newVehicleId = kb.nextInt();
                            b.setVehicleId(newVehicleId);
                            System.out.println("Vehicle Updated");
                            break;
//                        case EDIT_YEAR:
//                            System.out.println("Edit Year");
//                            int newYear = kb.nextInt();
//                            b.setBookingDateTime(new LocalDateTime.of(newYear, ));
//                            System.out.println("Year Updated");
//                            break;

                        case EXIT:
                            System.out.println("Exit Menu option chosen");
                            break;
                    }

                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter number in range");
                }
            } while (option != EXIT);
        }
    }

    double calculateAverageLength() {
        double result = 0;
        for (Booking b : bookingList) {
            result = result + b.getCost();

        }
        result = result / bookingList.size();

        return result;
    }

    //https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
    double haversine(double lat1, double lon1,
                     double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 3961;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

}
