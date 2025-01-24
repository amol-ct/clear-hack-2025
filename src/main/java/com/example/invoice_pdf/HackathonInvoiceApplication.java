package com.example.invoice_pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.invoice_pdf", "com/example/invoice_pdf/controller"})
public class HackathonInvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonInvoiceApplication.class, args);
	}

}
