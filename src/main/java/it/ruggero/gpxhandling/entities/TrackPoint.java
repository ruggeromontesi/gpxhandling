package it.ruggero.gpxhandling.entities;

import java.time.LocalDateTime;

public class TrackPoint {
    private double lat;
    private double lon;

    private double elevation;
    private LocalDateTime localDateTime;

    public TrackPoint(double lat, double lon) {
        this.lat = Math.toRadians(lat);
        this.lon = Math.toRadians(lon);
    }

    public TrackPoint(double lat, double lon, double elevation) {
        this.lat = Math.toRadians(lat);
        this.lon = Math.toRadians(lon);
        this.elevation = elevation;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public double getElevation() {
        return this.elevation;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String toString() {
        return "latitude : " + this.lat + " [rad] " + "longitude : " + this.lon + " [rad]" + "elevation : " + this.elevation +" [m]\t ";
    }
}