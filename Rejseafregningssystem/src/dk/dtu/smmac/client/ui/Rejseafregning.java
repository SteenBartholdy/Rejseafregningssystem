package dk.dtu.smmac.client.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import dk.dtu.smmac.shared.RejseDTO;
import dk.dtu.smmac.shared.RejseafregningDTO;

public class Rejseafregning extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label date, dateTo, startTimeLabel, endTimeLabel;
	private DateLabel startDateLabel, endDateLabel;
	private ListBox startTime, endTime;
	private Anchor bilag, addTravel;
	private Button edit, delete, save;
	private RejseafregningDTO rejseafregning;
	private DateTimeFormat dateFormat;
	private ListDataProvider<RejseDTO> dataProvider;
	private CellTable<RejseDTO> table;
	private final SingleSelectionModel<RejseDTO> model;

	public Rejseafregning()
	{
		initWidget(this.vPanel);

		fTable = new FlexTable();

		table = new CellTable<RejseDTO>();
		model = new SingleSelectionModel<RejseDTO>();
		table.setSelectionModel(model);

		TextColumn<RejseDTO> landColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getLand();
			}
		};

		TextColumn<RejseDTO> datoFraColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+dateFormat.format(obj.getDatoFra());
			}
		};

		TextColumn<RejseDTO> datoTilColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+dateFormat.format(obj.getDatoTil());
			}
		};

		TextColumn<RejseDTO> projektColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getProjekt();
			}
		};

		TextColumn<RejseDTO> assignmentColumn = new TextColumn<RejseDTO>() {

			@Override
			public String getValue(RejseDTO obj) {
				return ""+obj.getOpgave();
			}
		};

		table.addColumn(landColumn, "Land");
		table.addColumn(datoFraColumn, "Fra dato");
		table.addColumn(datoTilColumn, "Til dato");
		table.addColumn(projektColumn, "Projekt");
		table.addColumn(assignmentColumn, "Opgave");

		dataProvider = new ListDataProvider<RejseDTO>();
		dataProvider.addDataDisplay(table);

		date = new Label("Dato:");

		dateFormat = DateTimeFormat.getFormat("dd/MM-yyyy");

		startDateLabel = new DateLabel(dateFormat);
		endDateLabel = new DateLabel(dateFormat);

		dateTo = new Label("Til");

		startTimeLabel = new Label("Starttid:");
		endTimeLabel = new Label("Sluttid:");

		bilag = new Anchor();
		bilag.setText("Tilføj bilag");
		bilag.setStyleName("anchorStyle");

		addTravel = new Anchor();
		addTravel.setText("Tilføj rejse");
		addTravel.setStyleName("anchorStyle");

		save = new Button();
		save.setText("Fortsæt");
		save.setStyleName("alignButtomRight");

		startTime = new ListBox();
		endTime = new ListBox();

		for(int i = 0; i<10; i++)
		{
			startTime.addItem("0" + i + ":00");
			endTime.addItem("0" + i + ":00");
		}
		for(int i = 10; i<24; i++)
		{
			startTime.addItem("" + i + ":00");
			endTime.addItem("" + i + ":00");
		}

		fTable.setWidget(0, 0, date);
		fTable.setWidget(0, 1, startDateLabel);
		fTable.setWidget(0, 2, dateTo);
		fTable.setWidget(0, 3, endDateLabel);
		fTable.setWidget(1, 0, startTimeLabel);
		fTable.setWidget(1, 1, startTime);
		fTable.setWidget(1, 2, endTimeLabel);
		fTable.setWidget(1, 3, endTime);
		fTable.setWidget(2, 0, addTravel);
		fTable.setWidget(2, 2, bilag);

		vPanel.setStyleName("margin");
		vPanel.add(fTable);
		vPanel.add(table);
		vPanel.add(save);
	}

	public void reset() {
		this.startTime.setSelectedIndex(0);
		this.endTime.setSelectedIndex(0);
		this.startDateLabel.setValue(null);
		this.endDateLabel.setValue(null);

		dataProvider.getList().clear();
	}

	public void setRejseafregning(RejseafregningDTO rejseafregning) {
		this.startTime.setSelectedIndex(rejseafregning.getSlutTid());
		this.endTime.setSelectedIndex(rejseafregning.getStartTid());
		
		this.rejseafregning = rejseafregning;
	}

	public RejseafregningDTO getRejseafregning() {
		this.rejseafregning.setStartTid(getStartTime());
		this.rejseafregning.setSlutTid(getEndTime());

		return this.rejseafregning;
	}

	public Anchor getBilagButton()
	{
		return bilag;
	}

	public void setStartTime(int starttime)
	{
		startTime.setItemSelected(starttime, true);
	}

	public int getStartTime() {
		String str = startTime.getValue(startTime.getSelectedIndex());
		str = str.substring(0, 2);
		return Integer.parseInt(str);
	}

	public int getEndTime() {
		String str = endTime.getValue(endTime.getSelectedIndex());
		str = str.substring(0, 2);
		return Integer.parseInt(str);
	}

	public void setEndTime(int endtime)
	{
		endTime.setItemSelected(endtime, true);
	}

	public void addTravelSummary(RejseDTO rejse)
	{
		if(dataProvider.getList().contains(rejse)) {

		} else {
			dataProvider.getList().add(rejse);
		}
	}

	public Anchor getAddTravelAnchor()
	{
		return addTravel;
	}

	public Button getEditButton()
	{
		return edit;
	}

	public Button getDeleteButton()
	{
		return delete;
	}

	public Button getSaveButton() 
	{
		return save;
	}

	public void setStartDateLabel() {
		List<Date> list = new ArrayList<Date>();

		for (int i = 0; dataProvider.getList().size() > i; i++) {
			list.add(dataProvider.getList().get(i).getDatoFra());
		}

		Collections.sort(list);
		java.util.Date date = new java.util.Date(list.get(0).getTime());
		startDateLabel.setValue(date);
	}

	public void setEndDateLabel() {
			int i;
			List<Date> list = new ArrayList<Date>();

			for (i = 0; dataProvider.getList().size() > i; i++) {
				list.add(dataProvider.getList().get(i).getDatoTil());
			}

			Collections.sort(list);
			java.util.Date date = new java.util.Date(list.get(i-1).getTime());
			endDateLabel.setValue(date);
	}

	public CellTable<RejseDTO> getTable() {
		return this.table;
	}

	public SingleSelectionModel<RejseDTO> getModel() {
		return this.model;
	}

}
