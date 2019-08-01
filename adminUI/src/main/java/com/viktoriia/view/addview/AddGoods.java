package com.viktoriia.view.addview;

import java.io.IOException;    
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.GoodsTypeEntity;
import com.viktoriia.entity.Order;
import com.viktoriia.entity.OrderStateEntity;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.ShipmentStateEntity;
import com.viktoriia.entity.Storage;
import com.viktoriia.entity.enums.GoodsType;
import com.viktoriia.entity.enums.OrderState;
import com.viktoriia.entity.enums.ShipmentState;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.rabbitmq.CRUDOperation;
import com.viktoriia.rabbitmq.QueueConsumer;
import com.viktoriia.rabbitmq.QueueMessage;
import com.viktoriia.rabbitmq.QueueProducer;
import com.viktoriia.view.EmployeesView;
import com.viktoriia.view.EquipmentView;
import com.viktoriia.view.GoodsView;
import com.viktoriia.view.OrdersView;
import com.viktoriia.view.ShipmentsView;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.StoragesView;
import com.viktoriia.view.searchview.SearchGoods;

@SuppressWarnings("serial")
@Route(value = "addgoods", registerAtStartup = false)
@StyleSheet("frontend://styles/styles.css")
public class AddGoods extends VerticalLayout {
		
	public AddGoods() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("Add new employee");
		
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
		VerticalLayout workspace = new VerticalLayout(createCard());
		workspace.addClassName("workspace");
		workspace.setSizeFull();
		
