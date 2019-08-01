package com.viktoriia.view;

import java.io.IOException;   
import java.util.concurrent.TimeoutException;

import org.vaadin.crudui.crud.impl.GridCrud; 
import org.vaadin.crudui.layout.impl.VerticalSplitCrudLayout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
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
import com.viktoriia.rabbitmq.CRUDOperation;
import com.viktoriia.rabbitmq.QueueMessage;
import com.viktoriia.rabbitmq.QueueProducer;
import com.viktoriia.service.impl.GoodsService;
import com.viktoriia.view.addview.AddGoods;
import com.viktoriia.view.searchview.SearchGoods;

@SuppressWarnings("serial")
@Route(value = "goods", registerAtStartup = false)
@StyleSheet("frontend://styles/styles.css")
public class GoodsView extends VerticalLayout {

	private GoodsService service = new GoodsService();
	
	public GoodsView() {
		
		NumberField deleteById = new NumberField("Delete by id");
		deleteById.setClearButtonVisible(true);
		deleteById.setValueChangeMode(ValueChangeMode.EAGER);
		
		NativeButton deleteButton = new NativeButton("Delete");
		deleteButton.addClickListener(event -> {
			GoodsEntity goods = new GoodsEntity();
			goods.setId(deleteById.getValue().intValue());
			
			QueueMessage message = new QueueMessage();
			message.setClassEntity(GoodsEntity.class);
			message.setEntity(goods);
			message.setOperation(CRUDOperation.DELETE);
			
			try {				
				QueueProducer producer = new QueueProducer("queue");
				producer.sendMessage(message);
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		});
		
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("Goods");
		Tab delete = new Tab(deleteById, deleteButton);
		deleteButton.setHeight("60%");
		Tab searchGoods = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Goods", SearchGoods.class));
		Tab addGoods = new Tab(VaadinIcon.SUITCASE.create(), new RouterLink("Create Goods", AddGoods.class));

		Button exitButton = new Button("Sign out");
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), exitButton);
		
		exitButton.addClickListener(event -> {
			UI ui = getUI().get();			
			ui.getSession().close();
			ui.navigate("");
			
		});
		
		HorizontalLayout header = new HorizontalLayout(drawer, title, delete, searchGoods, addGoods, exit);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");
		
		// WORKSPACE
		GridCrud<GoodsEntity> crud = new GridCrud<>(GoodsEntity.class, new VerticalSplitCrudLayout());
		crud.getGrid().setColumns("id", "quantity", "description", "type", "shipment", "storage", "order");
		crud.getGrid().getColumnByKey("id").setFlexGrow(0);
		crud.getGrid().getColumnByKey("quantity").setFlexGrow(0);
		crud.getGrid().getColumnByKey("type").setWidth("auto").setFlexGrow(3);
		crud.getGrid().getColumnByKey("description").setWidth("auto").setFlexGrow(1);
		crud.getGrid().getColumnByKey("shipment").setWidth("auto").setFlexGrow(8);
		crud.getGrid().getColumnByKey("storage").setWidth("auto").setFlexGrow(1);
		crud.getGrid().getColumnByKey("order").setWidth("auto").setFlexGrow(0);
		crud.getCrudFormFactory().setUseBeanValidation(true);
		crud.setFindAllOperation(() -> service.getAll());
		crud.setDeleteOperationVisible(false);
		crud.setAddOperationVisible(false);
		crud.setUpdateOperationVisible(false);
		crud.setClickRowToUpdate(true);
		
		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Goods", SearchGoods.class));
		Tab actionButton2 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Create Goods", AddGoods.class));
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

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, crud, footer);
	}

}

