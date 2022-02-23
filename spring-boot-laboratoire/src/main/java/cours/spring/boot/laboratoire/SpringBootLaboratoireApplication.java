package cours.spring.boot.laboratoire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Classe principale
public class SpringBootLaboratoireApplication {

	public static void main(String[] args) {
		// 1. Mise en place du framework
		// 2. Rechercher vos classes applications qui doivent être prises en compte par Spring
		//  2.1 scan le classpath (cours.spring., cours.spring.boot, cours.boot.laboratoire)
		// 	2.2 @Stereoptype (@Component, @Service, @Controller, @RestController)
		//  2.3 instancier les classes trouvées
		// 3. auto configurer selon ce qui est disponbiel sur le classpath (framework, libraries)
		//  3.1 starter-web -> tomcat -> démarre le server
		SpringApplication.run(SpringBootLaboratoireApplication.class, args);
	}

}
