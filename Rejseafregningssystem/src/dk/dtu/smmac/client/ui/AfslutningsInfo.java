package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AfslutningsInfo extends Composite {

	private VerticalPanel vPanel;
	private HorizontalPanel hPanel;
	private FlexTable fTable;
	private Label oversigt, befordringL, befordring, dagpengeL, dagpenge, udgifterL, udgifter, afregningTotalL, afregningTotal, refunderingL, refundering, forskudL, forskud, afregningL, afregning;
	private Button godkend, afvis, back;
	private double bef, dag, ud, af, ref, afto, fo;
	
	public AfslutningsInfo()
	{
		vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		hPanel = new HorizontalPanel();
		
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
		back = new Button("Tilbage");
		
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
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
		vPanel.add(hPanel);
		hPanel.add(back);
		hPanel.add(godkend);
		hPanel.add(afvis);
		back.setStyleName("marginRight");
		godkend.setStyleName("marginRight");
		afvis.setStyleName("marginLeft");
	}
	
	
	public void setBefordring(double befordring)
	{
		this.bef = befordring;
		this.befordring.setText(befordring + " DKK"); 
	}
	
	public void setDagpenge(double dagpenge)
	{
		this.dag = dagpenge;
		this.dagpenge.setText(dagpenge + " DKK");
	}
	
	public void setUdgifter(double udgifter)
	{
		this.ud = udgifter;
		this.udgifter.setText(udgifter + " DKK");
	}
	
	public void setAfregningTotal()
	{
		this.afto = bef + dag + ud;
		this.afregningTotal.setText(this.afto + " DKK");
	}
	
	public void setRefundering(double refundering)
	{
		this.ref = refundering;
		this.refundering.setText(refundering + " DKK");
	}
	
	public void setForskud(double forskud)
	{
		this.fo = forskud;
		this.forskud.setText(forskud + " DKK");
	}
	
	public void setAfregning()
	{
		this.af = bef + ud + ref - fo;
		this.afregning.setText(af + " DKK");
	}
	
	public Button getGodkendButton()
	{
		return godkend;
	}
	
	public Button getAfvisButton()
	{
		return afvis;
	}
	
	public Button getBackButton() 
	{
		return back;
	}
	
}
