--CREATE USER TRMS
--IDENTIFIED BY Re1mbursment
--DEFAULT TABLESPACE users
--TEMPORARY TABLESPACE temp
--QUOTA 10M ON users;
--
--GRANT connect to TRMS;
--GRANT resource to TRMS;
--GRANT create session to TRMS;
--GRANT create table to TRMS;
--GRANT create view to TRMS;
--
--conn TRMS/Re1mbursment

create table DEPARTMENT(
    DPT_ID integer,
    DPT_NAME varchar(25) not null,
    MGR_ID integer,
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DPT_ID)
);
    
    
create sequence SQ_DPT_PK
    start with 1
    minvalue 1
    maxvalue 20;
    

create table EMPLOYEETYPE(
    EMPTYPE_ID integer,
    EMPTYPE_NAME varchar2(20) not null,
    CONSTRAINT PK_EMPLOYEETYPE PRIMARY KEY(EMPTYPE_ID)
);

create sequence SQ_EMPTYPE_PK
    start with 1
    minvalue 1
    maxvalue 20;

    
create table DEPARTMENT(
        DPT_ID integer,
        DPT_NAME varchar(25) not null,
        MGR_ID integer,
        CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DPT_ID)
);
    
    
create sequence SQ_DPT_PK
    start with 1
    minvalue 1
    maxvalue 20;


create table EMPLOYEE(
    EMP_ID integer,
    EMP_FNAME varchar2(20) not null,
    EMP_LNAME varchar2(20) not null,
    --EMP_USER varchar(20) unique,
    EMP_PASSWORD varchar2(32) default '',
    EMP_EMAIL varchar2(64) unique not null,
    EMP_DPT integer default 1 not null,
    EMP_SPVR integer,
    EMP_TYPE integer,    
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_ID),
    CONSTRAINT FK_EMPLOYEE_SPVR FOREIGN KEY(EMP_SPVR) REFERENCES EMPLOYEE(EMP_ID),
    CONSTRAINT FK_EMPLOYEE_DPT FOREIGN KEY(EMP_DPT) REFERENCES DEPARTMENT(DPT_ID),
    CONSTRAINT FK_EMPLOYEE_TYPE FOREIGN KEY(EMP_TYPE) REFERENCES EMPLOYEETPE(EMPTYPE_ID)
);

create sequence SQ_EMP_PK
    start with 1
    minvalue 1
    maxvalue 10000;







create table EVENTYPE(
    EVNTYPE_ID integer,
    EVNTYPE_NAME varchar2(50),
    EVNTYPE_REIMBURSED integer,
    CONSTRAINT PK_EVENTYPE PRIMARY KEY(EVNTYPE_ID)

)

create sequence SQ_EVNTYPE_PK
    start with 1
    minvalue 1
    maxvalue 20;



create table GRADEFORMAT(
    GRDFORM_ID integer,
    GRDFORM_NAME integer,
    GRDFORM_PASSING integer,
    CONSTRAINT PK_GRADEFORMAT PRIMARY KEY(GRDFORM_ID)
)

create sequence SQ_GRDFORM_PK
    start with 1
    minvalue 1
    maxvalue 20;



create table EVENT(
    EVNT_ID integer,
    EVNT_DESC varchar(200),
    EVNT_LOC varchar2(200),
    EVNT_TIME date,
    CONSTRAINT PK_EVENT PRIMARY KEY(EVNT_ID)
)

create sequence SQ_EVNT_PK
    start with 1
    minvalue 1
    maxvalue 20;

create trigger TR_EVENT_BI
before insert on EVENT
    for each row
        begin
            select SQ_EVNT_PK.NEXTVAL
            into :New.EVNT_ID from DUAL;
end;
/



create table RFILE(
    FILE_ID integer,
    REIM_ID integer,
    FILE_NAME varchar2(50) not null,
    FILE_PATH varchar2(50),
    CONSTRAINT PK_RFILE PRIMARY KEY(FILE_ID),
    CONSTRAINT FK_RFILE_REIM FOREIGN KEY(REIM_ID) REFERENCES REIMBURSEMENT(REIM_ID)
);

create sequence SQ_RFILE_PK
    start with 1
    minvalue 1
    maxvalue 10000;

create trigger TR_RFILE_BI
before insert on RFILE
    for each row
        begin
            select SQ_RFILE_PK.NEXTVAL
            into :NEW.FILE_ID from DUAL;
end;
/



create table GRADEUPLOAD(
    GRDUPL_ID integer,
    REIM_ID integer, 
    FILE_ID integer,
    
    GRDUPL_SPVRCONFIRM char(1),
    GRDUPL_BENCOPASS char(1),
    
    CONSTRAINT PK_GRADEUPLOAD PRIMARY KEY(GRDUPL_ID),
    CONSTRAINT FK_GRADEUPLOAD_FILE FOREIGN KEY(FILE_ID) REFERENCES RFILE(FILE_ID)
)

create sequence SQ_GRDUPL_PK
    start with 1
    minvalue 1
    maxvalue 100000;

create table REIMBURSEMENT(
    REIM_ID integer,
    EMP_ID integer not null,
    EVNT_ID integer not null,
    EVNTYPE_ID integer not null,
    
    REIM_DATE DATE,
    EVNT_ID integer,
    
    REIM_REASON varchar2(200),
    REIM_GRADEFORMAT integer,
    REIM_EVNTCOST number (10,2) default 0.00,
    REIM_WORKMISS integer default 0,
    
    
    REIM_AMOUNT number(10,2),
    REIM_PROJREIM number(10,2),
   
    REIM_EXCEEDAMT number(10,2) default 0, 
    REIM_EXCEEDREASON varchar2(200),
    
    REIM_APPROVAL char(1) default 0 not null,
    REIM_DENYREASON char(200),
    
    REIM_SPVREMAIL integer default 0,
    REIM_HEADEMAIL integer default 0,
    
    REIM_COMPLETE char(1) default 0 not null,
    REIM_PASSED char (1) default 0,
    REIM_URGENT char(1) default 0,
    CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY(REIM_ID),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_APRVL CHECK (REIM_APPROVAL >= 0 or  REIM_APPROVAL <= 6),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_CMP CHECK (REIM_COMPLETE = 0 or  REIM_COMPLETE = 1),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_PASSED CHECK (REIM_PASSED = 0 or  REIM_PASSED = 1),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_URG CHECK (REIM_URGENT = 0 or  REIM_URGENT = 1)
);


create sequence SQ_REIM_PK
    start with 1
    minvalue 1
    maxvalue 100000;


ALTER TABLE DEPARTMENT ADD CONSTRAINT FK_DEPARTMENT_MGR FOREIGN KEY(MGR_ID) REFERENCES EMPLOYEE(EMP_ID);

ALTER TABLE REIMBURSEMENT ADD (CONSTRAINT FK_REIMBURSEMENT_DEPARTMENT FOREIGN KEY(DPT_ID) REFERENCES DEPARTMENT(DPT_ID),
CONSTRAINT FK_REIMBURSEMENT_EMPLOYEE FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID),
CONSTRAINT FK_REIMBURSEMENT_EVENT FOREIGN KEY(EVNT_ID) REFERENCES EVENT(EVNT_ID),
CONSTRAINT FK_REIMBURSEMENT_GRADEFORMAT FOREIGN KEY(GRDFORM_ID) REFERENCES GRADEFORMAT(GRDFORM_ID));

commit;
