package map.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"map.project.demo"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//2 pattern-uri
	//pe maine: main,in controller trebuie addrelatii si metodele suplimentare crud-ului, incepand din repo, in service le apelez si in controller cu get
	//teste
	//intreaba de main
}
