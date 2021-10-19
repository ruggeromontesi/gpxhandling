import java.util.*;

class Track {
    private static double N = 1;
    private List<TrackPoint> trackPointList = new ArrayList<>();
    private List<Segment> segmentList = new ArrayList<>();
    private double totalDistance = 0;

    public List<TrackPoint> getTrackPointList() {
        return this.trackPointList;
    }

    public double getTotalDistance() {
        return (this.totalDistance * N - (this.totalDistance * N) % 1) / N;
    }

    public void calculateTotalDistance1() {
        segmentList.stream().forEach(x -> totalDistance += x.getDistance());
    }

    public void calculateTotalDistance() {
        segmentList.stream().forEach(x -> totalDistance += x.getDistance());
    }

    public double calculatePointToPointDistance(TrackPoint t1, TrackPoint t2) {
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
        return dist;
    }

    public void createSegmentList() {
        for (int i = 1; i < trackPointList.size(); i++) {
            segmentList.add(new Segment(trackPointList.get(i - 1), trackPointList.get(i)));
        }
    }

    public void printDistances() {
        segmentList.stream().forEach(x -> System.out.println("dist : " + x.getDistance()));
    }

    public void printElevations() {
        trackPointList.forEach(x-> System.out.printf("elevation :  %3.1f%n", x.getElevation()));
    }
}