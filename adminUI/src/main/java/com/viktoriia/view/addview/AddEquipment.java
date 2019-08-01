package com.viktoriia.view.addview;

import java.io.IOException;   
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.EquipmentTypeEntity;
import com.viktoriia.entity.enums.EquipmentType;
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
import com.viktoriia.view.searchview.SearchEquipment;

@SuppressWarnings("serial")
@Route(value = "addequipment", registerAtStartup = false)
@StyleSheet("frontend://styles/styles.css")
public class AddEquipment extends VerticalLayout {
		
	public AddEquipment() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("Create employee");
		
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
		Tab actionButton2 = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Equipment", EquipmentView.class));
		Tab actionButton1 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Equipment", SearchEquipment.class));
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
			Tab actionButton6 = new Tab(VaadinIcon.TRUCK.create(), new RouterLink("Shipments", ShipmentsView.class));
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
		add(header, workspace, footer);
	}

	private Component createCard() {
		Board board = new Board();
		
		FormLayout equipmentForm = new FormLayout();
		Binder<EquipmentEntity> binder = new Binder<>();
		Binder<EquipmentTypeEntity> typeBinder = new Binder<>();
		EquipmentEntity equipment = new EquipmentEntity();
		EquipmentTypeEntity type = new EquipmentTypeEntity();
				
		TextField quantity = new TextField();
		quantity.setValueChangeMode(ValueChangeMode.EAGER);
		ComboBox<EquipmentType> comboBox = new ComboBox<EquipmentType>("");
		comboBox.setItems(EquipmentType.values());
		
		Label infoLabel = new Label();
		infoLabel.addClassName("layout");
		NativeButton save = new NativeButton("Save");

		equipmentForm.addFormItem(quantity, "Quantity");
		equipmentForm.addFormItem(comboBox, "EquipmentType");
		equipmentForm.addClassName("layout");
		
		board.addRow(equipmentForm);
		board.addRow(save);
		board.addRow(infoLabel);
		
		quantity.setRequiredIndicatorVisible(true);
		
		binder.forField(quantity)
			.withValidator(new StringLengthValidator(
		                "Quantity should not be empty", 1, null))
			.withConverter(Integer::valueOf,
							String::valueOf)
			.bind(EquipmentEntity::getQuantity, EquipmentEntity::setQuantity);
		

		typeBinder.bind(comboBox, EquipmentTypeEntity::getType, EquipmentTypeEntity::setType);
		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(equipment) && typeBinder.writeBeanIfValid(type)) {
		        infoLabel.setText(String.format("Saved bean values: "
		        		+ "Quantity: " + "%s "
		        		+ "Type: " + "%s ", equipment.getQuantity(), type.getType()));
		       
		        equipment.setType(type);
		        
		        QueueMessage message = new QueueMessage();
		        message.setClassEntity(EquipmentEntity.class);
		        message.setEntity(equipment);
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
		        BinderValidationStatus<EquipmentEntity> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText);
		    }
		});
		
		
		board.addClassName("card");
		board.setSizeUndefined();
		board.setWidth("100%");
		board.setHeight("600px");
		return board;

	}

}
