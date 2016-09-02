package lcs.prs.goingmobile.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.services.interfaces.GpxServiceIFace;
import pt.karambola.gpx.beans.Gpx;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.parser.GpxParser;
import pt.karambola.gpx.util.GpxRouteUtils;
import pt.karambola.gpx.util.GpxTrackUtils;

@Service
public class GpxService implements ServiceIFace, GpxServiceIFace {
	
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
	
	@Override
	public Set<Journey> processGpx(MultipartFile multipartFile, String name) {
		Set<Journey> journeyList = new HashSet<>();
		
			Gpx gpx = ParseMultipartFile(multipartFile);
			
			
			int i=0;
			for (Track tr : gpx.getTracks()) {
				float dist = (float)getTrackDistanceMeters(tr)/1000;
				double avg = getTrackAverageSpeedKmH(tr);
				float proba = computeProba(avg);
				double gmPoints = computePoints(avg, proba);
				Gpx indivGpx = new Gpx();
				indivGpx.addTrack(tr);
				Journey journey = new Journey();
				
				//parser.writ
				if (dist>0.5 && avg>2) {
				journey.setJourneyName(name+i);
				journey.setKilometers(dist);
				journey.setAvgSpeed(avg);
				journey.setGmPoints(gmPoints);
				journey.setCycledProbability(proba);
				journey.setRawData(gpxToString(indivGpx));
				journeyList.add(journey);
				}
				i++;
			}
			
		return journeyList;
	}
	
	public String gpxToString(Gpx gpx) {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String output = null;
		try {
			parser.writeGpx(gpx, bos);
			output = new String(bos.toByteArray(),"UTF-8");
		} catch (ParserConfigurationException | TransformerException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public float computeProba(double avgspeed) {
		return (float)1.0;
	}
	
	public double computePoints(double kms,float proba) {
		return kms*proba/2;
	}
	
}
