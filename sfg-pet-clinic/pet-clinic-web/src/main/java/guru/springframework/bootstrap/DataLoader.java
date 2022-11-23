package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0){
            loadData();
        }

    }

    private void loadData() {

        PetType dogType = new PetType();
        dogType.setName("dog");
        PetType savedDogPetType = petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("cat");
        PetType savedCatPetType = petTypeService.save(catType);

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");
        Speciality SavedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("surgery");
        Speciality SavedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("dentistry");
        Speciality SavedDentistry = specialtyService.save(dentistry);

        Owner mike = new Owner();
        mike.setFirstName("Michael");
        mike.setLastName("Weston");
        mike.setAddress("123 West Side");
        mike.setCity("Miami");
        mike.setTelephone("234523455");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(mike);
        mikesPet.setName("Snoopy");
        mikesPet.setBirthDate(LocalDate.now());
        mike.getPets().add(mikesPet);

        ownerService.save(mike);

        Owner cindy = new Owner();
        cindy.setFirstName("Cindy");
        cindy.setLastName("Rage");
        cindy.setAddress("123 South Side");
        cindy.setCity("Atlanta");
        cindy.setTelephone("3456346");

        Pet cindysPet = new Pet();
        cindysPet.setPetType(savedCatPetType);
        cindysPet.setOwner(cindy);
        cindysPet.setName("Garfield");
        cindysPet.setBirthDate(LocalDate.now());
        cindy.getPets().add(cindysPet);

        ownerService.save(cindy);

        Visit garfieldsVisit = new Visit();
        garfieldsVisit.setPet(cindysPet);
        garfieldsVisit.setDescription("Routine checkup");
        garfieldsVisit.setDate(LocalDate.now());
        visitService.save(garfieldsVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(SavedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Lan");
        vet2.setLastName("Lasaki");
        vet2.getSpecialities().add(SavedSurgery);


        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
