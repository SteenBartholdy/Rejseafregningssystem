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
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();
	private Label lNyKode, lNyKodeVeri, lGammelKode, lBrugernavn, lBrugernavnHentet;
	private Button btnTilbage, btnUdfoer, btnUsynlig;
	private PasswordTextBox tbNyKode, tbNyKodeVeri, tbGammelKode;
	private String lwidth = "150px";
	private String height = "20px";
	
	public NyKode()
	{
		initWidget(this.vPanel);
		
		lGammelKode = new Label("Indtast nuværende adgangskode:");
		lGammelKode.setStyleName("boldTextPlusMargin");
		lNyKode = new Label("Indtats en ny adgangskode:");
		lNyKode.setStyleName("boldTextPlusMargin");
		lNyKodeVeri = new Label("Bekræft den nye adgangskode:");
		lNyKodeVeri.setStyleName("boldTextPlusMargin");
		lBrugernavn = new Label("Brugernavn:  ");
		lBrugernavn.setStyleName("boldTextPlusMargin");
		lBrugernavnHentet = new Label();
		lBrugernavnHentet.setStyleName("marginButtom");
		
		btnTilbage = new Button("Tilbage");
		btnTilbage.setStyleName("buttonNyKode");
		btnUdfoer = new Button("Udfør");
		btnUdfoer.setStyleName("buttonNyKode");
		btnUsynlig = new Button();
		btnUsynlig.setStyleName("buttonNyKode");
		btnUsynlig.setVisible(false);
				
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
		
		vPanel.add(hPanel1);
		hPanel1.add(lBrugernavn);
		hPanel1.add(lBrugernavnHentet);
		vPanel.add(lGammelKode);
		vPanel.add(tbGammelKode);
		vPanel.add(lNyKode);
		vPanel.add(tbNyKode);
		vPanel.add(lNyKodeVeri);
		vPanel.add(tbNyKodeVeri);
		vPanel.add(hPanel2);
		hPanel2.add(btnTilbage);	
		hPanel2.add(btnUdfoer);
		hPanel2.add(btnUsynlig);
		
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
		return lBrugernavnHentet.getText();
	}
	
	public void setBrugernavnL(String brugernavn) 
	{
		lBrugernavnHentet.setText(brugernavn);
	}

	public void reset()
	{
		tbNyKode.setValue("");
		tbNyKodeVeri.setValue("");
		tbGammelKode.setValue("");
	}
}

