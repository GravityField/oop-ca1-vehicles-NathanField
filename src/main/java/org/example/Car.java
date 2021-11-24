package org.example;

public class Car extends Vehicle
{

    private int numberOfSeats;
    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude, int numberOfSeats)
    {
        super(id, type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numberOfSeats = numberOfSeats;
    }

    // Constructor to create a Vehicle object, when the id is available.
    // So this is called to construct a Vehicle when the vehicle record is read from
    // the vehicles.txt file, and the id is known.
    //
    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude)
    {
        super(id, type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);
    }


     
}
