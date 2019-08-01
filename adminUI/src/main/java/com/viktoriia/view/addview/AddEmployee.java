package com.viktoriia.view.addview;

import java.io.IOException;    
import java.time.LocalDate; 
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.UI;
import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.enums.Department;
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
import com.viktoriia.view.searchview.SearchEmployee;

@SuppressWarnings("serial")
@Route(value = "addemployee", registerAtStartup = false)
@StyleSheet("frontend://styles/styles.css")
public class AddEmployee extends VerticalLayout {
			
	public AddEmployee() {
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
		Tab actionButton2 = new Tab(VaadinIcon.SEARCH.create(), new RouterLink("Search Employee", SearchEmployee.class));
		Tab actionButton1 = new Tab(VaadinIcon.USERS.create(), new RouterLink("Employees", EmployeesView.class));
		actionButton1.setClassName("tab");
		actionButton2.setClassName("tab");
		Tabs buttonBar = new Tabs(actionButton1, actionButton2);
		HorizontalLayout footer = new HorizontalLayout(buttonBar);
		footer.setJustifyContentMode(JustifyContentMode.CENTER);
		footer.setWidth("100%");
		
		if(SignIn.getRole().equals(UserRole.ADMIN) || SignIn.getRole().equals(UserRole.CHIEF)) {
			Tab actionButton3 = new Tab(VaadinIcon.PACKAGE.create(), new RouterLink("Storages", StoragesView.class));
			Tab actionButton4 = new Tab(VaadinIcon.TOOLS.create(), new RouterLink("Equipment", EquipmentView.class));
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
		FormLayout employeeForm = new FormLayout();
		Binder<Person> binder = new Binder<>();
		Binder<DepartmentEntity> depBinder = new Binder<>();
		
		DepartmentEntity department = new DepartmentEntity();
		Person person = new Person();
		
		TextField fullName = new TextField();
		TextField phoneNumber = new TextField();
		TextField email = new TextField();
		DatePicker dateOfBirth = new DatePicker();
		ComboBox<Department> comboBox = new ComboBox<Department>("");
		comboBox.setItems(Department.values());
		
		SerializablePredicate<String> phoneOrEmailPredicate = value -> !phoneNumber
		        .getValue().trim().isEmpty()
		        && !email.getValue().trim().isEmpty();

		// E-mail and phone have specific validators
		Binding<Person, String> emailBinding = binder.forField(email)
		        .withValidator(phoneOrEmailPredicate,
		                "Both phone and email cannot be empty")
		        .withValidator(new EmailValidator("Incorrect email address"))
		        .bind(Person::getEmail, Person::setEmail);

		Binding<Person, String> phoneBinding = binder.forField(phoneNumber)
		        .withValidator(phoneOrEmailPredicate,
		                "Both phone and email cannot be empty")
		        .bind(Person::getPhoneNumber, Person::setPhoneNumber);
		
		// Trigger cross-field validation when the other field is changed
		email.addValueChangeListener(event -> phoneBinding.validate());
		phoneNumber.addValueChangeListener(event -> emailBinding.validate());
		
		// Full Name name is required field
		fullName.setRequiredIndicatorVisible(true);
		comboBox.setRequiredIndicatorVisible(true);

		binder.forField(fullName)
		        .withValidator(new StringLengthValidator(
		                "Please add the name", 1, null))
		        .bind(Person::getFullName, Person::setFullName);
		
		depBinder.bind(comboBox, DepartmentEntity::getDepartment, DepartmentEntity::setDepartment);
		
		Binder.BindingBuilder<Person, LocalDate> bindingBuilder = binder.forField(dateOfBirth)
				  .withValidator(dateBirth -> !dateBirth.isBefore(dateOfBirth.getValue()),
				  "Birthdate cannot be empty")
				  .withValidator(dateBirth -> !dateBirth.isAfter(LocalDate.now()),
						  "Birthdate is incorrect");
				Binding<Person, LocalDate> dateBinder = bindingBuilder.bind(Person::getDateOfBirth, Person::setDateOfBirth);
		dateOfBirth.addValueChangeListener(event -> dateBinder.validate());	
				
		Label infoLabel = new Label();
		infoLabel.addClassName("layout");
		NativeButton save = new NativeButton("Save");

		employeeForm.addFormItem(fullName, "Full Name");
		employeeForm.addFormItem(dateOfBirth, "Birthdate");
		employeeForm.addFormItem(email, "E-mail");
		employeeForm.addFormItem(phoneNumber, "Phone");
		employeeForm.addFormItem(comboBox, "Department");
		employeeForm.addClassName("layout");
		
		board.addRow(employeeForm);
		board.addRow(save);
		board.addRow(infoLabel);
			
		save.addClickListener(event -> {
			
			if(binder.writeBeanIfValid(person) && depBinder.writeBeanIfValid(department)) {
				infoLabel.setText(String.format("Saved bean values:  "
						+ "FullName:  " + "%s "
						+ " Email:  " + "%s "
						+ "Phone Number: " + "%s "
						+ "Birthdate: "	+ "%s "
						+ "Department: " + "%s ",
						person.getFullName(), person.getEmail(), person.getPhoneNumber(), person.getDateOfBirth(), department.getDepartment()));
				
				Employee employee = new Employee();
				employee.setPerson(person);
				employee.setDepartment(department);
				
				QueueMessage message= new QueueMessage();
				message.setClassEntity(Employee.class);
				message.setEntity(employee);
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
				BinderValidationStatus<Person> validate = binder.validate();
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

