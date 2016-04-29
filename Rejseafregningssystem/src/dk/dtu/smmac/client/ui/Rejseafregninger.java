package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.RejseafregningerDTO;

public class Rejseafregninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private ListDataProvider<RejseafregningerDTO> dataProvider;
	
	public Rejseafregninger() {
		initWidget(this.vPanel);
		
		CellTable<RejseafregningerDTO> table = new CellTable<RejseafregningerDTO>();
		table.setPageSize(14);
		SimplePager pager = new SimplePager();
		pager.setDisplay(table);
		
		TextColumn<RejseafregningerDTO> nummerColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getNr();
			}
		};
		
		TextColumn<RejseafregningerDTO> landColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getLand();
			}
		};
		
		TextColumn<RejseafregningerDTO> datoFraColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getDatoFra();
			}
		};
		
		TextColumn<RejseafregningerDTO> datoTilColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getDatoTil();
			}
		};
		
		TextColumn<RejseafregningerDTO> startTidColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getStartTid();
			}
		};
		
		TextColumn<RejseafregningerDTO> SlutTidColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getSlutTid();
			}
		};
		
		table.addColumn(nummerColumn, "Nummer");
		table.addColumn(landColumn, "Lande");
		table.addColumn(datoFraColumn, "Fra dato");
		table.addColumn(datoTilColumn, "Til dato");
		table.addColumn(startTidColumn, "Starttid");
		table.addColumn(SlutTidColumn, "Sluttid");

		dataProvider = new ListDataProvider<RejseafregningerDTO>();
		dataProvider.addDataDisplay(table);
	    
	    vPanel.setStyleName("margin");
		vPanel.add(table);
		vPanel.add(pager);
	}
	
	public void setData(List<RejseafregningerDTO> rejseList) {
		dataProvider.getList().clear();
		
		for (RejseafregningerDTO rejse : rejseList) {
	    	dataProvider.getList().add(rejse);
	    }
	}

}
