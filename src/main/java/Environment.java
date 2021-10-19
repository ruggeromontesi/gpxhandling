import it.ruggero.gpxhandling.entities.Track;
import it.ruggero.gpxhandling.entities.TrackPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Environment {
	    private Track track = new Track();
	    String buffer = "";
	    boolean parse = false;
	
	   public void go() {
	    String tmp = "";

		
        BufferedReader br;
         
        try{
			//"C:\\Projects\\gpxhandling\\src\\main\\resources\\Lastsummerride.gpx"
            br = new BufferedReader( new FileReader("C:\\Projects\\gpxhandling\\src\\main\\resources\\Afternoon_Ride.gpx"));
            while((tmp = br.readLine()) != null){
				process(tmp);
            }
            br.close();

        }catch(IOException e1){
            System.out.println(e1.toString());
        }
		track.createSegmentList();
		track.calculateTotalDistance1();

        System.out.println("ci sono  " + track.getTrackPointList().size() + " punti");

    	System.out.println("la lunghezza complessiva del percorso e' " + (int)track.getTotalDistance()+ " m" );
		System.out.println("print of elevations");
		track.printElevations();
		track.printDateTime();
		track.printSpeed();
	   
   }


	public void process(String input) {
		DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		if (input.contains("<trkpt") && !parse) {
			parse = true;
			buffer = input;
		}

		if (parse && !input.contains("<trkpt")) {
			buffer += input;
		}

		if (input.contains("</trkpt>") && parse) {
			parse = false;
			int i = buffer.indexOf("lat");
			int j = i + 5;
			while (buffer.charAt(j++) != '"') ;
			double latDouble = Double.parseDouble(buffer.substring(i + 5, j - 1));
			i = buffer.indexOf("lon");
			j = i + 5;
			while (buffer.charAt(j++) != '"') ;
			double lonDouble = Double.parseDouble(buffer.substring(i + 5, j - 1));
			i = buffer.indexOf("<ele>");
			j = i + 5;
			int k = buffer.indexOf("</ele>");
			double elevation = Double.parseDouble(buffer.substring(j, k));
			TrackPoint trackPoint = new TrackPoint(latDouble, lonDouble, elevation);

			if (buffer.contains("<time>") && buffer.contains("</time>")) {
				int startIndex = buffer.indexOf("<time>");
				int stopIndex = buffer.indexOf("</time>");
				String dateString = buffer.substring(startIndex + 6, stopIndex - 1);
				LocalDateTime dateTime = LocalDateTime.parse(dateString, f);
				trackPoint.setLocalDateTime(dateTime);
			}
			track.getTrackPointList().add(trackPoint);


		}

	}

	
	
	
}