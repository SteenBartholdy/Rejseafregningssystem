package dk.dtu.smmac.client.ui;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ProvidesKey;

import dk.dtu.smmac.server.dal.RejseDAO;
import dk.dtu.smmac.shared.RejseDTO;


public class DageInfo extends Composite 
{
	private CellTable<RejseDTO> table;
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnAnnuller;
	DateTimeFormat dtFmt = DateTimeFormat.getFormat("dd/MM yyyy");

	 private static final ProvidesKey<RejseDTO> KEY_PROVIDER = new ProvidesKey<RejseDTO>() {
		    @Override
		    public Object getKey(RejseDTO dag) {
		      return dag.getRejseID();
		    }
		  };
	
	public DageInfo(RejseDAO did)
	{
		table = new CellTable<RejseDTO>(KEY_PROVIDER);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		table.setPageSize(4);
		
		initWidget(this.vPanel);
		
		btnAnnuller = new Button("Annuller");

		TextColumn<RejseDag> datoColumn = new TextColumn<RejseDag>()
		{
			@Override
			public String getValue(RejseDag object) 
			{
				return object.getRejseDagDato();
			}
		};
		table.addColumn(datoColumn, "Dato:");

		TextColumn<RejseDag> landColumn = new TextColumn<RejseDag>()
		{
			@Override
			public String getValue(RejseDag object) 
			{
				return object.getRejseDagLand();
			}
		};
		table.addColumn(landColumn, "Land:");

		Column<RejseDag, Boolean> morgenmadColumn = new Column<RejseDag, Boolean>(new CheckboxCell()) { 
			@Override 
			public Boolean getValue(RejseDag object) { 
				return object.getRejseDagMorgenmad(); 
			} 
		}; 

		morgenmadColumn.setFieldUpdater(new FieldUpdater<RejseDag, Boolean>() { 
			@Override
			public void update(int index, RejseDag object, Boolean value) {
				object.setRejseDagMorgenmad(value); 
			} 
		}); 

		table.addColumn(morgenmadColumn, "Morgenmad:");

		Column<RejseDag, Boolean> frokostColumn = new Column<RejseDag, Boolean>(new CheckboxCell()) { 
			@Override 
			public Boolean getValue(RejseDag object) { 
				return object.getRejseDagFrokost(); 
			} 
		}; 

		frokostColumn.setFieldUpdater(new FieldUpdater<RejseDag, Boolean>() { 
			@Override
			public void update(int index, RejseDag object, Boolean value) {
				object.setRejseDagFrokost(value); 
			} 
		}); 

		table.addColumn(frokostColumn, "Frokost:");

		vPanel.add(table);
		vPanel.add(btnAnnuller);

		vPanel.setStyleName("margin");
	

	}

	public Button getbtnAnnullerDageInfo()
	{
		return btnAnnuller;
	}
}
