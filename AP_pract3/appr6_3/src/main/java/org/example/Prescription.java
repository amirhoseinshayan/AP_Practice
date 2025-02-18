package org.example;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Prescription {

    private Set<PrescriptionItem> items = new HashSet<>();
    private Location location ;
    private int  date;


    public Prescription(Set<PrescriptionItem> items, Location location, int  date) {
        this.items = items;
        this.location = location;
        this.date = date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public Set<PrescriptionItem> getItems() {
        return items;
    }

    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    public void setItems(Set<PrescriptionItem> items) {
        this.items = items;
    }
}

