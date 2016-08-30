package lcs.prs.goingmobile.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import lcs.prs.goingmobile.entities.Journey;
import pt.karambola.gpx.beans.Gpx;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.parser.GpxParser;
import pt.karambola.gpx.util.GpxRouteUtils;
import pt.karambola.gpx.util.GpxTrackUtils;

@Service
public class GpxService {
	
	@Autowired
	GpxParser parser;
	
	@Autowired
	GpxTrackUtils trackUtil;
	
	public Gpx ParseMultipartFile(MultipartFile multipartFile) {
		Gpx gpx = null;
		try {
			gpx = parser.parseGpx(multipartFile.getInputStream());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gpx;
	}
	
	public double getDistanceAllTracks(Gpx gpx) {
		
		double result = 0;

		for (Track tr : gpx.getTracks()) {
			Route rte = trackUtil.convertTrack(tr);
			GpxRouteUtils rteUtil = new GpxRouteUtils(rte);
			result += rteUtil.length();
			
			}
	
		return result;
	}
	
	public double getTrackDistanceMeters(Track tr) {
		Route rte = trackUtil.convertTrack(tr);
		GpxRouteUtils rteUtil = new GpxRouteUtils(rte);
		
		return rteUtil.length();
		
	}
	
	public double getTrackAverageSpeedKmH(Track tr) {
		double distance = getTrackDistanceMeters(tr);
		int trackSize = tr.getTrackPoints().size();
		Date start = tr.getTrackPoints().get(0).getTime();
		Date end = tr.getTrackPoints().get(trackSize-1).getTime();
		
		long delta = end.getTime() - start.getTime();
				
		return convertToKmH(distance,delta/1000);
		
	}
	
	public double convertToKmH (double meters , double seconds) {
		return (meters/seconds)*3.6;
	}
	
	public Set<Journey> processGpx(MultipartFile multipartFile, String name) {
		Set<Journey> journeyList = new HashSet<>();
		
			Gpx gpx = ParseMultipartFile(multipartFile);
			
			
			int i=0;
			for (Track tr : gpx.getTracks()) {
				float dist = (float)getTrackDistanceMeters(tr)/1000;
				double avg = getTrackAverageSpeedKmH(tr);
				double gmPoints = dist*0.5;
				
				Journey journey = new Journey();
				
				journey.setJourneyName(name+i);
				journey.setKilometers(dist);
				journey.setAvgSpeed(avg);
				journey.setGmPoints(gmPoints);
				journey.setCycledProbability(1);
				journey.setRawData(tr.getSrc());
				journeyList.add(journey);
				
				i++;
			}
			
		return journeyList;
	}
	
}
