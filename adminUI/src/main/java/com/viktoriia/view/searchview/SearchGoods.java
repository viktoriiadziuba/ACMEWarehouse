package com.viktoriia.view.searchview;

import java.util.ArrayList;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
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
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.service.impl.ShipmentService;
import com.viktoriia.service.impl.StorageService;
import com.viktoriia.view.EmployeesView;
import com.viktoriia.view.EquipmentView;
import com.viktoriia.view.GoodsView;
import com.viktoriia.view.OrdersView;
import com.viktoriia.view.ShipmentsView;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.StoragesView;
import com.viktoriia.view.addview.AddGoods;

@SuppressWarnings("serial")
@Route(value = "searchgoods", registerAtStartup = false)
public class SearchGoods extends VerticalLayout {

	private GoodsService service = new GoodsService();
	private ShipmentService shipmentService = new ShipmentService();
	private StorageService storageService = new StorageService();
	private OrderService orderService = new OrderService();
	private Grid<GoodsEntity> grid = new Grid<>(GoodsEntity.class);

	public SearchGoods() {
		// HEADER
		Icon drawer = VaadinIcon.SEARCH.create();
		Span title = new Span("Goods search");

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
		
		NumberField filterByShipment = new NumberField("Search by Shipment");
		filterByShipment.setClearButtonVisible(true);
		filterByShipment.setValueChangeMode(ValueChangeMode.EAGER);

		NumberField filterByStorage = new NumberField("Search by Storage");
		filterByStorage.setClearButtonVisible(true);
		filterByStorage.setValueChangeMode(ValueChangeMode.EAGER);

		NumberField filterByOrder = new NumberField("Search by Order");
		filterByOrder.setClearButtonVisible(true);
		filterByOrder.setValueChangeMode(ValueChangeMode.EAGER);

		NativeButton searchById = new NativeButton("Search");
		NativeButton shipmentSearch = new NativeButton("Search");
		NativeButton storageSearch = new NativeButton("Search");
		NativeButton orderSearch = new NativeButton("Search");

		searchById.addClickListener(e -> getGoodsById(filterById.getValue().intValue()));
		shipmentSearch.addClickListener(e -> getByShipment(filterByShipment.getValue().intValue()));
		storageSearch.addClickListener(e -> getByStorage(filterByStorage.getValue().intValue()));
		orderSearch.addClickListener(e -> getByOrder(filterByOrder.getValue().intValue()));

		grid.setColumns("id", "quantity", "description", "type", "shipment", "storage", "order");
		grid.getColumnByKey("id").setFlexGrow(0);
		grid.getColumnByKey("quantity").setFlexGrow(0);
		grid.getColumnByKey("type").setWidth("auto").setFlexGrow(3);
		grid.getColumnByKey("description").setWidth("auto").setFlexGrow(1);
		grid.getColumnByKey("shipment").setWidth("auto").setFlexGrow(8);
		grid.getColumnByKey("storage").setWidth("auto").setFlexGrow(1);
		grid.getColumnByKey("order").setWidth("auto").setFlexGrow(0);

		board.addRow(filterById, searchById).setWidth("10%");
		board.addRow(filterByShipment, shipmentSearch).setWidth("10%");
		board.addRow(filterByStorage, storageSearch).setWidth("10%");
		board.addRow(filterByOrder, orderSearch).setWidth("10%");
		board.addRow(grid);
		board.setSizeFull();
		
		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.SUITCASE.create(), new RouterLink("Create Goods", AddGoods.class));
		Tab actionButton2 = new Tab(VaadinIcon.SUITCASE.create(), new RouterLink("Goods", GoodsView.class));
		actionButton1.setClassName("tab");
		actionButton2.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1, actionButton2);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");
								
		if(SignIn.getRole().equals(UserRole.ADMIN) || SignIn.getRole().equals(UserRole.CHIEF)) {
			Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Storages", StoragesView.class));
			Tab actionButton4 = new Tab(VaadinIcon.USERS.create(), new RouterLink("Employees", EmployeesView.class));
			Tab actionButton5 = new Tab(VaadinIcon.CLIPBOARD_TEXT.create(), new RouterLink("Orders", OrdersView.class));
			Tab actionButton6 = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Equipment", EquipmentView.class));
			Tab actionButton7 = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Shipments", ShipmentsView.class));
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

	public void getByShipment(int shipmentId) {
		grid.setItems(new ArrayList());
		grid.setItems(shipmentService.getWithGoods(shipmentId));
	}

	public void getByStorage(int storageId) {
		grid.setItems(new ArrayList());
		grid.setItems(storageService.getWithGoods(storageId));
	}

	public void getByOrder(int orderId) {
		grid.setItems(new ArrayList());
		grid.setItems(orderService.getWithGoods(orderId));
	}
	
	public void getGoodsById(int id) {
		grid.setItems(new ArrayList());
		grid.setItems(service.getById(id));
	}
}
