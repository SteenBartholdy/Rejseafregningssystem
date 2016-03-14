package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginTopView extends Composite {

	private static LoginTopViewUiBinder uiBinder = GWT.create(LoginTopViewUiBinder.class);

	@UiField
	Anchor login;


	interface LoginTopViewUiBinder extends UiBinder<Widget, LoginTopView> {
	}

	public LoginTopView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Anchor getLoginAnchor() {
		return login;
	}


}
