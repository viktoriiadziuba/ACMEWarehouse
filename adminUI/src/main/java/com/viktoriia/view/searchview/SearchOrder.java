package com.viktoriia.view.searchview;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.view.EmployeesView;
import com.viktoriia.view.EquipmentView;
import com.viktoriia.view.GoodsView;
import com.viktoriia.view.OrdersView;
import com.viktoriia.view.ShipmentsView;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.StoragesView;

@SuppressWarnings("serial")
@Route(value = "searchorder", registerAtStartup = false)
public class SearchOrder extends VerticalLayout {

	private OrderService service = new OrderService();
	private Grid<Order> grid = new Grid<>(Order.class);
	
	public SearchOrder() {
		// HEADER
		Icon drawer = VaadinIcon.SEARCH.create();
		Span title = new Span("Order search");
		
		Button exitButton = new Button("Sign out");
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), exitButton);
		
		exitButton.addClickListener(event -> {
			UI ui = getUI().get();			
			ui.getSession().close();
			ui.navigate("");
			
		});
		
		HorizontalLayout header = new HorizontalLayout(drawer, title, exit);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");
		
		// WORKSPACE
		Board board = new Board();
		NumberField filterById = new NumberField("Search by id");
		filterById.setClearButtonVisible(true);
		filterById.setValueChangeMode(ValueChangeMode.EAGER);
		
		NativeButton search = new NativeButton("Search");
		search.addClickListener(e -> getOrderById(filterById.getValue().intValue()));
		
		grid.setColumns("id", "state", "description");
		grid.getColumnByKey("id").setFlexGrow(0);
		grid.getColumnByKey("state").setTextAlign(ColumnTextAlign.CENTER);
		grid.getColumnByKey("description").setTextAlign(ColumnTextAlign.CENTER);
		
		board.addRow(filterById, search).setWidth("10%");
		board.addRow(grid);
		board.setSizeFull();
		
		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.CLIPBOARD_TEXT.create(), new RouterLink("Orders", OrdersView.class));
		actionButton1.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");
		
		if(SignIn.getRole().equals(UserRole.ADMIN) || SignIn.getRole().equals(UserRole.CHIEF)) {
			Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Storages", StoragesView.class));
			Tab actionButton4 = new Tab(VaadinIcon.USERS.create(), new RouterLink("Employees", EmployeesView.class));
			Tab actionButton5 = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Shipments", ShipmentsView.class));
			Tab actionButton6 = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Equipment", EquipmentView.class));
			Tab actionButton7 = new Tab(VaadinIcon.SUITCASE.create(), new RouterLink("Goods", GoodsView.class));
			actionButton3.setClassName("tab");
			actionButton4.setClassName("tab");
			actionButton5.setClassName("tab");
			actionButton6.setClassName("tab");
			actionButton7.setClassName("tab");
			buttonBar.add(actionButton3, actionButton3, actionButton4,
					actionButton5, actionButton6, actionButton7);
		}
		
		add(header, board, footer);
		setSizeFull();
	}
	
	public void getOrderById(int id) {
		grid.setItems(service.getById(id));
	}
}
