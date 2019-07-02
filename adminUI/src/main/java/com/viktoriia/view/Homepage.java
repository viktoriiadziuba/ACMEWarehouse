package com.viktoriia.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@SuppressWarnings("serial")
@Route("homepage")
@StyleSheet("frontend://styles/styles.css")
public class Homepage extends VerticalLayout {

	public Homepage() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("My application");
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(drawer, title, exit);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(createCard(), createCard(), createCard(), createCard());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab actionButton2 = new Tab(VaadinIcon.USERS.create(), new RouterLink("Employees", Employees.class));
		Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Storages", Storages.class));
		Tab actionButton4 = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Equipment", Equipment.class));
		Tab actionButton5 = new Tab(VaadinIcon.CLIPBOARD_TEXT.create(), new RouterLink("Orders", Orders.class));
		Tab actionButton6 = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Shipments", Shipments.class));
		Tab actionButton7 = new Tab(VaadinIcon.SUITCASE.create(), new RouterLink("Goods", Goods.class));
		actionButton1.setClassName("tab");
		actionButton2.setClassName("tab");
		actionButton3.setClassName("tab");
		actionButton4.setClassName("tab");
		actionButton5.setClassName("tab");
		actionButton6.setClassName("tab");
		actionButton7.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1, actionButton2, actionButton3, actionButton3, actionButton4,
				actionButton5, actionButton6, actionButton7);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");

		// MENU
		VerticalLayout sideMenu = new VerticalLayout();
		sideMenu.addClassName("sideMenu");
		sideMenu.setHeight("100%");
		sideMenu.setWidth("auto");
		sideMenu.setSpacing(false);
		drawer.getElement().addEventListener("click", ev -> sideMenu.getStyle().set("left", "0px"));
		Icon avatar = VaadinIcon.USER.create();
		avatar.setSize("4em");
		sideMenu.add(avatar, new RouterLink("User profile", UserPage.class));
		sideMenu.setAlignItems(Alignment.CENTER);

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(sideMenu, header, workspace, footer);
	}

	private Component createCard() {
		Span card1Label = new Span("News");
		FlexLayout card = new FlexLayout(card1Label);
		card.addClassName("card");
		card.setAlignItems(Alignment.CENTER);
		card.setJustifyContentMode(JustifyContentMode.CENTER);
		card.setWidth("100%");
		card.setHeight("200px");
		return card;
	}

}