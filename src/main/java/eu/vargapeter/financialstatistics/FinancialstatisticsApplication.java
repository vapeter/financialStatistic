package eu.vargapeter.financialstatistics;

import eu.vargapeter.financialstatistics.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FinancialstatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialstatisticsApplication.class, args);
	}

}
