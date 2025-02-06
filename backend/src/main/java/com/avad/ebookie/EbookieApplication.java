package com.avad.ebookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // jpa audit
@SpringBootApplication
public class EbookieApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbookieApplication.class, args);
	}

}
