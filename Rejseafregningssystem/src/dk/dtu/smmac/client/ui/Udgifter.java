package dk.dtu.smmac.client.ui;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.UdgifterDTO;

public class Udgifter extends Composite
{
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnGem, btnAnnuller;
	private CellTable<UdgifterDTO> table;
}
