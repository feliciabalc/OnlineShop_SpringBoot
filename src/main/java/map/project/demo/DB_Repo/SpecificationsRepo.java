package map.project.demo.DB_Repo;

import map.project.demo.Entities.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface SpecificationsRepo extends JpaRepository<Specifications,Long> {
    default List<Specifications> filteredByColor(String color) {
        List<Specifications> specifications = findAll();
        List<Specifications> filteredSpecifications = new ArrayList<>();
        for (Specifications item : specifications) {
            if (Objects.equals(item.getColor(), color))
                filteredSpecifications.add(item);
        }
        return filteredSpecifications;

    }

    default List<Specifications> filteredBySize(String size) {
        List<Specifications> specifications = findAll();
        List<Specifications> filteredSpecifications = new ArrayList<>();
        for (Specifications item : specifications) {
            String[] allSizes = new String[]{item.getSize()};
            for (int j = 0; j < allSizes.length; j++) {
                if (Objects.equals(allSizes[j], size)) {
                    filteredSpecifications.add(item);
                }
            }}
        return filteredSpecifications;

    }

}
