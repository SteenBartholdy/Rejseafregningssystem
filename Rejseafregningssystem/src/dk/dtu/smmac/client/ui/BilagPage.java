package dk.dtu.smmac.client.ui;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.BilagDTO;

public class BilagPage extends Composite{
	
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable = new FlexTable();
	private ListDataProvider<BilagDTO> dataProvider;
	private CellTable<BilagDTO> table;
	private Anchor addBilag;
	
	public BilagPage() {
		
		initWidget(this.vPanel);

		table = new CellTable<BilagDTO>();
		
		TextColumn<BilagDTO> bilagsNoColumn = new TextColumn<BilagDTO>() {

			@Override
			public String getValue(BilagDTO obj) {
				return "Bilag " + obj.getID();
			}
		};
		
		TextColumn<BilagDTO> forklaringColumn = new TextColumn<BilagDTO>() {

			@Override
			public String getValue(BilagDTO obj) {
				return obj.getForklaring();
			}
		};
		
		table.addColumn(bilagsNoColumn);
		table.addColumn(forklaringColumn);
		
		dataProvider = new ListDataProvider<BilagDTO>();
		dataProvider.addDataDisplay(table);
		
		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");

		vPanel.add(table);	
	}

}
