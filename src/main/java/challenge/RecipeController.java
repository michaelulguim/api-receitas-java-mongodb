package challenge;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;


	@PostMapping
	public Recipe save(@RequestBody Recipe recipe) {
		return service.save(recipe);
	}
	@PutMapping("/{id}")
	public void update(@RequestBody Recipe recipe, @PathVariable("id") String id) {
		service.update(id, recipe);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@GetMapping("/{id}")
	public Recipe get(@PathVariable String id) {
		return service.get(id);
	}

	@GetMapping("/ingredient")
	public List<Recipe> listByIngredient(@RequestParam("ingredient") String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping("/search")
	public List<Recipe> search(@RequestParam("search") String search) {
		return service.search(search);
	}

	@PostMapping("/{id}/like/{userId}")
	public void like(@PathVariable("id") String id, @PathVariable("userId") String userId ) {
		service.like(id, userId);
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void unlike(@PathVariable("id") String id, @PathVariable("userId") String userId) {
		service.unlike(id, userId);
	}

	@PostMapping("/{id}/comment")
	public RecipeComment addComment(@RequestBody RecipeComment comment, @PathVariable("id") String id) {
		return service.addComment(id, comment);
	}

	@PutMapping("/{id}/comment/{commentId}")
	public void updateComment(@RequestBody RecipeComment comment,
							  @PathVariable("id") String id,
							  @PathVariable("commentId") String commentId) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("{id}/comment/{commentId}")
	public void deleteComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId) {
		service.deleteComment(id, commentId);
	}

}
