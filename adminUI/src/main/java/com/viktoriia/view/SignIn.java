package com.viktoriia.view;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.User;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.rabbitmq.QueueConsumer;
import com.viktoriia.service.impl.UserService;
import com.viktoriia.view.addview.AddEmployee;
import com.viktoriia.view.addview.AddEquipment;
import com.viktoriia.view.addview.AddGoods;
import com.viktoriia.view.searchview.SearchEmployee;
import com.viktoriia.view.searchview.SearchEquipment;
import com.viktoriia.view.searchview.SearchGoods;
import com.viktoriia.view.searchview.SearchOrder;
import com.viktoriia.view.searchview.SearchShipment;
import com.viktoriia.view.searchview.SearchStorage;

@SuppressWarnings("serial")
@Route("")
@StyleSheet("styles/styles.css")
public class SignIn extends VerticalLayout {

	private UserService service = new UserService();
	private User user = new User();
	private RouteConfiguration configuration = RouteConfiguration.forSessionScope();
	private static UserRole role;
	
	public static UserRole getRole() {
		return role;
	}

	public SignIn() {
		try {
			QueueConsumer consumer = new QueueConsumer("queue");
			Thread thread = new Thread(consumer);
			thread.run();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		
		// HEADER
		Span title = new Span("Sign in");
		Tab createUser = new Tab(VaadinIcon.RECORDS.create(), new RouterLink("Sign up", SignUp.class));
		HorizontalLayout header = new HorizontalLayout(title, createUser);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(signIn());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace);
	}

	private Component signIn() {
		TextField userName = new TextField();
		PasswordField password = new PasswordField();
		Span username = new Span("Username");
		Span pass = new Span("Password");
		Label infoLabel = new Label();
		infoLabel.addClassName("layout");
		
		NativeButton signIn = new NativeButton("Sign in");
		
		Board signInForm = new Board();
		signInForm.addRow(username , userName);
		signInForm.addRow(pass , password);
		signInForm.addRow(signIn);
		signInForm.add(infoLabel);
		
		signIn.addClickListener(event -> {
			user = service.signin(userName.getValue(), password.getValue());
			auth();
		});
		
		signInForm.addClassName("layout");
		signInForm.setWidth("auto");
		signInForm.setHeight("200px");
		return signInForm;
	}
	
	public UserRole auth() {
		if(!user.equals(null)) {
			role = user.getRole().getRole();
			
			if(role.equals(UserRole.ADMIN) || role.equals(UserRole.CHIEF)) {
				configuration.setRoute("employees", EmployeesView.class);
				configuration.setRoute("equipment", EquipmentView.class);
				configuration.setRoute("goods", GoodsView.class);
				configuration.setRoute("orders", OrdersView.class);
				configuration.setRoute("shipments", ShipmentsView.class);
				configuration.setRoute("storages", StoragesView.class);
				configuration.setRoute("addemployee", AddEmployee.class);
				configuration.setRoute("addequipment", AddEquipment.class);
				configuration.setRoute("addgoods", AddGoods.class);
				configuration.setRoute("searchemployee", SearchEmployee.class);
				configuration.setRoute("searchgoods", SearchGoods.class);
				configuration.setRoute("searchequipment", SearchEquipment.class);
				configuration.setRoute("searchshipment", SearchShipment.class);
				configuration.setRoute("searchstorage", SearchStorage.class);
				configuration.setRoute("searchorder", SearchOrder.class);
				
				getUI().ifPresent(ui -> ui.navigate("employees"));
				
				return role;
			
			} else if(role.equals(UserRole.EQUIPMENT_MANADER)) {
				configuration.setRoute("equipment", EquipmentView.class);
				configuration.setRoute("addequipment", AddEquipment.class);
				configuration.setRoute("searchequipment", SearchEquipment.class);
				
				getUI().ifPresent(ui -> ui.navigate("equipment"));
				
				return role;
				
			} else if(role.equals(UserRole.HR_MANAGER)) {
				configuration.setRoute("employees", EmployeesView.class);
				configuration.setRoute("addemployee", AddEmployee.class);
				configuration.setRoute("searchemployee", SearchEmployee.class);
				
				getUI().ifPresent(ui -> ui.navigate("employees"));
				
				return role;
				
			} else if(role.equals(UserRole.SHIPMENT_MANAGER)) {
				configuration.setRoute("shipments", ShipmentsView.class);
				configuration.setRoute("searchshipment", SearchShipment.class);
				
				getUI().ifPresent(ui -> ui.navigate("shipments"));
				
				return role;
				
			} else if(role.equals(UserRole.STORAGE_SUPERVIZOR)) {
				configuration.setRoute("goods", GoodsView.class);
				configuration.setRoute("orders", OrdersView.class);
				configuration.setRoute("storages", StoragesView.class);
				configuration.setRoute("addgoods", AddGoods.class);
				configuration.setRoute("searchgoods", SearchGoods.class);
				configuration.setRoute("searchstorage", SearchStorage.class);
				configuration.setRoute("searchorder", SearchOrder.class);
				
				getUI().ifPresent(ui -> ui.navigate("goods"));
				
				return role;
			}
		}
		return null;
	}

}