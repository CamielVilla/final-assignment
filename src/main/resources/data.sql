INSERT INTO public.admin(id, email, name, password, role, enabled)
VALUES (1000, 'admin@homecare.nl', 'Camiel Villa', '$2a$12$uuI98Ue2D7yL5GXNDaAI.O/wPZ59XTNKw9ydolKmuSFG8uNKez1CO', 'ADMIN', '1'); --AdminPassword

INSERT INTO public.nurse(id, big_number, email, name, password, role, enabled)
VALUES (1000, 123456, 'nurse@homecare.nl', 'nurse1', '$2a$12$cxwUo5SoXS0nquqGjpB2Uuu3h9mlnZAsbRT64vTyFv/n9DEI7ioAi', 'NURSE', '1'); --NursePassword

INSERT INTO public.patient(id, date_of_birth, email, name, password, role, enabled)
VALUES(1000, '11-11-1911', 'patient@gmail.com', 'patient1', '$2a$12$Uw2nvlMAhXQzqvAOEHLJuuAB0QQkH2lmFQd4zvOX.cx/Jqj3tqNWa', 'PATIENT', '1'); --PatientPassword

INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(1000, 'pleister vervangen', 'linker knie', 'schaafwond', 1000);

INSERT INTO public.wound_photo(id, assessment_date, nurse_assessment, patient_comment, photo_date, wound_id)
VALUES(1000,'2022-05-31T11:26:02.714784', 'ga zo door', 'ging goed', '2022-05-31T11:26:02.714784', 1000 )


