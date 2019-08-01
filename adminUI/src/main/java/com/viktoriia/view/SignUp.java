package com.viktoriia.view;

import java.io.IOException; 
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;  
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.Binder.Binding;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.Route;
import com.viktoriia.entity.DepartmentEntity;
import com.viktoriia.entity.Employee;
import com.viktoriia.entity.Person;
import com.viktoriia.entity.User;
import com.viktoriia.entity.UserRoleEntity;
import com.viktoriia.entity.enums.Department;
import com.viktoriia.entity.enums.UserRole;
import com.viktoriia.rabbitmq.CRUDOperation;
import com.viktoriia.rabbitmq.QueueMessage;
import com.viktoriia.rabbitmq.QueueProducer;
import com.viktoriia.service.impl.UserService;

@SuppressWarnings("serial")
@Route("signup")
@StyleSheet("styles/styles.css")
public class SignUp extends VerticalLayout {
	
	private UserService service = new UserService();
	
	public SignUp() {
		// HEADER
		Span title = new Span("Welcome On Board");
		HorizontalLayout header = new HorizontalLayout(title);
		header.expand(title);
		header.setPadding(true);
		header.setWidth("100%");

		// WORKSPACE
		VerticalLayout workspace = new VerticalLayout(signUp());
		workspace.addClassName("workspace");
		workspace.setSizeFull();

		// CONTAINER
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		setPadding(false);
		add(header, workspace);

	}

	private Component signUp() {
		Board board = new Board();
		
		FormLayout userLayout = new FormLayout();
		
		Binder<User> userBinder = new Binder<>();
		Binder<UserRoleEntity> roleBinder = new Binder<>();
		Binder<Person> personBinder = new Binder<>();
		Binder<DepartmentEntity> depBinder = new Binder<>();
		
		User user = new User();
		UserRoleEntity role = new UserRoleEntity();
		Person person = new Person();
		DepartmentEntity department = new DepartmentEntity();
				
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		ComboBox<UserRole> roleBox = new ComboBox<UserRole>("");
		roleBox.setItems(UserRole.values());
				
		TextField fullName = new TextField();
		TextField phoneNumber = new TextField();
		TextField email = new TextField();
		DatePicker dateOfBirth = new DatePicker();
		
		ComboBox<Department> departmentBox = new ComboBox<Department>("");
		departmentBox.setItems(Department.values());
		
		SerializablePredicate<String> phoneOrEmailPredicate = value -> !phoneNumber
		        .getValue().trim().isEmpty()
		        && !email.getValue().trim().isEmpty();
		
		SerializablePredicate<String> passwordOrUsernamePredicate = value -> !password
		        .getValue().trim().isEmpty()
		        && !username.getValue().trim().isEmpty();

		// E-mail and phone have specific validators
		Binding<Person, String> emailBinding = personBinder.forField(email)
		        .withValidator(phoneOrEmailPredicate,
		                "Both phone and email can not be empty")
		        .withValidator(new EmailValidator("Incorrect email address"))
		        .bind(Person::getEmail, Person::setEmail);

		Binding<Person, String> phoneBinding = personBinder.forField(phoneNumber)
		        .withValidator(phoneOrEmailPredicate,
		                "Both phone and email can not be empty")
		        .bind(Person::getPhoneNumber, Person::setPhoneNumber);
		
		Binding<User, String> passwordBinding = userBinder.forField(password)
				.withValidator(passwordOrUsernamePredicate, "Both password and username can not be empty")
				.bind(User::getPassword, User::setPassword);
		
		Binding<User, String> usernameBinding = userBinder.forField(username)
				.withValidator(passwordOrUsernamePredicate, "Both password and username can not be empty")
				.bind(User::getUserName, User::setUserName);
		
		// Trigger cross-field validation when the other field is changed
		email.addValueChangeListener(event -> phoneBinding.validate());
		phoneNumber.addValueChangeListener(event -> emailBinding.validate());
		password.addValueChangeListener(event -> passwordBinding.validate());
		username.addValueChangeListener(event -> usernameBinding.validate());
		
		// Full Name name is required field
		fullName.setRequiredIndicatorVisible(true);
		departmentBox.setRequiredIndicatorVisible(true);
		roleBox.setRequiredIndicatorVisible(true);
		
		personBinder.forField(fullName)
		        .withValidator(new StringLengthValidator(
		                "Please add the name", 1, null))
		        .bind(Person::getFullName, Person::setFullName);
		
		depBinder.bind(departmentBox, DepartmentEntity::getDepartment, DepartmentEntity::setDepartment);
		roleBinder.bind(roleBox, UserRoleEntity::getRole, UserRoleEntity::setRole);
		
		Binder.BindingBuilder<Person, LocalDate> bindingBuilder = personBinder.forField(dateOfBirth)
				  .withValidator(dateBirth -> !dateBirth.isBefore(dateOfBirth.getValue()),
				  "Birthdate cannot be empty")
				  .withValidator(dateBirth -> !dateBirth.isAfter(LocalDate.now()),
						  "Birthdate is incorrect");
				Binding<Person, LocalDate> dateBinder = bindingBuilder.bind(Person::getDateOfBirth, Person::setDateOfBirth);
		dateOfBirth.addValueChangeListener(event -> dateBinder.validate());	
		
				
		NativeButton save = new NativeButton("Save");
		save.setWidth("20%");
		Label infoLabel = new Label();
		infoLabel.addClassName("layout");
		
		// Button bar
		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save);
		
