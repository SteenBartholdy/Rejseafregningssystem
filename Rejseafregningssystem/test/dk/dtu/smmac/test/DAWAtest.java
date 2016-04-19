package dk.dtu.smmac.test;

import java.util.List;

import dk.dtu.smmac.server.dal.DAWA;
import dk.dtu.smmac.shared.PostNrDTO;

public class DAWAtest {

	public static void main(String[] args) {
		DAWA dawa = new DAWA();
		
		try {
			List<PostNrDTO> zip = dawa.getZip();
			
			System.out.println(zip.get(30).getNo());
			
			System.out.println(dawa.getRoad("2650"));
			
			System.out.println(dawa.getHouseNo("2650", "Brostykkevej"));
			
			System.out.println(dawa.getFloor("2650", "Brostykkevej", "121"));
			
			System.out.println(dawa.getDoor("2650", "Brostykkevej", "121", "st"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
