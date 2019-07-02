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
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.service.impl.EmployeeServiceImpl;
import com.viktoriia.view.Homepage;
import com.viktoriia.view.SignIn;
import com.viktoriia.view.UserPage;

@SuppressWarnings("serial")
@Route("employees/add")
@StyleSheet("frontend://styles/styles.css")
public class AddEmployee extends VerticalLayout {
	
	EmployeeServiceImpl service;
	
	public AddEmployee() {
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
		FormLayout employeeForm = new FormLayout();
		Binder<Employee> binder = new Binder<>();
		Employee employee = new Employee();
				
		
		TextField fullName = new TextField();
		fullName.setValueChangeMode(ValueChangeMode.EAGER);
		TextField phoneNumber = new TextField();
		phoneNumber.setValueChangeMode(ValueChangeMode.EAGER);
		TextField email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		DatePicker dateOfBirth = new DatePicker();
		ComboBox<Department> comboBox = new ComboBox<Department>("");
		comboBox.setItems(Department.values());
		Label infoLabel = new Label();
		NativeButton save = new NativeButton("Save");

		employeeForm.addFormItem(fullName, "Full Name");
		employeeForm.addFormItem(dateOfBirth, "Birthdate");
		employeeForm.addFormItem(email, "E-mail");
		employeeForm.addFormItem(phoneNumber, "Phone");
		employeeForm.addFormItem(comboBox, "Department");
		
		employeeForm.add(save, infoLabel);
		
		SerializablePredicate<String> phoneOrEmailPredicate = value -> !phoneNumber
		        .getValue().trim().isEmpty()
		        || !email.getValue().trim().isEmpty();

		// E-mail and phone have specific validators
//		Binding<Employee, String> emailBinding = binder.forField(email)
//		        .withValidator(phoneOrEmailPredicate,
//		                "Both phone and email cannot be empty")
//		        .withValidator(new EmailValidator("Incorrect email address"))
//		        .bind(Employee::getEmail, Employee::setEmail);
//
//		Binding<Employee, String> phoneBinding = binder.forField(phoneNumber)
//		        .withValidator(phoneOrEmailPredicate,
//		                "Both phone and email cannot be empty")
//		        .bind(Employee::getPhoneNumber, Employee::setPhoneNumber);
		
		// Trigger cross-field validation when the other field is changed
//		email.addValueChangeListener(event -> phoneBinding.validate());
//		phoneNumber.addValueChangeListener(event -> emailBinding.validate());
//		
//		// First name and last name are required fields
//		fullName.setRequiredIndicatorVisible(true);
//
//		binder.forField(fullName)
//		        .withValidator(new StringLengthValidator(
//		                "Please add the name", 1, null))
//		        .bind(Employee::getFullName, Employee::setFullName);
//		
//		binder.bind(comboBox, Employee::getDepartment, Employee::setDepartment);
		
//		Binder.BindingBuilder<Employee, LocalDate> bindingBuilder = binder.forField(dateOfBirth)
//				  .withValidator(dateBirth -> !dateBirth.isBefore(dateOfBirth.getValue()),
//				  "Cannot return before departing");
//				Binder.Binding<Employee, LocalDate> dateBinder = bindingBuilder.bind(Employee::getDateOfBirth, Employee::setDateOfBirth);
//		dateOfBirth.addValueChangeListener(event -> dateBinder.validate());		
				
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(employee)) {
		        infoLabel.setText("Saved bean values: " + employee);
		        service.add(employee);
		    } else {
		        BinderValidationStatus<Employee> validate = binder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText);
		    }
		});
		
		
		employeeForm.addClassName("card");
		employeeForm.setSizeUndefined();
		employeeForm.setWidth("100%");
		employeeForm.setHeight("600px");
		return employeeForm;

	}

}

