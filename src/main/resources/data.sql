INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('admin', 1000, 'admin@homecare.nl', '1', 'Admin1', '$2a$12$uuI98Ue2D7yL5GXNDaAI.O/wPZ59XTNKw9ydolKmuSFG8uNKez1CO', 'ADMIN', null , null); --AdminPassword

INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('nurse', 1001, 'nurse@homecare.nl', '1', 'Nurse1', '$2a$12$cxwUo5SoXS0nquqGjpB2Uuu3h9mlnZAsbRT64vTyFv/n9DEI7ioAi', 'NURSE', '12345' , null); --NursePassword

INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('patient', 1002, 'patient@homecare.nl', '1', 'Patient1', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', null , '15-15-1970'); --PatientPassword

-- INSERT INTO public.users(
--     user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
-- VALUES ('patient', 1003, 'patient2@homecare.nl', '1', 'Patient2', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', null , '15-15-1989'); --PatientPassword


INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(2000, 'pleister vervangen', 'linker knie', 'schaafwond', 1002);

INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(2001, 'pleister vervangen en daarna spoelen', 'hoofd', 'snijwond', 1002);

INSERT INTO public.wound_examination(
    id, assessment_date, nurse_assessment, patient_comment, photo_date, file_file_name, wound_id)
VALUES (3000, null, null, null, '2022-06-29', null, 2000);
