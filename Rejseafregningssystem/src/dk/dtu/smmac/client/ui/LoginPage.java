package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage extends Composite {

	private static LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

	interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
	}

	@UiField
	Button login;
	@UiField
	Button glemtPassword;
	@UiField
	TextBox brugernavn;
	@UiField 
	PasswordTextBox password;
	
	public LoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Button getLoginButton(){
		return login;
	}
	
	public Button getGlemtPasswordButton()
	{
		return glemtPassword;
	}
	
	public String getBrugernavn()
	{
		return brugernavn.getText();
	}
	
	public String getPassword()
	{
		return password.getText();
	}
}
