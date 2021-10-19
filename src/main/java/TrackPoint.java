public class TrackPoint {
    private double lat;
    private double lon;

    private double elevation;

    TrackPoint(double lat, double lon) {
        this.lat = Math.toRadians(lat);
        this.lon = Math.toRadians(lon);
    }

    TrackPoint(double lat, double lon, double elevation) {
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

    public String toString() {
        return "latitude : " + this.lat + " [rad] " + "longitude : " + this.lon + " [rad]" + "elevation : " + this.elevation +" [m]\t ";
    }
}