		// FOOTER
		Tab actionButton1 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Goods", SearchGoods.class));
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
		
		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace, footer);
	}

	private Component createCard() {
		Board board = new Board();
		
		FormLayout goodsForm = new FormLayout();
		Binder<GoodsEntity> binder = new Binder<>();
		GoodsEntity goods = new GoodsEntity();
				
		TextArea description = new TextArea();
		TextField quantity = new TextField();
		ComboBox<GoodsType> comboBox = new ComboBox<GoodsType>("");
		comboBox.setItems(GoodsType.values());
		
		binder.forField(quantity)
		.withValidator(new StringLengthValidator(
	                "Quantity should not be empty", 1, null))
		.withConverter(Integer::valueOf,
						String::valueOf)
		.bind(GoodsEntity::getQuantity, GoodsEntity::setQuantity);
		
		binder.forField(description).bind(GoodsEntity::getDescription, GoodsEntity::setDescription);
		
		GoodsTypeEntity goodsType = new GoodsTypeEntity();
		Binder<GoodsTypeEntity> goodsTypeBinder = new Binder<>();
		
		goodsTypeBinder.forField(comboBox).bind(GoodsTypeEntity::getType, GoodsTypeEntity::setType);
		
		Label infoLabel = new Label();
		infoLabel.addClassName("layout");
		NativeButton save = new NativeButton("Save");
		save.addClassName("layout");

		goodsForm.addFormItem(quantity, "Goods Quantity");
		goodsForm.addFormItem(description, "Goods Description");
		goodsForm.addFormItem(comboBox, "Goods Type");
		goodsForm.addClassName("layout");
		
		FormLayout orderForm = new FormLayout();
		Binder<Order> orderBinder = new Binder<>();
		Order order = new Order();
		
		TextArea orderDescription = new TextArea();
		ComboBox<OrderState> orderStateBox = new ComboBox<OrderState>("");
		orderStateBox.setItems(OrderState.values());
		
		orderBinder.forField(orderDescription).bind(Order::getDescription, Order::setDescription);
		
		OrderStateEntity orderState = new OrderStateEntity();
		Binder<OrderStateEntity> orderStateBinder = new Binder<>();
		
		orderStateBinder.forField(orderStateBox).bind(OrderStateEntity::getState, OrderStateEntity::setState);
		
		orderForm.setSizeFull();
		orderForm.addFormItem(orderDescription, "Order Description");
		orderForm.addFormItem(orderStateBox, "OrderState");
		orderForm.addClassName("layout");
		
		FormLayout storageForm = new FormLayout();
		Binder<Storage> storageBinder = new Binder<>();
		Storage storage = new Storage();
		
		TextField address = new TextField();
		
		storageBinder.forField(address)
		.withValidator(new StringLengthValidator(
	                "Address should not be empty", 1, null))
		.bind(Storage::getAddress, Storage::setAddress);
		
		storageForm.addFormItem(address, "Storage Address");
		storageForm.addClassName("layout");
		
		FormLayout shipmentForm = new FormLayout();
		Binder<Shipment> shipmentBinder = new Binder<>();
		Shipment shipment = new Shipment();
		
		TextField shipmentQuantity = new TextField();
		DatePicker dateOfShipment = new DatePicker();
		TextArea shipmentDescription = new TextArea();
		ComboBox<ShipmentState> shipmentStateBox = new ComboBox<ShipmentState>("");
		shipmentStateBox.setItems(ShipmentState.values());
		
		shipmentBinder.forField(shipmentQuantity)
		.withValidator(new StringLengthValidator(
	                "Quantity should not be empty", 1, null))
		.withConverter(Integer::valueOf,
						String::valueOf)
		.bind(Shipment::getQuantity, Shipment::setQuantity);
		
		Binder.BindingBuilder<Shipment, LocalDate> bindingBuilder = shipmentBinder.forField(dateOfShipment)
				  .withValidator(dateShipment -> !dateShipment.isBefore(dateOfShipment.getValue()),
				  "Date of shipment should not be empty");
				Binding<Shipment, LocalDate> dateBinder = bindingBuilder.bind(Shipment::getDateOfShipment, Shipment::setDateOfShipment);
		dateOfShipment.addValueChangeListener(event -> dateBinder.validate());	
		
		shipmentBinder.forField(shipmentDescription).bind(Shipment::getDescription, Shipment::setDescription);
		
		ShipmentStateEntity shipmentState = new ShipmentStateEntity();
		Binder<ShipmentStateEntity> shipmentStateBinder = new Binder<>();
		
		shipmentStateBinder.forField(shipmentStateBox).bind(ShipmentStateEntity::getState, ShipmentStateEntity::setState);
		
		shipmentForm.addFormItem(shipmentQuantity, "Shipment Quantity");
		shipmentForm.addFormItem(dateOfShipment, "Date Of Shipment");
		shipmentForm.addFormItem(shipmentDescription, "Shipment Description");
		shipmentForm.addFormItem(shipmentStateBox, "Shipment State");
		shipmentForm.addClassName("layout");
		
		board.addRow(goodsForm).getStyle().set("border", "1px solid #9E9E9E");
		board.addRow(orderForm).getStyle().set("border", "1px solid #9E9E9E");
		board.addRow(storageForm).getStyle().set("border", "1px solid #9E9E9E");
		board.addRow(shipmentForm).getStyle().set("border", "1px solid #9E9E9E");;
		board.addRow(save).getStyle().set("border", "1px solid #9E9E9E");;
		board.addRow(infoLabel);
		
		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(goods) && 
		    	goodsTypeBinder.writeBeanIfValid(goodsType) && 
		    	orderBinder.writeBeanIfValid(order) &&
		    	orderStateBinder.writeBeanIfValid(orderState) &&
		    	storageBinder.writeBeanIfValid(storage) &&
		    	shipmentBinder.writeBeanIfValid(shipment) &&
		    	shipmentStateBinder.writeBeanIfValid(shipmentState)) {
		    	
		        infoLabel.setText(String.format("Saved bean values: "
		        		+ "[Goods quantity: %s "
		        		+ "Goods description: %s " 
		        		+ "GoodsType: %s ] "
		        		    + "[Order description: %s "
		        			+ "Order state: %s ] "
		        				+ "[Storage address: %s ] "
		        						 + "[Shipment quantity: %s "
		        						 + "Shipment date: %s "
		        						 + "Shipment description: %s "
		        							   + "ShipmentState: %s ] " , goods.getQuantity(), goods.getDescription(), goodsType.getType(),
		        								   order.getDescription(), orderState.getState(),
		        								   storage.getAddress(),
		        								   shipment.getQuantity(), shipment.getDateOfShipment(), shipment.getDescription(), 
		        								   shipmentState.getState()));
		        
		        order.setState(orderState);
		        shipment.setState(shipmentState);
		        
		        goods.setOrder(order);
		        goods.setShipment(shipment);
		        goods.setStorage(storage);
		        goods.setType(goodsType);
		        
		        QueueMessage message= new QueueMessage();
				message.setClassEntity(GoodsEntity.class);
				message.setEntity(goods);
				message.setOperation(CRUDOperation.CREATE);
				
				try {
					QueueConsumer consumer = new QueueConsumer("queue");
					Thread thread = new Thread(consumer);
					thread.run();
					
					QueueProducer producer = new QueueProducer("queue");
					producer.sendMessage(message);
					
				} catch (IOException | TimeoutException e) {
					e.printStackTrace();
				}
		        
		    } else {
		        BinderValidationStatus<GoodsEntity> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        BinderValidationStatus<Storage> validateStorage = storageBinder.validate();
		        String errorText1 = validateStorage.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        BinderValidationStatus<Shipment> validateShipment = shipmentBinder.validate();
		        String errorText2 = validateShipment.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText + " " + errorText1 + " " + errorText2);
		    }
		});
		
		
		board.addClassName("card");
		board.setSizeFull();
		board.setWidth("100%");
		board.setHeight("800px");
		return board;

	}


}
