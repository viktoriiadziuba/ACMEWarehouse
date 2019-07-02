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
import com.viktoriia.entity.Storage;
import com.viktoriia.view.addview.AddStorage;

@SuppressWarnings("serial")
@Route("storages")
@StyleSheet("frontend://styles/styles.css")
public class Storages extends VerticalLayout {

	private Grid<Storage> grid;

	public Storages() {
		grid = new Grid<>((Storage.class));
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("My application");
		Tab addStorage = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Create Storage", AddStorage.class));
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(drawer, title, addStorage, home, exit);
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
		VerticalLayout storage = new VerticalLayout(grid);
		grid.setColumns("id", "address");
		storage.addClassName("card");
		storage.add(grid);
		storage.setAlignItems(Alignment.CENTER);
		storage.setJustifyContentMode(JustifyContentMode.CENTER);
		storage.setWidth("100%");
		storage.setHeight("auto");
		return storage;
	}

}