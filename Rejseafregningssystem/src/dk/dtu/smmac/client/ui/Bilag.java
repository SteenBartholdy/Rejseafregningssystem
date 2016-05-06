package dk.dtu.smmac.client.ui;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.BilagDTO;

public class Bilag extends Composite{

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private ListDataProvider<BilagDTO> dataProvider;
	private CellTable<BilagDTO> table;
	private Button save, add;
	private Column<BilagDTO, String> buttonColumn, fileColumn;

	public Bilag() {

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

		ButtonCell buttonCell = new ButtonCell();
		buttonColumn = new Column<BilagDTO, String>(buttonCell) {
			@Override
			public String getValue(BilagDTO object) {
				return "Slet";
			}
		};

	    ButtonCell fileCell = new ButtonCell();
		fileColumn = new Column<BilagDTO, String>(fileCell) {
			@Override
			public String getValue(BilagDTO object) {
				return "Tilføj fil";
			}
		};
		
		TextColumn<BilagDTO> fileNameColumn = new TextColumn<BilagDTO>() {

			@Override
			public String getValue(BilagDTO obj) {
				return "";
			}
		};
		
		table.addColumn(bilagsNoColumn, "Bilags nummer:");
		table.addColumn(forklaringsColumn, "Forklaring:");
		table.addColumn(fileNameColumn, "Filnavn:");
		table.addColumn(fileColumn);
		table.addColumn(buttonColumn);

		dataProvider = new ListDataProvider<BilagDTO>();
		dataProvider.addDataDisplay(table);

		add = new Button("Tilføj");
		save = new Button("Tilbage");

		vPanel.setStyleName("margin");

		vPanel.add(table);	
		vPanel.add(hPanel);
		hPanel.add(save);
		hPanel.add(add);
		save.setStyleName("marginRight");
		add.setStyleName("marginLeft");
	}

	public Column<BilagDTO, String> getButtonColumn() {
		return buttonColumn;
	}

	public Column<BilagDTO, String> getFileColumn() {
		return fileColumn;
	}

	public Button getAddBilag()
	{
		return add;
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
