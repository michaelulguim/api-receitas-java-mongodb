package challenge;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return this.recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		this.recipeRepository.findById(id).map(record -> {
			record.setIngredients(recipe.getIngredients());
			record.setTitle(recipe.getTitle());
			record.setDescription(recipe.getDescription());
			return this.recipeRepository.save(record);
		});
	}

	@Override
	public void delete(String id) {
		this.recipeRepository.findById(id).map(record -> {
			this.recipeRepository.delete(record);
			return null;
		});
	}

	@Override
	public Recipe get(String id) {
		return this.recipeRepository.findById(id).orElse(null);
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return this.recipeRepository.findByIngredientsOrderByTitle(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return this.recipeRepository.searchByTitleAndDescriptionContainsIgnoreCase(search);
	}

	@Override
	public void like(String id, String userId) {
		 this.recipeRepository.findById(id).map(x -> {
		 	 x.addLikes(userId);
			 return recipeRepository.save(x);
		});

	}
	@Override
	public void unlike(String id, String userId) {
		this.recipeRepository.findById(id).map(record -> {
			record.removeLikes(userId);
			return this.recipeRepository.save(record);
		});
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		comment.setId(ObjectId.get());
		this.recipeRepository.findById(id).map(recipe -> {
			recipe.addComments(comment);
			return this.recipeRepository.save(recipe);
		});
		return comment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Optional<Recipe> recipeOptional = this.recipeRepository.findById(id);
		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			recipe.getComments().stream()
					.filter(x -> x.getId().equals(commentId))
					.findFirst().map(record -> {
						record.setComment(comment.getComment());
						return this.recipeRepository.save(recipe);
					});
		}
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Optional<Recipe> recipeOptional = this.recipeRepository.findById(id);
		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			recipe.getComments().stream()
					.filter(x -> x.getId().equals(commentId)).findFirst()
					.map(x -> {
						recipe.removeComments(x);
						return this.recipeRepository.save(recipe);
					});

		}
	}

}
