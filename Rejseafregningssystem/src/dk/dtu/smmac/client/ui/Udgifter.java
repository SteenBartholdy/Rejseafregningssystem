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
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Button btnTilbage;
	private CellTable<UdgifterDTO> table;
	//List<String> udgiftstyper = new ArrayList<String>();

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

	    	if(!dk.dtu.smmac.shared.FieldVerifier.isValidTal(value)){
	    		Window.alert("Indtast venligst et nummer.");
	    		

	          /*
	           * Clear the view data. The view data contains the pending change and
	           * allows the table to render with the pending value until the data is
	           * committed. If the data is committed into the object, the view data
	           * is automatically cleared out. If the data is not committed because
	           * it is invalid, you must delete.
	           */
	          bilagsnummerCell.clearViewData(KEY_PROVIDER.getKey(object));

	          // **** Redraw the table. ****
	          table.redraw();
	          return;
	        }
	 
	        // **** Push the changes into the Contact. At this point, you could send an ****
	        // **** asynchronous request to the server to update the database. ****
	        object.setBilagsNummer(value);

	        // **** Redraw the table with the new data. ****
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
	       
	    	// **** Validate the data ****

//	    	if(!dk.dtu.smmac.shared.FieldVerifier.isValidTal(value)){
//	    		Window.alert("Indtast venligst et nummer.");
//	    		
//
//	          /*
//	           * Clear the view data. The view data contains the pending change and
//	           * allows the table to render with the pending value until the data is
//	           * committed. If the data is committed into the object, the view data
//	           * is automatically cleared out. If the data is not committed because
//	           * it is invalid, you must delete.
//	           */
//	          datoCell.clearViewData(KEY_PROVIDER.getKey(object));
//
//	          // Redraw the table.
//	          table.redraw();
//	          return;
//	        }

	        // **** Push the changes into the Contact. At this point, you could send an ****
	        // **** asynchronous request to the server to update the database. ****
	    	  
	        object.setUdgiftDato(value);

	        // **** Redraw the table with the new data. ****
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

	    	if(!dk.dtu.smmac.shared.FieldVerifier.isValidTal(value)){
	    		Window.alert("Indtast venligst et korrekt beløb.");
	    		

	          /*
	           * Clear the view data. The view data contains the pending change and
	           * allows the table to render with the pending value until the data is
	           * committed. If the data is committed into the object, the view data
	           * is automatically cleared out. If the data is not committed because
	           * it is invalid, you must delete.
	           */
	          beloebCell.clearViewData(KEY_PROVIDER.getKey(object));

	          // **** Redraw the table. ****
	          table.redraw();
	          return;
	        }

	        // **** Push the changes into the Contact. At this point, you could send an ****
	        // **** asynchronous request to the server to update the database. ****
	        object.setUdgiftBeloeb(value);

	        // **** Redraw the table with the new data. ****
	        table.redraw();
	      }
	    });
	
	    vPanel.setStyleName("margin");
	    hPanel.setStyleName("margin");
	    vPanel.add(table);
	    vPanel.add(hPanel);
	    hPanel.add(btnTilbage);
	    
	}
}
