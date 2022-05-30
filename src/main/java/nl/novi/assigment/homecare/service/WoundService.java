package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.dto.WoundDto;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

@Service
public class WoundService {
    private final WoundRepository woundRepository;
    private final PatientService patientService;


    public WoundService(WoundRepository woundRepository, PatientService patientService) {
        this.woundRepository = woundRepository;
        this.patientService = patientService;
    }

    public Wound getWoundById (Long id){
        Wound wound = woundRepository.findById(id).get();
        return wound;
    }

    public WoundDto addWound(CreateWoundDto createWoundDto) {
        Wound wound = new Wound();
        wound.setWoundName(createWoundDto.getWoundName());
        wound.setWoundLocation(createWoundDto.getWoundLocation());
        wound.setTreatmentPlan(createWoundDto.getTreatmentPlan());
        wound.setPatient(patientService.getPatientById(createWoundDto.getPatientId()));
        Wound savedWound = woundRepository.save(wound);
        return toWoundDto(savedWound);
    }

    public WoundDto toWoundDto (Wound wound){
        WoundDto woundDto = new WoundDto();
        woundDto.setId(wound.getId());
        woundDto.setWoundName(wound.getWoundName());
        woundDto.setWoundLocation(wound.getWoundLocation());
        woundDto.setTreatmentPlan(wound.getTreatmentPlan());
        woundDto.setPatient(wound.getPatient());
        return woundDto;

    }




}
