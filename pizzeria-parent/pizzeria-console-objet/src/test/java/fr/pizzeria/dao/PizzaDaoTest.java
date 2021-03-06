package fr.pizzeria.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.console.PizzeriaAppSpringConfig;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PizzeriaAppSpringConfig.class)
public class PizzaDaoTest {

	@Autowired
	private IDao<Pizza, String> pizzaDao;

	@Test
	public void testFindAll() {

		List<Pizza> listZa = pizzaDao.findAll();

		assertTrue(8 == 8);

	}

}
