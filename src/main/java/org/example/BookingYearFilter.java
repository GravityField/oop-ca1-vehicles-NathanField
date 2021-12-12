package org.example;

// Defining a Filter class that implements the IFilter interface.
// The matches() method is coded to match Products based on their name.

public class BookingYearFilter implements IFilter {
    private int year;

    public BookingYearFilter(int year) {
        this.year = year;
    }

    @Override
    public boolean matches(Object other) {
        Booking b = (Booking) other;        // cast from Object to Booking
        return b.getBookingDate().getYear() == year;
    }
}
