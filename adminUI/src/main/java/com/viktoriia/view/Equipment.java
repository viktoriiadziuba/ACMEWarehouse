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
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.view.addview.AddEquipment;

@SuppressWarnings("serial")
@Route("equipment")
@StyleSheet("frontend://styles/styles.css")
public class Equipment extends VerticalLayout {

	private Grid<EquipmentEntity> grid;

	public Equipment() {
		grid = new Grid<>((EquipmentEntity.class));
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("My application");
		Tab addEquipment = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Create Equipment", AddEquipment.class));
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(drawer, title, addEquipment, home, exit);
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
		VerticalLayout equipment = new VerticalLayout(grid);
		grid.setColumns("id", "type");
		equipment.addClassName("card");
		equipment.add(grid);
		equipment.setAlignItems(Alignment.CENTER);
		equipment.setJustifyContentMode(JustifyContentMode.CENTER);
		equipment.setWidth("100%");
		equipment.setHeight("auto");
		return equipment;
	}

}