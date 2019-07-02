package com.viktoriia.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@SuppressWarnings("serial")
@Route("signin")
@StyleSheet("styles/styles.css")
public class SignIn extends VerticalLayout {

	public SignIn() {
		// HEADER
		Span title = new Span("My application");
		Tab createUser = new Tab(VaadinIcon.RECORDS.create(), new RouterLink("Sign up", SignUp.class));
		HorizontalLayout header = new HorizontalLayout(title, createUser);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(signIn());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace);

	}

	private Component signIn() {
		TextField userName = new TextField();
		PasswordField password = new PasswordField();
		Span username = new Span("Username");
		Span pass = new Span("Password");
		RouterLink login = new RouterLink("Login", Homepage.class);
		FlexLayout signInForm = new FlexLayout(username, userName, pass, password, login);
		signInForm.addClassName("signInForm");
		signInForm.setAlignItems(Alignment.CENTER);
		signInForm.setJustifyContentMode(JustifyContentMode.CENTER);
		signInForm.setWidth("100%");
		signInForm.setHeight("400px");
		return signInForm;
	}

}