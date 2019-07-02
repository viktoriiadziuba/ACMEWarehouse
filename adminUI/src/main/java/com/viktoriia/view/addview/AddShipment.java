package com.viktoriia.view.addview;

import java.util.Optional; 
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.Shipment;
import com.viktoriia.entity.enums.ShipmentState;
import com.viktoriia.service.impl.ShipmentServiceImpl;
import com.viktoriia.view.Homepage;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.UserPage;

@SuppressWarnings("serial")
@Route("addshipments")
@StyleSheet("frontend://styles/styles.css")
public class AddShipment extends VerticalLayout {

	ShipmentServiceImpl service;
	
	public AddShipment() {
		// HEADER
		Icon drawer = VaadinIcon.MENU.create();
		Span title = new Span("Add new employee");
		Tab home = new Tab(VaadinIcon.HOME.create(), new RouterLink("Home", Homepage.class));
		Tab exit = new Tab(VaadinIcon.SIGN_OUT.create(), new RouterLink("Sign out", SignIn.class));
		HorizontalLayout header = new HorizontalLayout(drawer, title, home, exit);
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
		sideMenu.add(avatar, new RouterLink("User profile", UserPage.class));
		sideMenu.setAlignItems(Alignment.CENTER);

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(sideMenu, header, workspace);
	}

	private Component createCard() {
		FormLayout shipmentForm = new FormLayout();
		Binder<Shipment> binder = new Binder<>();
		Shipment shipment = new Shipment();
				
		
		TextField quantity = new TextField();
		quantity.setValueChangeMode(ValueChangeMode.EAGER);
		TextField description = new TextField();
		description.setValueChangeMode(ValueChangeMode.EAGER);
		DatePicker dateOfShipment = new DatePicker();
		ComboBox<ShipmentState> comboBox = new ComboBox<ShipmentState>("");
		comboBox.setItems(ShipmentState.values());
		Label infoLabel = new Label();
		NativeButton save = new NativeButton("Save");

		shipmentForm.addFormItem(quantity, "Quantity");
		shipmentForm.addFormItem(description, "Description");
		shipmentForm.addFormItem(dateOfShipment, "Date Of Shipment");
		shipmentForm.addFormItem(comboBox, "Shipment State");
		
		shipmentForm.add(save, infoLabel);

		quantity.setRequiredIndicatorVisible(true);
		
		binder.forField(quantity)
			.withConverter(Integer::valueOf,
							String::valueOf)
			.bind(Shipment::getQuantity, Shipment::setQuantity);
		
		binder.forField(description)
			.bind(Shipment::getDescription, Shipment::setDescription);
		
//		binder.bind(comboBox, Shipment::getState, Shipment::setState);
		
//		Binder.BindingBuilder<Shipment, LocalDate> bindingBuilder = binder.forField(dateOfShipment)
//				  .withValidator(dateShipment -> !dateShipment.isBefore(dateOfShipment.getValue()),
//				  "Cannot return before departing");
//				Binder.Binding<Shipment, LocalDate> dateBinder = bindingBuilder.bind(Shipment::getDateOfShipment, Shipment::setDateOfShipment);
//				dateOfShipment.addValueChangeListener(event -> dateBinder.validate());		
		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(shipment)) {
		        infoLabel.setText("Saved bean values: " + shipment);
		        service.add(shipment);
		    } else {
		        BinderValidationStatus<Shipment> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText);
		    }
		});
		
		
		shipmentForm.addClassName("card");
		shipmentForm.setSizeUndefined();
		shipmentForm.setWidth("100%");
		shipmentForm.setHeight("600px");
		return shipmentForm;

	}

}
