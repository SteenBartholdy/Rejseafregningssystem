package dk.dtu.smmac.server.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.DAWAService;
import dk.dtu.smmac.shared.PostNrDTO;

public class DAWA extends RemoteServiceServlet implements DAWAService {
	
	private String data;
    private JSONArray json;
    private JSONObject obj;
    
    private List<PostNrDTO> zip;
    
    private final String API = "http://dawa.aws.dk/";
    
    //Henter post nummer listen
    @Override
    public List<PostNrDTO> getZip() throws Exception {
    	zip = new ArrayList<PostNrDTO>();
    	
    	this.data = getUrl(this.API + "postnumre/");
    	this.json = new JSONArray(data);
    	
    	for (int i = 0; i < this.json.length(); i++) {
    		this.obj = this.json.getJSONObject(i);
    		zip.add(new PostNrDTO(this.obj.optString("nr"), this.obj.optString("navn")));
    	}
    	
    	return zip;
    }
    
    //Henter vejnavn listen
    @Override
    public List<String> getRoad(String zip) throws Exception {
		if (zip.equals("")) {
			return null;
		}
    	
    	List<String> road = new ArrayList<String>();
		
		this.data = getUrl(this.API + "vejnavne?postnr=" + zip);
		this.json = new JSONArray(data);
		
		for (int i = 0; i < this.json.length(); i++) {
    		this.obj = this.json.getJSONObject(i);
    		road.add(this.obj.optString("navn"));
		}
		
    	return road;
    }
    
    //Henter husnr listen
    @Override
    public List<String> getHouseNo(String zip, String road) throws Exception {
    	if (zip.equals("")) {
			return null;
		}
    	
    	List<String> houseNo = new ArrayList<String>();
    	
    	this.data = getUrl(this.API + "adgangsadresser?vejnavn=" + road + "&postnr=" + zip);
		//this.data = getUrl("http://dawa.aws.dk/adgangsadresser?vejnavn=Lærkevej&postnr=2400");
    	this.json = new JSONArray(data);
    	
		for (int i = 0; i < this.json.length(); i++) {
    		this.obj = this.json.getJSONObject(i);
    		houseNo.add(this.obj.optString("husnr"));
		}
		
    	return houseNo;
    }
    
    //Henter etagenr listen
    @Override
    public List<String> getFloor(String zip, String road, String houseNo) throws Exception {
    	if (zip.equals("")) {
			return null;
		}
    	
    	List<String> floor = new ArrayList<String>();
    	
    	this.data = getUrl(this.API + "adresser?vejnavn=" + road + "&husnr=" + houseNo + "&postnr=" + zip);
		this.json = new JSONArray(data);
    	
		for (int i = 0; i < this.json.length(); i++) {
    		this.obj = this.json.getJSONObject(i);
    		floor.add(this.obj.optString("etage"));
		}
		
		Set<String> hs = new HashSet<>();
		hs.addAll(floor);
		floor.clear();
		floor.addAll(hs);
		
    	return floor;
    }
    
    //Henter dør listen
    @Override
    public List<String> getDoor(String zip, String road, String houseNo, String floor) throws Exception {
    	if (zip.equals("")) {
			return null;
		}
    	
    	List<String> door = new ArrayList<String>();
    	
    	this.data = getUrl(this.API + "adresser?vejnavn=" + road + "&husnr=" + houseNo + "&etage=" + floor + "&postnr=" + zip);
		this.json = new JSONArray(data);
    	
		for (int i = 0; i < this.json.length(); i++) {
    		this.obj = this.json.getJSONObject(i);
    		door.add(this.obj.optString("dør"));
		}
		
    	return door;
    }
    
    //Henter indholdet fra en url og omskriver det til en string
    public String getUrl(String url) throws IOException {
    	url = url.replace("æ", "%C3%A6");
    	url = url.replace("ø", "%C3%B8");
    	url = url.replace("å", "%C3%A5");
    	url = url.replace("Æ", "%C3%86");
    	url = url.replace("Ø", "%C3%98");
    	url = url.replace("Å", "%C3%85");
    	url = url.replace(" ", "%20");
    	url = url.replace("é", "%C3%A9");
    	url = url.replace("ü", "%C3%BC");
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

	@Override
	public String getCity(String zipCode) throws Exception {
		for(int i = 0; i < zip.size(); i++) {
			if (zip.get(i).getNo().equals(zipCode))
			{
				return zip.get(i).getName();
			}
		}
		
		return "";
	}
    
}
