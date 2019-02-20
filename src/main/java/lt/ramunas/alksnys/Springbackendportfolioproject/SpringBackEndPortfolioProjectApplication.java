package lt.ramunas.alksnys.Springbackendportfolioproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("lt.ramunas.alksnys")
public class SpringBackEndPortfolioProjectApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(SpringBackEndPortfolioProjectApplication.class, args);
	}

}
