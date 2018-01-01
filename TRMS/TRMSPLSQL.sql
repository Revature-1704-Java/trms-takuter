create trigger TR_EMPLOYEETYPE_BI
before insert on EMPLOYEETYPE
    for each row
        begin
            select SQ_EMPTYPE_PK.NEXTVAL
            into :New.EMPTYPE_ID from DUAL;
end;
/


create trigger TR_DEPARTMENT_BI
before insert on DEPARTMENT
    for each row
        begin
            select SQ_DPT_PK.NEXTVAL
            into :New.DPT_ID from DUAL;
end;
/


create trigger TR_EMPLOYEE_BI
before insert on EMPLOYEE
    for each row
         begin
            select SQ_EMP_PK.NEXTVAL
            into :New.EMP_ID from DUAL;
end;
/




create trigger TR_EVENTYPE_BI
before insert on EVENTYPE
    for each row
        begin
            select SQ_EVNTYPE_PK.NEXTVAL
            into :New.EVNTYPE_ID from DUAL;
end;
/


create trigger TR_GRADEFORMAT_BI
before insert on GRADEFORMAT
    for each row
        begin
            select SQ_GRDFORM_PK.NEXTVAL
            into :New.GRDFORM_ID from DUAL;
end;
/


create trigger TR_GRDUPL_BI
before insert on GRADEUPLOAD
    for each row
        begin
            select SQ_GRDUPL_PK.NEXTVAL
            into :NEW.GRDUPL_ID from DUAL;
end;
/


create trigger TR_REIM_BI
before insert on REIMBURSEMENT
    for each row
        begin
            select SQ_REIM_PK.NEXTVAL
            into :NEW.REIM_ID from DUAL;
end;
/

create or replace procedure insertEmployee
--()
as
begin
    select max(1) from dual;
end;
/

create or replace function totalReimbursed
(EMPID in integer)
return number
as
    REIMBURSED number(10,2);
begin
    select sum(REIM_PROJREIM) into REIMBURSED from REIMBURSEMENT where EMP_ID = EMPID AND REIM_PASSED = 1;
    return REIMBURSED;
end;
/

create or replace procedure stateChanged
(APRVL in char, EROLE in integer, rID in integer)
as
begin
    if APRVL = '1' then
        if erole=1 then
            update REIMBURSEMENT set REIM_APPROVAL = 6 where REIM_ID = rID;
        else 
            update REIMBURSEMENT set REIM_APPROVAL = EROLE+1 where REIM_ID = rID;
        end if;
    else
        update REIMBURSEMENT set REIM_APPROVAL = 0 where REIM_ID = rID;
    end if;
end;
/