package org.example.animal.controller;

import lombok.RequiredArgsConstructor;
import org.example.animal.service.AnimalService;
import org.example.animal.repository.AnimalRepository;
import org.example.animal.view.AnimalView;

@RequiredArgsConstructor
public class AnimalController {
    private final AnimalRepository animalRepository = new AnimalRepository();
    private AnimalService animalService = new AnimalService(animalRepository);

    public void animalMenu() {
        AnimalView view = new AnimalView(animalRepository,animalService);
        view.animalView();
    }
}
