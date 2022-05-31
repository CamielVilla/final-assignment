INSERT INTO public.admin(id, email, name, password)
VALUES (1000, 'admin@homecare.nl', 'Camiel Villa', 'AdminPassword');

INSERT INTO public.nurse(id, big_number, email, name, password)
VALUES (1000, 123456, 'nurse@homecare.nl', 'nurse1', 'NursePassword');

INSERT INTO public.patient(id, date_of_birth, email, name, password)
VALUES(1000, '11-11-1911', 'patient@gmail.com', 'patient1', 'PatientPassword');

INSERT INTO public.wound(id, treatment_plan, wound_location, wound_name, patient_id)
VALUES(1000, 'pleister vervangen', 'linker knie', 'schaafwond', 1000);

INSERT INTO public.wound_photo(id, assessment_date, nurse_assessment, patient_comment, photo_date, wound_id)
VALUES(1000,'2022-05-31T11:26:02.714784', 'ga zo door', 'ging goed', '2022-05-31T11:26:02.714784', 1000 )


