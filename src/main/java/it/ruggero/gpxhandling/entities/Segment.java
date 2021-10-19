package it.ruggero.gpxhandling.entities;

import it.ruggero.gpxhandling.entities.TrackPoint;

import java.time.ZoneOffset;

public class Segment {
    private TrackPoint startPoint;
    private TrackPoint endPoint;
    private double distance;
    private double speed;

    public Segment(TrackPoint startPoint, TrackPoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        calculatePointToPointDistance(startPoint, endPoint);

    }

    public double getDistance() {
        return this.distance;
    }

    public double getSpeed() {
        calculateSpeed();
        return speed;
    }

    private void calculatePointToPointDistance(TrackPoint t1, TrackPoint t2) {
        double R = 6371000; //earth radius in meters
        double lat1 = t1.getLat();
        double lon1 = t1.getLon();
        double lat2 = t2.getLat();
        double lon2 = t2.getLon();
        double a = Math.cos(lat1) * Math.cos(lon1) * Math.cos(lat2) * Math.cos(lon2) + Math.cos(lat1) * Math.sin(lon1) * Math.cos(lat2) * Math.sin(lon2) + Math.sin(lat1) * Math.sin(lat2);
        double d2 = R * Math.acos(a);
        double dist;
        dist = d2;
        //if (Double.isNaN(dist)) dist = 0;
        this.distance = dist;
    }

    private void calculateSpeed() {
        if ( (startPoint.getLocalDateTime()!=null) && (endPoint.getLocalDateTime()!=null)) {
            ZoneOffset zone = ZoneOffset.of("Z");
            long duration = endPoint.getLocalDateTime().toEpochSecond(zone )-startPoint.getLocalDateTime().toEpochSecond(zone) ;
            this.speed = this.distance/duration;


        }

    }
}