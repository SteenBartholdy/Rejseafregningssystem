package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NyKode extends Composite 
{
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label lNyKode, lNyKodeVeri, lGammelKode, lBrugernavn;
	private Button btnTilbage, btnUdfoer, btnUsynlig;
	private Label tbBrugernavn;
	private PasswordTextBox tbNyKode, tbNyKodeVeri, tbGammelKode;
	private String lwidth = "150px";
	private String height = "20px";
	private String bwidth = "50px";
	
	public NyKode()
	{
		initWidget(this.vPanel);
		
		lGammelKode = new Label("Indtast nuværende adgangskode:");
		lGammelKode.setStyleName("boldTextPlusMargin");
		lNyKode = new Label("Indtats en ny adgangskode:");
		lNyKode.setStyleName("boldTextPlusMargin");
		lNyKodeVeri = new Label("Bekræft den nye adgangskode:");
		lNyKodeVeri.setStyleName("boldTextPlusMargin");
		lBrugernavn = new Label("Indtast brugernavn:");
		lBrugernavn.setStyleName("boldTextPlusMargin");
		
		btnTilbage = new Button("Tilbage");
		//btnTilbage.setWidth(bwidth);
		//btnTilbage.setHeight(height);
		btnUdfoer = new Button("Udfør");
		//btnUdfoer.setWidth(bwidth);
		btnUdfoer.setStyleName("marginButtom");
		btnUdfoer.setHeight(height);
//		btnUsynlig = new Button();
//		btnUsynlig.setVisible(false);
		
		
		tbBrugernavn = new Label();
		tbBrugernavn.setWidth(lwidth);
		tbBrugernavn.setHeight(height);
		tbBrugernavn.setStyleName("marginButtom");
		tbNyKode = new PasswordTextBox();
		tbNyKode.setWidth(lwidth);
		tbNyKode.setHeight(height);
		tbNyKode.setStyleName("marginButtom");
		tbNyKodeVeri = new PasswordTextBox();
		tbNyKodeVeri.setWidth(lwidth);
		tbNyKodeVeri.setHeight(height);
		tbNyKodeVeri.setStyleName("marginButtom");
		tbGammelKode = new PasswordTextBox();
		tbGammelKode.setWidth(lwidth);
		tbGammelKode.setHeight(height);
		tbGammelKode.setStyleName("marginButtom");
		
		vPanel.add(lBrugernavn);
		vPanel.add(tbBrugernavn);
		vPanel.add(lGammelKode);
		vPanel.add(tbGammelKode);
		vPanel.add(lNyKode);
		vPanel.add(tbNyKode);
		vPanel.add(lNyKodeVeri);
		vPanel.add(tbNyKodeVeri);
		//vPanel.add(hPanel);
		vPanel.add(btnUdfoer);
		vPanel.add(btnTilbage);	
		
		vPanel.setStyleName("margin");
	}
	
	public Button getBtnNyKodeTilbage()
	{
		return btnTilbage;
	}

	public Button getBtnNyKodeUdfoer()
	{
		return btnUdfoer;
	}

	public String getNyKodeTB()
	{
		return tbNyKode.getText();
	}

	public String getNyKodeVeriTB()
	{
		return tbNyKodeVeri.getText();
	}

	public String getGammelKodeTB()
	{
		return tbGammelKode.getText();
	}

	public String getBrugernavnTB()
	{
		return tbBrugernavn.getText();
	}
	
	public void setBrugernavnL(String brugernavn) 
	{
		tbBrugernavn.setText(brugernavn);
	}

	public void reset()
	{
		tbNyKode.setValue("");
		tbNyKodeVeri.setValue("");
		tbGammelKode.setValue("");
	}
}

