package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NyKode extends Composite 
{
	private VerticalPanel vPanel = new VerticalPanel();
	private Label lNyKode, lNyKodeVeri, lGammelKode, lBrugernavn;
	private Button btnTilbage, btnUdfoer;
	private TextBox tbNyKode, tbNyKodeVeri, tbGammelKode, tbBrugernavn;	
	private String lwidth = "100px";
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
		lBrugernavn = new Label("Indtast brugernavn:");
		lBrugernavn.setStyleName("boldTextPlusMargin");
		
		btnTilbage = new Button("Tilbage");
		btnUdfoer = new Button("Udfør");
		
		tbBrugernavn = new TextBox();
		tbBrugernavn.setWidth(lwidth);
		tbBrugernavn.setHeight(height);
		tbNyKode = new TextBox();
		tbNyKode.setWidth(lwidth);
		tbNyKode.setHeight(height);
		tbNyKodeVeri = new TextBox();
		tbNyKodeVeri.setWidth(lwidth);
		tbNyKodeVeri.setHeight(height);
		tbGammelKode = new TextBox();
		tbGammelKode.setWidth(lwidth);
		tbGammelKode.setHeight(height);
		
		vPanel.add(lGammelKode);
		vPanel.add(tbGammelKode);
		vPanel.add(lNyKode);
		vPanel.add(tbNyKode);
		vPanel.add(lNyKodeVeri);
		vPanel.add(tbNyKodeVeri);
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
}

