package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(basePackages="challenge")
public interface RecipeRepository extends MongoRepository<Recipe, String>, CustomRepositorySearch {
    List<Recipe> findByIngredientsOrderByTitle(String ingredient);
}
