package br.com.fiap.librarybatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class LibraryBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBatchApplication.class, args);
	}

}
