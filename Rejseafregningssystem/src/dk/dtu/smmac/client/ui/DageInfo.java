package dk.dtu.smmac.client.ui;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;

import dk.dtu.smmac.shared.DageInfoDTO;


public class DageInfo extends Composite 
{
	private CellTable<DageInfoDTO> table;
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnAnnuller;
	DateTimeFormat dtFmt = DateTimeFormat.getFormat("dd/MM yyyy");

	 private static final ProvidesKey<DageInfoDTO> KEY_PROVIDER = new ProvidesKey<DageInfoDTO>() {
		    @Override
		    public Object getKey(DageInfoDTO dag) {
		      return dag.getDagID();
		    }
		  };
	
	public DageInfo()//RejseDAO did)
	{
		table = new CellTable<DageInfoDTO>(KEY_PROVIDER);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		table.setPageSize(8);
		
		initWidget(this.vPanel);
		
		btnAnnuller = new Button("Annuller");

		// **** Add a checkbox input column that shows "DagID" ****
		
		Column<DageInfoDTO, String> dagidColumn = new Column<DageInfoDTO, String>(null) {
			@Override
			public String getValue(DageInfoDTO object) {
				return Integer.toString(object.getDagID());
			}
		};
		
		table.addColumn(dagidColumn, "DagID:");
		
		// **** Add a checkbox input column that shows "Morgenmad" ****
		
	    Column<DageInfoDTO, Boolean> morgenmadColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getMorgenmad(); 
	      } 
	    }; 

	    morgenmadColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setMorgenmad(value); 
	      } 
	    }); 
	    
	    table.addColumn(morgenmadColumn, "Morgenmad:");
		
	    // **** Add a checkbox input column that shows "Frokost" ****
	    
	    Column<DageInfoDTO, Boolean> frokostColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getFrokost(); 
	      } 
	    }; 

	    frokostColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setFrokost(value); 
	      } 
	    }); 
	    
	    table.addColumn(frokostColumn, "Frokost:");
	    
	    // **** Add a checkbox input column that shows "Aftensmad" ****
	    
	    Column<DageInfoDTO, Boolean> aftensmadColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getAftensmad(); 
	      } 
	    }; 

	    aftensmadColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setAftensmad(value); 
	      } 
	    }); 
	    
	    table.addColumn(aftensmadColumn, "Aftensmad:");
	    
	    // **** Add a checkbox input column that shows "Nattillæg" ****
	    
	    Column<DageInfoDTO, Boolean> nattillColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getNattill(); 
	      } 
	    }; 

	    nattillColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setNattill(value); 
	      } 
	    }); 
	    
	    table.addColumn(nattillColumn, "Nattillæg:");

	    // **** Add a checkbox input column that shows "Rejse Afbrudt" ****
	    
	    Column<DageInfoDTO, Boolean> rejseafbrudtColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getRejseAfbrudt(); 
	      } 
	    }; 

	    rejseafbrudtColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setRejseAfbrudt(value); 
	      } 
	    }); 
	    
	    table.addColumn(rejseafbrudtColumn, "Rejse Afbrudt:");
	    
	    // **** Add a checkbox input column that shows "Udokumenteret nattillæg" ****
	    
	    Column<DageInfoDTO, Boolean> udoknatColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getUdokNat(); 
	      } 
	    }; 

	    udoknatColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setUdokNat(value); 
	      } 
	    }); 
	    
	    table.addColumn(udoknatColumn, "Udokumenteret Nattillæg:");
	    
	    // **** Add a checkbox input column that shows "Refunderes" ****
	    
	    Column<DageInfoDTO, Boolean> refunderesColumn = new Column<DageInfoDTO, Boolean>(new CheckboxCell()) { 
	      @Override 
	      public Boolean getValue(DageInfoDTO object) { 
	        return object.getRefunderes(); 
	      } 
	    }; 

	    refunderesColumn.setFieldUpdater(new FieldUpdater<DageInfoDTO, Boolean>() { 
	      public void update(int index, DageInfoDTO object, Boolean value) {
	        object.setRefunderes(value); 
	      } 
	    }); 
	    
	    table.addColumn(refunderesColumn, "Refunderes:");
	    
		vPanel.add(table);
		vPanel.add(btnAnnuller);
		vPanel.setStyleName("margin");
	}

	public Button getbtnAnnullerDageInfo()
	{
		return btnAnnuller;
	}
}
