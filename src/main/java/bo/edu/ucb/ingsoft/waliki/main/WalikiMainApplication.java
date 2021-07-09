package bo.edu.ucb.ingsoft.waliki.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class WalikiMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalikiMainApplication.class, args);
	}


}
