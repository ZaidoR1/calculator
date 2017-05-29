package com.example.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CalcDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalcDbApplication.class, args);
		
		
//		System.out.println( "----" );
		// DB-Session muss geschlossen werden...
//		if ( HibernateUtil.getSessionFactory() != null )
//			HibernateUtil.getSessionFactory().close();
	}
}
