package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.RejseDTO;

public class Rejseafregninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private ListDataProvider<RejseDTO> dataProvider;
	
	public Rejseafregninger() {
		initWidget(this.vPanel);
		
		CellTable<RejseDTO> table = new CellTable<RejseDTO>();

		TextColumn<RejseDTO> idColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getNummer();
			}
		};
		
		TextColumn<RejseDTO> datoColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getDatoFra();
			}
		};
		
		TextColumn<RejseDTO> landColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getLand();
			}
		};
		
		table.addColumn(idColumn, "Id");
		table.addColumn(datoColumn, "Dato");
		table.addColumn(landColumn, "Land");

		dataProvider = new ListDataProvider<RejseDTO>();
		dataProvider.addDataDisplay(table);
	    
	    vPanel.setStyleName("margin");
		vPanel.add(table);
	}
	
	public void setData(List<RejseDTO> rejseList) {
		List<RejseDTO> list = dataProvider.getList();
	    for (RejseDTO rejse : rejseList) {
	      list.add(rejse);
	    }
	}

}
