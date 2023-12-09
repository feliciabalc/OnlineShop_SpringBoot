package map.project.demo.DB_Repo;

import map.project.demo.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    default List<Orders> filteredByDate(String date) {
        List<Orders> orders = findAll();
        List<Orders> filteredOrders = new ArrayList<>();
        for (Orders item : orders) {
            if (Objects.equals(item.getDate(), date))
                filteredOrders.add(item);
        }
        return filteredOrders;

    }
}
