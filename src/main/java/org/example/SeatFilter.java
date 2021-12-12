package org.example;

// Defining a Filter class that implements the IFilter interface.
// The matches() method is coded to match Products based on their name.

public class SeatFilter implements IFilter {
    private int seats;

    public SeatFilter(int seats) {
        this.seats = seats;
    }

    @Override
    public boolean matches(Object other) {
        Vehicle v = (Vehicle) other;        // cast from Object to Booking
        if(v instanceof Car)
        {
            return ((Car) v).getNumberOfSeats() == seats ;
        }
        return false;
    }
}
