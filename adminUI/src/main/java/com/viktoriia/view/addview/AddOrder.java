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
import com.viktoriia.entity.Order;
import com.viktoriia.entity.enums.OrderState;
import com.viktoriia.service.impl.OrderServiceImpl;
import com.viktoriia.view.Homepage;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.UserPage;

@SuppressWarnings("serial")
@Route("addorders")
@StyleSheet("frontend://styles/styles.css")
public class AddOrder extends VerticalLayout {

	OrderServiceImpl service;
	
	public AddOrder() {
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
		FormLayout orderForm = new FormLayout();
		Binder<Order> binder = new Binder<>();
		Order order = new Order();
				
		TextField description = new TextField();
		description.setValueChangeMode(ValueChangeMode.EAGER);
		ComboBox<OrderState> comboBox = new ComboBox<OrderState>("");
		comboBox.setItems(OrderState.values());
		Label infoLabel = new Label();
		NativeButton save = new NativeButton("Save");

		orderForm.addFormItem(description, "Description");
		orderForm.addFormItem(comboBox, "State");
		
		orderForm.add(save, infoLabel);
		
		binder.forField(description)
			.bind(Order::getDescription, Order::setDescription);
		
//		binder.bind(comboBox, Order::getState, Order::setState);
		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(order)) {
		        infoLabel.setText("Saved bean values: " + order);
		        service.add(order);
		    } else {
		        BinderValidationStatus<Order> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText);
		    }
		});
		
		
		orderForm.addClassName("card");
		orderForm.setSizeUndefined();
		orderForm.setWidth("100%");
		orderForm.setHeight("600px");
		return orderForm;

	}

}
