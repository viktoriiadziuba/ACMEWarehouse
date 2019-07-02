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
import com.viktoriia.entity.GoodsEntity;
import com.viktoriia.entity.enums.GoodsType;
import com.viktoriia.service.impl.GoodsServiceImpl;
import com.viktoriia.view.Homepage;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.UserPage;

@SuppressWarnings("serial")
@Route("addgoods")
@StyleSheet("frontend://styles/styles.css")
public class AddGoods extends VerticalLayout {
	
	GoodsServiceImpl service;
	
	public AddGoods() {
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
		FormLayout goodsForm = new FormLayout();
		Binder<GoodsEntity> binder = new Binder<>();
		GoodsEntity goods = new GoodsEntity();
				
		TextField description = new TextField();
		description.setValueChangeMode(ValueChangeMode.EAGER);
		ComboBox<GoodsType> comboBox = new ComboBox<GoodsType>("");
		comboBox.setItems(GoodsType.values());
		Label infoLabel = new Label();
		NativeButton save = new NativeButton("Save");

		goodsForm.addFormItem(description, "Description");
		goodsForm.addFormItem(comboBox, "Goods Type");
		
		goodsForm.add(save, infoLabel);
		
//		binder.forField(description)
//			.bind(GoodsEntity::getDescription, GoodsEntity::setDescription);
//		
//		binder.bind(comboBox, GoodsEntity::getType, GoodsEntity::setType);
		
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(goods)) {
		        infoLabel.setText("Saved bean values: " + goods);
		        service.add(goods);
		    } else {
		        BinderValidationStatus<GoodsEntity> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText);
		    }
		});
		
		
		goodsForm.addClassName("card");
		goodsForm.setSizeUndefined();
		goodsForm.setWidth("100%");
		goodsForm.setHeight("600px");
		return goodsForm;

	}


}
