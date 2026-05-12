package com.dinotaurent.notas_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotasAppApplication {

	static void main(String[] args) {
		SpringApplication.run(NotasAppApplication.class, args);
		IO.println("Hola mundo desde la nueva forma de imprimir en java");
	}

}
