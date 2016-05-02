package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;

import dk.dtu.smmac.shared.UdgifterDTO;

public class Udgifter extends Composite
{
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnTilbage, btnNyUdgift;
	private CellTable<UdgifterDTO> table;


	private static final ProvidesKey<UdgifterDTO> KEY_PROVIDER = new ProvidesKey<UdgifterDTO>() {
		@Override
		public Object getKey(UdgifterDTO udgift) {
			return udgift.getBilagsNummer();
		}
	};

	public Udgifter()
	{
		ArrayList<String> udgiftstyper = new ArrayList<String>();
		udgiftstyper.add("Transport");
		udgiftstyper.add("Forplejning");
		udgiftstyper.add("Beklædning");
		udgiftstyper.add("Andet");

		table = new CellTable<UdgifterDTO>(KEY_PROVIDER);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		initWidget(this.vPanel);

		btnTilbage = new Button("Tilbage");
		btnNyUdgift = new Button("Tilføj ny udgift");

		// **** Add column that shows "Udgiftstype" ****

		final SelectionCell udgiftstyperCell = new SelectionCell(udgiftstyper);
		Column<UdgifterDTO, String> udgiftstyperColumn = new Column<UdgifterDTO, String>(udgiftstyperCell) {

			@Override
			public String getValue(UdgifterDTO object) 
			{
				return object.getUdgiftType();
			}

		};
		table.addColumn(udgiftstyperColumn, "Udgiftstype:");

		udgiftstyperColumn.setFieldUpdater(new FieldUpdater<UdgifterDTO, String>() 
		{
			@Override
			public void update(int index, UdgifterDTO object, String value) 
			{		       
				object.setUdgiftType(value);
				table.redraw();
			}

		});

		// **** Add a text input column to set "Bilagsnummer" (kilde: "celltable-eksemplet") ****

		final TextInputCell bilagsnummerCell = new TextInputCell();
		Column<UdgifterDTO, String> bilagsnummerColumn = new Column<UdgifterDTO, String>(bilagsnummerCell) {
			@Override
			public String getValue(UdgifterDTO object) {
				return object.getBilagsNummer();
			}
		};
		table.addColumn(bilagsnummerColumn, "Bilagsnummer:");

		// **** Add a field updater to be notified when the user enters a "Bilagsnummer" ****

		bilagsnummerColumn.setFieldUpdater(new FieldUpdater<UdgifterDTO, String>() {
			@Override
			public void update(int index, UdgifterDTO object, String value) {

				// **** Validate the data ****

				if(!dk.dtu.smmac.shared.FieldVerifier.isValidTal(value))
				{
					Window.alert("Indtast venligst et nummer.");
					bilagsnummerCell.clearViewData(KEY_PROVIDER.getKey(object));

					table.redraw();
					return;
				}

				object.setBilagsNummer(value);
				table.redraw();
			}
		});

		// **** Add a text input column to set "Dato" (kilde: "celltable-eksemplet") ****

		final TextInputCell datoCell = new TextInputCell();
		Column<UdgifterDTO, String> datoColumn = new Column<UdgifterDTO, String>(datoCell) {
			@Override
			public String getValue(UdgifterDTO object) {
				return object.getUdgiftDato();
			}
		};
		table.addColumn(datoColumn, "Dato:");

		// **** Add a field updater to be notified when the user enters a "Dato" ****

		datoColumn.setFieldUpdater(new FieldUpdater<UdgifterDTO, String>() {
			@Override
			public void update(int index, UdgifterDTO object, String value) {

				object.setUdgiftDato(value);
				table.redraw();
			}
		});

		// **** Add a text input column to set "Beløb" (kilde: "celltable-eksemplet") ****

		final TextInputCell beloebCell = new TextInputCell();
		Column<UdgifterDTO, String> beloebColumn = new Column<UdgifterDTO, String>(beloebCell) {
			@Override
			public String getValue(UdgifterDTO object) {
				return "" + object.getUdgiftBeloeb();
			}
		};
		table.addColumn(beloebColumn, "Beløb:");

		// **** Add a field updater to be notified when the user enters a "Beløb" ****

		beloebColumn.setFieldUpdater(new FieldUpdater<UdgifterDTO, String>() {
			@Override
			public void update(int index, UdgifterDTO object, String value) {

				// **** Validate the data ****

				if(!dk.dtu.smmac.shared.FieldVerifier.isValidTal(value))
				{
					Window.alert("Indtast venligst et korrekt beløb.");
					beloebCell.clearViewData(KEY_PROVIDER.getKey(object));
					table.redraw();
					return;
				}

				object.setUdgiftBeloeb(value);
				table.redraw();
			}
		});

		vPanel.setStyleName("margin");
		vPanel.add(table);
		vPanel.add(btnNyUdgift);
		btnNyUdgift.setStyleName("marginTop");
		vPanel.add(btnTilbage);
		btnTilbage.setStyleName("marginTop");
	}

	public Button getBtnNyUdgift()
	{
		return btnNyUdgift;
	}

	public Button getBtnTilbage()
	{
		return btnTilbage;
	}

}
