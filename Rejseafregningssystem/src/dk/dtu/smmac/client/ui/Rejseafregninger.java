package dk.dtu.smmac.client.ui;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import dk.dtu.smmac.shared.RejseafregningDTO;

public class Rejseafregninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private static List<RejseafregningDTO> LIST = Arrays.asList(new RejseafregningDTO(1, 0, 8, 9), new RejseafregningDTO(2, 0, 8, 10));
	
	public Rejseafregninger() {
		initWidget(this.vPanel);
		
		CellTable<RejseafregningDTO> table = new CellTable<RejseafregningDTO>();

		TextColumn<RejseafregningDTO> idColumn = new TextColumn<RejseafregningDTO>() {

			@Override
			public String getValue(RejseafregningDTO obj) {
				return ""+obj.getId();
			}
		};
		
		table.addColumn(idColumn, "Id");
		
		ListDataProvider<RejseafregningDTO> dataProvider = new ListDataProvider<RejseafregningDTO>();
		
		dataProvider.addDataDisplay(table);
		
		List<RejseafregningDTO> list = dataProvider.getList();
	    for (RejseafregningDTO contact : LIST) {
	      list.add(contact);
	    }
	    
	    vPanel.setStyleName("margin");
		vPanel.add(table);
	}

}
