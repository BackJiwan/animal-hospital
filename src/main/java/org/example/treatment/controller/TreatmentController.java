package org.example.treatment.controller;
import lombok.RequiredArgsConstructor;
import org.example.animal.repository.AnimalRepository;
import org.example.animal.service.AnimalService;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.member.view.MemberView;
import org.example.treatment.repository.TreatmentRepository;
import org.example.treatment.service.TreatmentService;
import org.example.treatment.view.TreatmentView;

@RequiredArgsConstructor
public class TreatmentController {
    private final TreatmentRepository treatmentRepository = new TreatmentRepository();
    private final MemberRepository memberRepository = new MemberRepository();
    private final AnimalRepository animalRepository = new AnimalRepository();
    private TreatmentService treatmentService = new TreatmentService(treatmentRepository);
    private MemberService memberService = new MemberService(memberRepository);
    private AnimalService animalService = new AnimalService(animalRepository);

    public void treatmentMenu(){
        TreatmentView view = new TreatmentView(treatmentService,treatmentRepository,memberService,
                memberRepository,animalService,animalRepository);
        view.treatmentView();
    }
}
