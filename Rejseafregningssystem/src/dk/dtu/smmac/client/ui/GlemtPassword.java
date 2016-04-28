package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class GlemtPassword extends Composite 
{	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private TextBox mail;
	private Button btnSend, btnAnnuller;
	private Label info;
	
	public GlemtPassword()
	{
		initWidget(this.vPanel);
		
		info = new Label("Indtast dit brugernavn for at få tilsendt dit kodeord: ");
		mail = new TextBox();
		btnSend = new Button("Send");
		btnAnnuller = new Button("Annuller");
		
		
		vPanel.add(info);
		vPanel.add(mail);
		hPanel.add(btnSend);
		hPanel.add(btnAnnuller);
		vPanel.add(hPanel);
		
		vPanel.setStyleName("margin");
		info.setStyleName("marginButtom");
		mail.setStyleName("marginButtom");
		btnSend.setHeight("30px");
		btnAnnuller.setHeight("30px");
		btnSend.setStyleName("marginRight");
		btnAnnuller.setStyleName("marginRight");		
	}
	
	public Button getbtnSendPassword()
	{
		return btnSend;
	}
	
	public Button getbtnAnnullerPassword()
	{
		return btnAnnuller;
	}

	public String getMailPassword()
	{
		return mail.getText();
	}
	
	public TextBox getMailTextField() {
		return mail;
	}

}
