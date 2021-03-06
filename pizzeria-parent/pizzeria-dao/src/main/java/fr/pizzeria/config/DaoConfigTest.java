package fr.pizzeria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import fr.pizzeria.dao.IDao;
import fr.pizzeria.dao.PizzaDaoImpljdbcTemplate;
import fr.pizzeria.model.Pizza;

@Configuration
public class DaoConfigTest {

	@Bean
	public IDao<Pizza, String> pizzaDao() {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("conf//schema.sql")
				.addScripts("conf//test-data.sql").build();

		return new PizzaDaoImpljdbcTemplate(db);
	}

}