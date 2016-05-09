package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.BilagDTO;
import dk.dtu.smmac.shared.DageInfoDTO;
import dk.dtu.smmac.shared.RejseafregningerDTO;

public class Godkend extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private ListDataProvider<RejseafregningerDTO> dataProvider;
	private CellTable<RejseafregningerDTO> table;
	private Button back;
	private DateTimeFormat dtFmt = DateTimeFormat.getFormat("dd/MM yyyy");
	private Column<RejseafregningerDTO, String> buttonColumn;
	
	public Godkend() {
		initWidget(this.vPanel);

		table = new CellTable<RejseafregningerDTO>();
		table.setPageSize(14);

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
				return dtFmt.format(obj.getDatoFra());
			}
		};
		
		TextColumn<RejseafregningerDTO> datoTilColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return dtFmt.format(obj.getDatoTil());
			}
		};
		
		TextColumn<RejseafregningerDTO> afregningColumn = new TextColumn<RejseafregningerDTO>() {

			@Override
			public String getValue(RejseafregningerDTO obj) {
				return ""+obj.getAfregning();
			}
		};
		
		ButtonCell buttonCell = new ButtonCell();
		buttonColumn = new Column<RejseafregningerDTO, String>(buttonCell) {
			@Override
			public String getValue(RejseafregningerDTO object) {
				return "Godkend";
			}
		};
		
		table.addColumn(nummerColumn, "Nummer");
		table.addColumn(landColumn, "Lande");
		table.addColumn(datoFraColumn, "Fra dato");
		table.addColumn(datoTilColumn, "Til dato");
		table.addColumn(afregningColumn, "Afregning (DDK)");
		table.addColumn(buttonColumn);
		
		dataProvider = new ListDataProvider<RejseafregningerDTO>();
		dataProvider.addDataDisplay(table);

		back = new Button("Tilbage");

		vPanel.setStyleName("margin");

		vPanel.add(table);	
		vPanel.add(hPanel);
		hPanel.add(back);
		back.setStyleName("marginRight");
	}
	
	public Column<RejseafregningerDTO, String> getButtonColumn() {
		return buttonColumn;
	}
	
	public Button getBackButton() 
	{
		return back;
	}
	
	public void setData(List<RejseafregningerDTO> list) {
		for (RejseafregningerDTO rejse : list) {
			this.dataProvider.getList().add(rejse);
		}
	}

	public List<RejseafregningerDTO> getData()
	{
		return dataProvider.getList();
	}

	public void reset()
	{
		dataProvider.getList().clear();
	}
	
}
