package map.project.demo.DB_Controller;

import map.project.demo.Entities.Courier;
import map.project.demo.Entities.Courier;
import map.project.demo.Service.CartService;
import map.project.demo.Service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courier")
public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long id) {
        Courier courier = courierService.getCourierById(id);

        if (courier != null) {
            return new ResponseEntity<>(courier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCourier() {
        List<Courier> courier = (List<Courier>) courierService.getAllCouriers();
        return new ResponseEntity<>(courier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Courier> updateCourier(@PathVariable Long id, @RequestBody Courier updatedCourier) {
        Courier existingCourier = courierService.getCourierById(id);
        if (existingCourier != null) {
            updatedCourier.setId(id);
            Courier savedCourier = courierService.saveCourier(updatedCourier);
            return new ResponseEntity<>(savedCourier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
