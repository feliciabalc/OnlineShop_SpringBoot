package map.project.demo.Entities;

public class WorkersFactory {

    public Employee createEmployee(String name, String salary, double telefon){
       Employee employee= new Employee();
       employee.name=name;
       employee.salary=salary;
       employee.telefon=telefon;
       return  employee;
    }

    public Courier createCourier(String name, String salary, double telefon){
        Courier courier= new Courier();
        courier.name=name;
        courier.salary=salary;
        courier.telefon=telefon;
        courier.role="Courier";
        return courier;
    }
}
