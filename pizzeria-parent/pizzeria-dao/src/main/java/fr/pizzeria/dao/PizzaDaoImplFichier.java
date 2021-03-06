package fr.pizzeria.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.pizzeria.exception.DaoRuntimeException;
import fr.pizzeria.exception.DeleteDaoException;
import fr.pizzeria.exception.UpdateDaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Quelqun
 * 
 *         cette classe implémente IDao avec des fichiers .txt sur le disque
 *
 */
public class PizzaDaoImplFichier implements IDao<Pizza, String> {

	static final String CHEMIN_SAUVEGARDE = "data\\";

	@Override
	public List<Pizza> findAll() {

		try (Stream<Path> files = Files.list(Paths.get(CHEMIN_SAUVEGARDE))) {

			return files.map(chemin -> {

				try (Stream<String> lines = Files.lines(chemin)) {

					String[] items = lines.findFirst().get().split(";");
					return new Pizza(items[1], items[2], Double.parseDouble(items[3]),
							CategoriePizza.getEnum(items[4]));
				} catch (IOException e) {
					throw new DaoRuntimeException(e);
				}

			}).collect(Collectors.toList());

		} catch (IOException e) {
			throw new DaoRuntimeException(e);
		}

	}

	@Override
	public void saveNew(Pizza pizza) {

		List<String> lines = Arrays.asList(pizza.toCSV());

		try {
			Files.write(Paths.get(CHEMIN_SAUVEGARDE + pizza.getCode() + ".txt"), lines, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new DaoRuntimeException(e);
		}

	}

	@Override
	public void update(String code, Pizza pizza) throws UpdateDaoException {

		if (Files.exists(Paths.get(CHEMIN_SAUVEGARDE + code + ".txt"))) {

			List<String> lines = Arrays.asList(pizza.toCSV());

			try {

				Files.delete(Paths.get(CHEMIN_SAUVEGARDE + code + ".txt"));

				Files.write(Paths.get(CHEMIN_SAUVEGARDE + pizza.getCode() + ".txt"), lines, StandardOpenOption.CREATE);
			} catch (IOException e) {
				throw new DaoRuntimeException(e);
			}

		} else {
			throw new UpdateDaoException("Code pizza introuvable");
		}

	}

	@Override
	public void delete(String code) throws DeleteDaoException {

		if (Files.exists(Paths.get(CHEMIN_SAUVEGARDE + code + ".txt"))) {

			try {
				Files.delete(Paths.get(CHEMIN_SAUVEGARDE + code + ".txt"));
			} catch (IOException e) {
				throw new DaoRuntimeException(e);
			}

		} else {
			throw new DeleteDaoException("Code pizza introuvable");
		}

	}

}
