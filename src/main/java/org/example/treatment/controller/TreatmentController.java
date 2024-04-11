package org.example.treatment.controller;
import lombok.RequiredArgsConstructor;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.member.view.MemberView;
import org.example.treatment.repository.TreatmentRepository;
import org.example.treatment.service.TreatmentService;
import org.example.treatment.view.TreatmentView;

@RequiredArgsConstructor
public class TreatmentController {
    private final TreatmentRepository treatmentRepository = new TreatmentRepository();
    private TreatmentService treatmentService = new TreatmentService(treatmentRepository);

    public void treatmentMenu(){
        TreatmentView view = new TreatmentView(treatmentService,treatmentRepository);
        view.treatmentView();
    }
}
