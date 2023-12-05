package map.project.demo.Service;

import map.project.demo.DB_Repo.ClientRepo;
import map.project.demo.DB_Repo.CourierRepo;
import map.project.demo.Entities.Client;
import map.project.demo.Entities.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {
    private final CourierRepo courierRepo;

    @Autowired
    public CourierService(CourierRepo courierRepo) {
        this.courierRepo = courierRepo;
    }

    public List<Courier> getAllCouriers() {
        return (List<Courier>) courierRepo.findAll();
    }

    public Courier getCourierById(Long id) {
        return courierRepo.findById(id).orElse(null);
    }

    public Courier saveCourier(Courier courier) {
        return courierRepo.save(courier);
    }

    public void deleteCourier(Long id) {
        courierRepo.deleteById(id);
    }
}
