package jpahibernate;


import jpahibernate.dao.PokemonDao;
import jpahibernate.dao.TypeDao;
import jpahibernate.dao.helper.DatabaseHelper;
import jpahibernate.exception.AlreadyExistsException;
import jpahibernate.model.Pokemon;
import jpahibernate.model.PokemonType;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	private static PokemonDao pokemonDao = new PokemonDao(); 
	private static TypeDao typeDao = new TypeDao();

	public static void main(String[] args) throws AlreadyExistsException {
		
		// Appeler le DAO pour récupérer la liste de tous les Pokemon présents en base de données
		System.out.println("Liste de Pokemon en présents en base de données :");
		pokemonDao.findAll().forEach(p -> System.out.println(p));
		System.out.println();
		
		// Insérer les 8 Pokemon suivants du pokédex (cf. https://www.pokepedia.fr/Liste_des_Pok%C3%A9mon_dans_l%27ordre_du_Pok%C3%A9dex_de_Kanto)
		System.out.println("Insertion des Pokemon :");
		
		List<Pokemon> newPokemons = new ArrayList<>();
		PokemonType sol = typeDao.findByName("Sol");
		Pokemon sablaireau = new Pokemon(28, "Sablaireau", 35);
		sablaireau.addType(sol);
		newPokemons.add(sablaireau);
		
		Pokemon sabelette = new Pokemon(27, "Sabelette", 12, sablaireau);
		sabelette.addType(sol);
		newPokemons.add(sabelette);
		
		PokemonType poison = typeDao.findByName("Poison");
		Pokemon nidoqueen = new Pokemon(31, "Nidoqueen", 70);
		nidoqueen.addType(sol);
		nidoqueen.addType(poison);
		newPokemons.add(nidoqueen);
		
		Pokemon nidorina = new Pokemon(30, "Nidorina", 40, nidoqueen);
		nidorina.addType(poison);
		newPokemons.add(nidorina);
		
		Pokemon nidoranF = new Pokemon(29, "Nidoran♀", 14, nidorina);
		nidoranF.addType(poison);
		newPokemons.add(nidoranF);
		
		Pokemon nidoking = new Pokemon(34, "Nidoking", 80);
		nidoking.addType(sol);
		nidoking.addType(poison);
		newPokemons.add(nidoking);
		
		Pokemon nidorino = new Pokemon(33, "Nidorino", 50, nidoking);
		nidorino.addType(poison);
		newPokemons.add(nidorino);
		
		Pokemon nidoranM = new Pokemon(32, "Nidoran♂", 18, nidorino);
		nidoranM.addType(poison);
		newPokemons.add(nidoranM);
		
		newPokemons.forEach(p -> {
			try {
				System.out.println("Pokemon inserted : " + pokemonDao.insert(p));
			} catch (AlreadyExistsException e) {
				System.out.println(e.getMessage());
			}
		});
		System.out.println();
		
		// Récupérer le Pokemon d'id 9
		System.out.println("Pokemon d'id 9 :");
		System.out.println(pokemonDao.findById(9));
		System.out.println();
		
		// Récupérer le Pokemon de numéro 12
		System.out.println("Pokemon numéro 12 :");
		Pokemon pokemon12 = pokemonDao.findByNumber(12);
		System.out.println(pokemon12);
		System.out.println();
		
		// Modification du Pokemon numero 12
		System.out.println("Modification Pokemon numéro 12 :");
		pokemon12.setHealthPoints(6000);
		System.out.println(pokemonDao.update(pokemon12));
		System.out.println(pokemonDao.findByNumber(12));
		System.out.println();
		
		// Récupérer le Pokemon de numéro 32
		System.out.println("Pokemon numéro 32 :");
		System.out.println(pokemonDao.findByNumber(32));
		System.out.println();
		
		// Récupérer les Pokemon de type poison
		System.out.println("Liste de Pokemon de type Poison :");
		PokemonType eau = typeDao.findByName("Poison");
		pokemonDao.findAllByType(eau).forEach(p -> System.out.println(p));
		
		// Libération des ressources
		DatabaseHelper.closeEntityManagerFactory();
	}

}
