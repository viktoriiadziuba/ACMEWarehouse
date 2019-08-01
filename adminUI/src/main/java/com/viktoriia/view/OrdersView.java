package com.viktoriia.view;

import org.vaadin.crudui.crud.impl.GridCrud;  
import org.vaadin.crudui.layout.impl.VerticalSplitCrudLayout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.service.impl.OrderService;
import com.viktoriia.view.searchview.SearchOrder;

@SuppressWarnings("serial")
@Route(value = "orders", registerAtStartup = false)
@StyleSheet("frontend://styles/styles.css")
public class OrdersView extends VerticalLayout {

	private OrderService service = new OrderService();

	public OrdersView() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("Orders");
		
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
		GridCrud<Order> crud = new GridCrud<>(Order.class, new VerticalSplitCrudLayout());
		crud.getGrid().setColumns("id", "state", "description");
		crud.getGrid().getColumnByKey("id").setFlexGrow(0);
		crud.getGrid().getColumnByKey("state").setTextAlign(ColumnTextAlign.CENTER);
		crud.getGrid().getColumnByKey("description").setTextAlign(ColumnTextAlign.CENTER);
		crud.getCrudFormFactory().setUseBeanValidation(true);
		crud.setFindAllOperation(() -> service.getAll());
		crud.setDeleteOperationVisible(false);
		crud.setAddOperationVisible(false);
		crud.setUpdateOperationVisible(false);
		crud.setClickRowToUpdate(true);
		
		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Order", SearchOrder.class));
		actionButton1.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");
						
		if(SignIn.getRole().equals(UserRole.ADMIN) || SignIn.getRole().equals(UserRole.CHIEF)) {
			Tab actionButton3 = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Shipments", ShipmentsView.class));
			Tab actionButton4 = new Tab(VaadinIcon.USERS.create(), new RouterLink("Employees", EmployeesView.class));
			Tab actionButton5 = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Storages", StoragesView.class));
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

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, crud, footer);
	}

}