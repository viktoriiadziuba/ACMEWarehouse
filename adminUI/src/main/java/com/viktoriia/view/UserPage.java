package com.viktoriia.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.User;

@SuppressWarnings("serial")
@Route("userpage")
@StyleSheet("styles/styles.css")
public class UserPage extends VerticalLayout {

	private Grid<User> grid;

	public UserPage() {
		grid = new Grid<>((User.class));
		// HEADER
		Span title = new Span("User page");
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(title, home, exit);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(createCard());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace);
	}

	private Component createCard() {
		VerticalLayout user = new VerticalLayout(grid);
		grid.setColumns("id", "userName", "fullName", "phoneNumber", "email", "dateOfBirth", "role");
		user.addClassName("card");
		user.add(grid);
		user.setAlignItems(Alignment.CENTER);
		user.setJustifyContentMode(JustifyContentMode.CENTER);
		user.setWidth("100%");
		user.setHeight("1000px");
		return user;

	}
}
