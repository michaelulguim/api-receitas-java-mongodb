package challenge;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
@Document(collection = "recipe")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipe {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private List<String> ingredients;

    private List<String> likes;

    private List<RecipeComment> comments;

    public String getId() {
        return id.toHexString();
    }

    public List<RecipeComment> getComments() {
        return comments;
    }

    public void setComments(List<RecipeComment> comments) {
        this.comments = comments;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<String> getIngredients() {
        if (this.ingredients == null) {
            return null;
        }
        return Collections.unmodifiableList(this.ingredients);
    }

    public void setIngredients(List<String> ingredients) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLikes() {
        if (this.likes == null) {
            return null;
        }
        return Collections.unmodifiableList(this.likes);
    }
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void addLikes(String like) {
        if (this.likes == null) {
            this.likes = new ArrayList<>();
        }
        this.likes.add(like);
    }

    public void removeLikes(String like) {
        this.likes.remove(like);
    }

    public void addIngredients(String like) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        this.ingredients.add(like);
    }

    public void removeIngredients(String like) {
        this.ingredients.remove(like);
    }

    public void addComments(RecipeComment comment) {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(comment);
    }

    public void removeComments(RecipeComment comment) {
        this.comments.remove(comment);
    }

}
