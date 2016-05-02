package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AfslutningsInfo extends Composite {

	
	private VerticalPanel vPanel;
	private FlexTable fTable;
	private Label oversigt, befordringL, befordring, dagpengeL, dagpenge, udgifterL, udgifter, afregningTotalL, afregningTotal, refunderingL, refundering, forskudL, forskud, afregningL, afregning;
	private Button godkend, afvis;
	
	public AfslutningsInfo()
	{
		vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		
		fTable = new FlexTable();
		fTable.setStyleName("flextable");
		
		oversigt = new Label("Oversigt");
		oversigt.setStyleName("boldText");
		
		befordringL = new Label("Befordringsgodtgørelse:");
		befordring = new Label();
		
		dagpengeL = new Label("Time/dagpenge:");
		dagpenge = new Label();
		
		udgifterL = new Label("Udgifter:");
		udgifter = new Label();
		
		afregningTotalL = new Label("Afregningstotal:");
		afregningTotal = new Label();
		
		refunderingL = new Label("Til refundering:");
		refundering = new Label();
		
		forskudL = new Label("Forskud:");
		forskud = new Label();
		
		afregningL = new Label("Til afregning:");
		afregning = new Label();
		
		godkend = new Button("Godkend");
		afvis = new Button("Afvis og slet");
		
		fTable.setWidget(0, 0, oversigt);
		fTable.setWidget(1, 0, befordringL);
		fTable.setWidget(1, 1, befordring);
		fTable.setWidget(2, 0, dagpengeL);
		fTable.setWidget(2, 1, dagpenge);
		fTable.setWidget(3, 0, udgifterL);
		fTable.setWidget(3, 1, udgifter);
		fTable.setWidget(4, 0, afregningTotalL); 
		fTable.setWidget(4, 1, afregningTotal);
		fTable.setWidget(5, 0, refunderingL);
		fTable.setWidget(5, 1, refundering);
		fTable.setWidget(6, 0, forskudL);
		fTable.setWidget(6, 1, forskud);
		fTable.setWidget(7, 0, afregningL);
		fTable.setWidget(7, 1, afregning);
		fTable.setWidget(8, 0, godkend);
		fTable.setWidget(8, 1, afvis);
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
	}
	
	
	public void setBefordring(String befordring)
	{
		this.befordring.setText(befordring + " DKK"); 
	}
	
	public void setDagpenge(String dagpenge)
	{
		this.dagpenge.setText(dagpenge + " DKK");
	}
	
	public void setUdgifter(String udgifter)
	{
		this.udgifter.setText(udgifter + " DKK");
	}
	
	public void setAfregningTotal(String afregningTotal)
	{
		this.afregningTotal.setText(afregningTotal + " DKK");
	}
	
	public void setRefundering(String refundering)
	{
		this.refundering.setText(refundering + " DKK");
	}
	
	public void setForskud(String forskud)
	{
		this.forskud.setText(forskud + " DKK");
	}
	
	public void setAfregning(String afregning)
	{
		this.afregning.setText(afregning + " DKK");
	}
	
	public Button getGodkendButton()
	{
		return godkend;
	}
	
	public Button getAfvisButton()
	{
		return afvis;
	}
	
}
