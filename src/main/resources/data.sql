INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('admin', 1000, 'admin@homecare.nl', '1', 'Admin1', '$2a$12$uuI98Ue2D7yL5GXNDaAI.O/wPZ59XTNKw9ydolKmuSFG8uNKez1CO', 'ADMIN', null , null); --AdminPassword

INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('nurse', 1001, 'nurse@homecare.nl', '1', 'Nurse1', '$2a$12$cxwUo5SoXS0nquqGjpB2Uuu3h9mlnZAsbRT64vTyFv/n9DEI7ioAi', 'NURSE', '12345' , null); --NursePassword

INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('patient', 1002, 'patient@homecare.nl', '1', 'Patient1', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', null , '15-15-1970'); --PatientPassword

INSERT INTO public.users(
    user_type, id, email, enabled, name, password, role, big_number, date_of_birth)
VALUES ('patient', 1003, 'patient2@homecare.nl', '1', 'Patient2', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', null , '15-15-1989'); --PatientPassword

-- INSERT INTO public.users(id, email, name, password, role, enabled)
-- VALUES (1000, 'admin@homecare.nl', 'Camiel Villa', '$2a$12$uuI98Ue2D7yL5GXNDaAI.O/wPZ59XTNKw9ydolKmuSFG8uNKez1CO', 'ADMIN', '1'); --AdminPassword
--
-- INSERT INTO public.users(id, big_number, email, name, password, role, enabled)
-- VALUES (1000, 123456, 'nurse@homecare.nl', 'nurse1', '$2a$12$cxwUo5SoXS0nquqGjpB2Uuu3h9mlnZAsbRT64vTyFv/n9DEI7ioAi', 'NURSE', '1'); --NursePassword
-- --
-- INSERT INTO public.patient(id, date_of_birth, email, name, password, role, enabled)
-- VALUES(1000, '11-11-1911', 'patient@gmail.com', 'patient1', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', '1'); --PatientPassword
--
INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(2000, 'pleister vervangen', 'linker knie', 'schaafwond', 1002);

INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(2001, 'pleister vervangen en daarna spoelen', 'hoofd', 'snijwond', 1002);
-- --
-- INSERT INTO public.wound_examination(
--     id, assessment_date, nurse_assessment, patient_comment, photo_date, file_file_name, wound_id)
-- VALUES (3000, null, null, null, null, null, 2000);

-- INSERT INTO public.wound_examination(id, assessment_date, nurse_assessment, patient_comment, photo_date, file_file_name, wound_id)
-- VALUES(3000,'2022-05-31T11:26:02.714784', 'ga zo door', 'ging goed', '2022-05-31T11:26:02.714784', null, 2000 )

