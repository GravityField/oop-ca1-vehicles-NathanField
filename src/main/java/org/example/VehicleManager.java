package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                if(type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck"))
                {
                    double loadSpace = sc.nextDouble();
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if (type.equalsIgnoreCase("Car")) {
                    int numberOfSeats = sc.nextInt();

                    // construct a Car object and add it to the passenger list
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            numberOfSeats));

                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void saveVehiclesToFile(String fileName) {
        try {
            FileWriter vehicleWriter = new FileWriter(fileName);
            for (Vehicle v : vehicleList) {

                if (v instanceof Car) {
                    vehicleWriter.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + ","
                                    + v.getMilesPerKwH() + ","
                                    + v.getRegistration() + ","
                                    + v.getCostPerMile() + ","
                                    + v.getLastServicedDate().getYear() + ","
                                    + v.getLastServicedDate().getMonthValue() + ","
                                    + v.getLastServicedDate().getDayOfMonth() + ","
                                    + v.getMileage() + ","
                                    + v.getDepotGPSLocation().getLatitude() + ","
                                    + v.getDepotGPSLocation().getLongitude() + "," +
                                    ((Car) v).getNumberOfSeats() +
                                    "\n"
                    );

                } else if (v instanceof Van) {
                    vehicleWriter.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + ","
                                    + v.getMilesPerKwH() + ","
                                    + v.getRegistration() + ","
                                    + v.getCostPerMile() + ","
                                    + v.getLastServicedDate().getYear() + ","
                                    + v.getLastServicedDate().getMonthValue() + ","
                                    + v.getLastServicedDate().getDayOfMonth() + ","
                                    + v.getMileage() + ","
                                    + v.getDepotGPSLocation().getLatitude() + ","
                                    + v.getDepotGPSLocation().getLongitude() + "," +
                                    ((Van) v).getLoadSpace() +
                                    "\n"
                    );

                }
            }
            vehicleWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }


    //TODO add more functionality as per spec.


    public void addVehicle(String type, String make, String model, double milesPerKwH,
                           String registration, double costPerMile,
                           int year, int month, int day,
                           int mileage, double latitude, double longitude, int loadSpace) {


        if (type.equalsIgnoreCase("Van") ||
                type.equalsIgnoreCase("Truck")) {
            // construct a Van object and add it to the passenger list
            vehicleList.add(new Van(type, make, model, milesPerKwH,
                    registration, costPerMile,
                    year, month, day,
                    mileage, latitude, longitude,
                    loadSpace));
        } else if (type.equalsIgnoreCase("Car")) {
            // construct a Car object and add it to the passenger list
            vehicleList.add(new Car(type, make, model, milesPerKwH,
                    registration, costPerMile,
                    year, month, day,
                    mileage, latitude, longitude,
                    loadSpace));
            //loadspace is passed in for number of seats
        }
    }

    CarRegistrationComparator registrationComparator = new CarRegistrationComparator();

    public ArrayList<Vehicle> findVehiclesByRegistration(String reg) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> findVehiclesByType(String type) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getType().equalsIgnoreCase(type)) {
                vehicles.add(v);
            }
        }

        Collections.sort(vehicles, registrationComparator);
        return vehicles;
    }

    public static class CarRegistrationComparator implements Comparator<Vehicle> {

        public int compare(Vehicle v1, Vehicle v2) {
            return v1.getRegistration().compareTo(v2.getRegistration());
        }
    }


}
