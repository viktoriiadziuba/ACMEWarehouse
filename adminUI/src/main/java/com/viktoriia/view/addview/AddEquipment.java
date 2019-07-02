package com.viktoriia.view.addview;

import java.util.Optional; 
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.EquipmentEntity;
import com.viktoriia.entity.enums.EquipmentType;
import com.viktoriia.service.impl.EquipmentServiceImpl;
import com.viktoriia.view.Homepage;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.UserPage;

@SuppressWarnings("serial")
@Route("addequipment")
@StyleSheet("frontend://styles/styles.css")
public class AddEquipment extends VerticalLayout {
	
	EquipmentServiceImpl service;
	
	public AddEquipment() {
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
		FormLayout equipmentForm = new FormLayout();
		Binder<EquipmentEntity> binder = new Binder<>();
		EquipmentEntity equipment = new EquipmentEntity();
				
		
		TextField quantity = new TextField();
		quantity.setValueChangeMode(ValueChangeMode.EAGER);
		TextField description = new TextField();
		description.setValueChangeMode(ValueChangeMode.EAGER);
		ComboBox<EquipmentType> comboBox = new ComboBox<EquipmentType>("");
		comboBox.setItems(EquipmentType.values());
		Label infoLabel = new Label();
		NativeButton save = new NativeButton("Save");

		equipmentForm.addFormItem(quantity, "Quantity");
		equipmentForm.addFormItem(description, "Description");
		equipmentForm.addFormItem(comboBox, "EquipmentType");
		
		equipmentForm.add(save, infoLabel);

		quantity.setRequiredIndicatorVisible(true);
		
		binder.forField(quantity)
			.withConverter(Integer::valueOf,
							String::valueOf)
			.bind(EquipmentEntity::getQuantity, EquipmentEntity::setQuantity);
		
//		binder.forField(description)
//			.bind(EquipmentEntity::getDescription, EquipmentEntity::setDescription);
//		
//		binder.bind(comboBox, EquipmentEntity::getType, EquipmentEntity::setType);
//		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(equipment)) {
		        infoLabel.setText("Saved bean values: " + equipment);
		        service.add(equipment);
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
		
		
		equipmentForm.addClassName("card");
		equipmentForm.setSizeUndefined();
		equipmentForm.setWidth("100%");
		equipmentForm.setHeight("600px");
		return equipmentForm;

	}

}
