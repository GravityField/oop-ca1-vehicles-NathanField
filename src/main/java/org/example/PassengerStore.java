package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }


    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add


    public void displayAllPassengers() {
        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }
    }

    public boolean addPassenger(String name, String email, String phone,
                             double latitude, double longitude)
    {
        Passenger P = new Passenger(name,email,phone,latitude,longitude);
        boolean found = false;
        for(Passenger p: passengerList) {
            if (p.equals(P)) {
                found = true;
                break;
            }

        }
        if(!found)
        {
            passengerList.add(P);
        }
        return found;
    }
    public void deletePassenger(String name, String email)
    {
        for(Passenger p: passengerList) {
            if (p.getName().equalsIgnoreCase(name) && p.getEmail().equalsIgnoreCase(email)) {
                passengerList.remove(p);
                break;
            }

        }

    }
    public void editPassenger(String name, String email, String phone,
                             double latitude, double longitude)
    {
        boolean found = false;
        for(Passenger p: passengerList) {
            if (p.getName().equalsIgnoreCase(name) && p.getEmail().equalsIgnoreCase(email)) {
                found = true;
                p.setName(name);
                p.setEmail(email);
                p.setPhone(phone);
                p.setLocation(latitude, longitude);

                break;
            }

        }
        if(!found)
        {
            System.out.println("not found");
        }

    }

    public Passenger findPassengerByName(String Name) {
        for (Passenger p : passengerList)
            if(p.getName().equalsIgnoreCase(Name)) {
                return p;
            }
        return null;


    }



} // end class
