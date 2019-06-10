package com.viktoriia.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("signup")
@StyleSheet("styles/styles.css")
public class SignUp extends VerticalLayout {

	public SignUp() {
		// HEADER
		Span title = new Span("Warehouse");
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		HorizontalLayout header = new HorizontalLayout(title, home);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(signUp());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace);

	}

	private Component signUp() {
		FormLayout layout = new FormLayout();

		// Create the fields
		TextField firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		TextField lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		TextField phone = new TextField();
		phone.setValueChangeMode(ValueChangeMode.EAGER);
		TextField email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		DatePicker birthDate = new DatePicker();

		Tab save = new Tab(new RouterLink("Save", SignIn.class));

		layout.addFormItem(firstName, "First name");
		layout.addFormItem(lastName, "Last name");
		layout.addFormItem(birthDate, "Birthdate");
		layout.addFormItem(email, "E-mail");
		layout.addFormItem(phone, "Phone");

		// Button bar
		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save);
		save.getStyle().set("marginRight", "10px");

		layout.add(actions);

		return layout;
	}
}
