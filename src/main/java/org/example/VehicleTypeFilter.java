package org.example;

// Defining a Filter class that implements the IFilter interface.
// The matches() method is coded to match Products based on their name.

public class VehicleTypeFilter implements IFilter {
    private String type;

    public VehicleTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean matches(Object other) {
        Vehicle v = (Vehicle) other;        // cast from Object to Booking
        return v.getType().equalsIgnoreCase(type);
    }
}
