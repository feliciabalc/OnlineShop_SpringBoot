package Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.DB_Repo.SuppliersRepo;
import map.project.demo.Entities.Review;
import map.project.demo.Entities.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class SupplierService {

    private final SuppliersRepo suppliersRepo;

    @Autowired
    public SupplierService(SuppliersRepo suppliersRepo) {
        this.suppliersRepo = suppliersRepo;
    }

    public List<Suppliers> getAllSuppliers() {
        return (List<Suppliers>) suppliersRepo.findAll();
    }

    public Suppliers getSupplierById(Long id) {
        return suppliersRepo.findById(id).orElse(null);
    }

    public Suppliers saveSupplier(Suppliers supplier) {
        return suppliersRepo.save(supplier);
    }

    public void deleteSupplier(Long id) {
        suppliersRepo.deleteById(id);
    }
}