		userLayout.addFormItem(username, "Username");
		userLayout.addFormItem(password, "Password");
		userLayout.addFormItem(roleBox, "User Role");
		userLayout.addFormItem(fullName, "Full Name");
		userLayout.addFormItem(phoneNumber, "Phone Number");
		userLayout.addFormItem(email, "Email");
		userLayout.addFormItem(dateOfBirth, "Date Of Birth");
		userLayout.addFormItem(departmentBox, "Department");
		userLayout.addClassName("layout");

		
		save.addClickListener(event -> {
			
			if(personBinder.writeBeanIfValid(person) && depBinder.writeBeanIfValid(department) && userBinder.writeBeanIfValid(user) && roleBinder.writeBeanIfValid(role)) {
				infoLabel.setText(String.format("Saved bean values:  "
						+ "Username: " + "%s "
						+ "FullName:  " + "%s "
						+ " Email:  " + "%s "
						+ "Phone Number: " + "%s "
						+ "Birthdate: "	+ "%s "
						+ "Department: " + "%s "
						+ "User Role: " + "%s ",
						user.getUserName(), person.getFullName(), person.getEmail(), person.getPhoneNumber(), person.getDateOfBirth(), department.getDepartment(), role.getRole()));
				
				Employee employee = new Employee();
				employee.setPerson(person);
				employee.setDepartment(department);
				
				user.setEmployee(employee);
				user.setRole(role);
				
				String emailForCheck = email.getValue();
				String phoneForCheck = phoneNumber.getValue();
				String usernameForCheck = username.getValue();
				
				if(service.existsByEmail(emailForCheck) == false) {
					if(service.existsByPhone(phoneForCheck) == false) {
						if(service.existsByUserName(usernameForCheck) == false) {
				
						QueueMessage message = new QueueMessage();
						message.setClassEntity(User.class);
						message.setEntity(user);
						message.setOperation(CRUDOperation.CREATE);
				
						try {
							QueueProducer producer = new QueueProducer("queue");
							producer.sendMessage(message);
						} catch (IOException | TimeoutException e) {
							e.printStackTrace();
						}
				
						getUI().ifPresent(ui -> ui.navigate(""));
						
						} else {
							infoLabel.setText("Username already exists");
						}
					} else {
						infoLabel.setText("Phone number already exists");
					}
				} else {
					infoLabel.setText("Email already exists");
				}
				
				
			} else {
				BinderValidationStatus<Person> validate = personBinder.validate();
		        String errorText = validate.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        BinderValidationStatus<User> validateU = userBinder.validate();
		        String errorTextU = validateU.getFieldValidationStatuses()
		                .stream().filter(BindingValidationStatus::isError)
		                .map(BindingValidationStatus::getMessage)
		                .map(Optional::get).distinct()
		                .collect(Collectors.joining(", "));
		        infoLabel.setText("There are errors: " + errorText + " " + errorTextU);
			}
		   
		});
		
		
		board.addRow(userLayout);
		board.add(actions);
		board.addRow(infoLabel);

		return board;
	}
}
