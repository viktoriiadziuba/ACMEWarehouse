CREATE TABLE "user" (
	"id" serial NOT NULL,
	"user_name" varchar(255) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	"user_role_id" integer NOT NULL,
	"person_id" integer NOT NULL,
	"employee_id" integer NOT NULL,
	CONSTRAINT "user_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "department" (
	"id" serial NOT NULL,
	"department" varchar(255) NOT NULL,
	CONSTRAINT "department_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"department_id" integer NOT NULL,
	"person_id" integer NOT NULL,
	CONSTRAINT "employee_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "person" (
	"id" serial NOT NULL,
	"full_name" varchar(255) NOT NULL,
	"phone_number" varchar(255) NOT NULL UNIQUE,
	"date_of_birth" DATE NOT NULL,
	"email" varchar(255) NOT NULL UNIQUE,
	CONSTRAINT "person_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_role" (
	"id" serial NOT NULL,
	"type" VARCHAR(255) NOT NULL,
	CONSTRAINT "user_role_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_state" (
	"id" integer NOT NULL,
	"state" varchar(255) NOT NULL,
	CONSTRAINT "order_state_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order" (
	"id" serial NOT NULL,
	"description" TEXT,
	"order_state_id" integer NOT NULL,
	"goods_id" integer NOT NULL,
	CONSTRAINT "order_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "storage" (
	"id" serial NOT NULL,
	"address" VARCHAR(255) NOT NULL,
	"goods_id" integer NOT NULL,
	CONSTRAINT "storage_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "goods" (
	"id" serial NOT NULL,
	"goods_type_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	CONSTRAINT "goods_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "goods_type" (
	"id" serial NOT NULL,
	"type" VARCHAR(255) NOT NULL,
	"description" TEXT,
	CONSTRAINT "goods_type_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "equipment_type" (
	"id" serial NOT NULL,
	"type" VARCHAR(255) NOT NULL,
	"description" TEXT,
	CONSTRAINT "equipment_type_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "equipment" (
	"id" serial NOT NULL,
	"equipment_type_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	CONSTRAINT "equipment_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "shipment_state" (
	"id" serial NOT NULL,
	"state" varchar(255) NOT NULL,
	CONSTRAINT "shipment_state_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "shipment" (
	"id" serial NOT NULL,
	"date_of_shipment" DATE NOT NULL,
	"description" TEXT,
	"shipment_state_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	"goods_id" integer NOT NULL,
	CONSTRAINT "shipment_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("user_role_id") REFERENCES "user_role"("id");
ALTER TABLE "user" ADD CONSTRAINT "user_fk1" FOREIGN KEY ("person_id") REFERENCES "person"("id");
ALTER TABLE "user" ADD CONSTRAINT "user_fk2" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");


ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("department_id") REFERENCES "department"("id");
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("person_id") REFERENCES "person"("id");




ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("order_state_id") REFERENCES "order_state"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("goods_id") REFERENCES "goods"("id");

ALTER TABLE "storage" ADD CONSTRAINT "storage_fk0" FOREIGN KEY ("goods_id") REFERENCES "goods"("id");

ALTER TABLE "goods" ADD CONSTRAINT "goods_fk0" FOREIGN KEY ("goods_type_id") REFERENCES "goods_type"("id");



ALTER TABLE "equipment" ADD CONSTRAINT "equipment_fk0" FOREIGN KEY ("equipment_type_id") REFERENCES "equipment_type"("id");


ALTER TABLE "shipment" ADD CONSTRAINT "shipment_fk0" FOREIGN KEY ("shipment_state_id") REFERENCES "shipment_state"("id");
ALTER TABLE "shipment" ADD CONSTRAINT "shipment_fk1" FOREIGN KEY ("goods_id") REFERENCES "goods"("id");

