package map.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"map.project.demo"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//2 pattern-uri
	//pe maine: in controller trebuie add relatii si metodele suplimentare crud-ului, incepand din repo, in service le apelez si in controller cu get
	//teste

}
