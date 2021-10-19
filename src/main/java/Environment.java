import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Environment {
	    private Track track = new Track();
	    String buffer = "";
	
	   public void go() {
	    String tmp = "";
		boolean parse = false;
		
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
	   
   }
    

   public void process(String input) {
	   //<ele>
	   if(!input.contains("<trkpt") && !input.contains("</trkpt>") && !input.contains("<ele>") ) {
		   return;
	   }
	   
	   if(input.contains("<trkpt")) {
		   buffer = input;
		   }
	   
	   if(input.contains("<ele>")) {
		   buffer += input;
		   int i = buffer.indexOf("lat");
		   int j = i + 5;
           while(buffer.charAt(j++) != '"');
		   double latDouble = Double.parseDouble(buffer.substring(i+5, j-1));
		   i = buffer.indexOf("lon");
		   j = i + 5;
           while(buffer.charAt(j++) != '"');
		   double lonDouble = Double.parseDouble(buffer.substring(i+5, j-1));
		   i = buffer.indexOf("<ele>");
		   j=i+5;
		   int k = buffer.indexOf("</ele>");
		   double elevation = Double.parseDouble(buffer.substring(j,k));
		   track.getTrackPointList().add(new TrackPoint(latDouble,lonDouble,elevation ));

		}
	}
	
	
	
}