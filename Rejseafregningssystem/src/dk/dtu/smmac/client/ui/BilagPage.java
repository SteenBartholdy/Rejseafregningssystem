package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.BilagDTO;
import dk.dtu.smmac.shared.RejseDTO;
import dk.dtu.smmac.shared.UdgifterDTO;

public class BilagPage extends Composite{
	
	private VerticalPanel vPanel = new VerticalPanel();
	private ListDataProvider<BilagDTO> dataProvider;
	private CellTable<BilagDTO> table;
	private Anchor addBilag;
	private Button save;
	
	public BilagPage() {
		
		initWidget(this.vPanel);

		table = new CellTable<BilagDTO>();
		
		TextColumn<BilagDTO> bilagsNoColumn = new TextColumn<BilagDTO>() {

			@Override
			public String getValue(BilagDTO obj) {
				return "Bilag " + obj.getID();
			}
		};
		
		final TextInputCell forklaringsCell = new TextInputCell();
		Column<BilagDTO, String> forklaringsColumn = new Column<BilagDTO, String>(forklaringsCell) {
			@Override
			public String getValue(BilagDTO object) {
				return object.getForklaring();
			}
		};
		
		forklaringsColumn.setFieldUpdater(new FieldUpdater<BilagDTO, String>() {
			@Override
			public void update(int index, BilagDTO object, String value) {
				object.setForklaring(value);
				table.redraw();
			}
		});
		
		table.addColumn(bilagsNoColumn);
		table.addColumn(forklaringsColumn);
		
		dataProvider = new ListDataProvider<BilagDTO>();
		dataProvider.addDataDisplay(table);
		
		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");
		addBilag.setStyleName("anchorStyle");
		
		save = new Button();
		save.setText("Fortsæt");
		save.setStyleName("alignButtom");
		
		vPanel.setStyleName("margin");

		vPanel.add(table);	
		vPanel.add(addBilag);
		vPanel.add(save);
	}
	
	public Anchor getAddBilag()
	{
		return addBilag;
	}
	
	public CellTable<BilagDTO> getTable() {
		return this.table;
	}
	
	public Button getSaveButton() 
	{
		return save;
	}
	
	public void addBilag(BilagDTO bilag)
	{
		dataProvider.getList().add(bilag);
	}
	
	public List<BilagDTO> getData()
	{
		return dataProvider.getList();
	}
	
	public void reset()
	{
		dataProvider.getList().clear();
	}
	
}
