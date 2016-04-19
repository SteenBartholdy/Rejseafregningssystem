package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class GlemtPassword extends Composite 
{	
	private VerticalPanel vPanel = new VerticalPanel();
	private TextBox mail;
	private Button btnOK;
	private Label info;
	
	public GlemtPassword()
	{
		initWidget(this.vPanel);
		
		info = new Label("Indtast mailadressen (brugernavn) for at få tilsendt dit kodeord: ");
		mail = new TextBox();
		btnOK = new Button("OK");
		
		vPanel.add(info);
		vPanel.add(mail);
		vPanel.add(btnOK);
	}
	
	
	
}
