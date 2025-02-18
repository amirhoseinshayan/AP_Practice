package org.example;

public  class LocationService {
    public int distance (Location location1 , Location location2) {
        int l = location1.getLength() - location2.getLength();
        int w = location1.getWidth() - location2.getWidth();
        return (int) Math.sqrt(l * l + w * w);
    }

}
