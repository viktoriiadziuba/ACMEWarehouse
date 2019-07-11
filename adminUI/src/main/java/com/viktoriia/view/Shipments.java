package com.viktoriia.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.Shipment;
import com.viktoriia.view.addview.AddShipment;

@SuppressWarnings("serial")
@Route("shipments")
@StyleSheet("frontend://styles/styles.css")
public class Shipments extends VerticalLayout {

	private Grid<Shipment> grid;

	public Shipments() {
		grid = new Grid<>((Shipment.class));

		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("My application");
		Tab addShipment = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Create Shipment", AddShipment.class));
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(drawer, title, addShipment, home, exit);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(createCard());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// MENU
		VerticalLayout sideMenu = new VerticalLayout();
		sideMenu.addClassName("sideMenu");
		sideMenu.setHeight("100%");
		sideMenu.setWidth("auto");
		sideMenu.setSpacing(false);
		drawer.getElement().addEventListener("click", ev -> sideMenu.getStyle().set("left", "0px"));
		Icon avatar = VaadinIcon.USER.create();
		avatar.setSize("4em");
		sideMenu.add(avatar, new Span("Username"), new RouterLink("User profile", UserPage.class));
		sideMenu.setAlignItems(Alignment.CENTER);

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(sideMenu, header, workspace);
	}

	private Component createCard() {
		VerticalLayout shipment = new VerticalLayout(grid);
		grid.setColumns("id", "dateOfShipment", "description");
		shipment.addClassName("card");
		shipment.add(grid);
		shipment.setAlignItems(Alignment.CENTER);
		shipment.setJustifyContentMode(JustifyContentMode.CENTER);
		shipment.setWidth("100%");
		shipment.setHeight("auto");
		return shipment;
	}

}
