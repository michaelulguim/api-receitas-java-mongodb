package challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe para mapear o comentario da receita no MongoDB
 *
 */
@Document(collection = "recipeComment")
public class RecipeComment {

    @Id
    private ObjectId id;

    private String comment;


    public String getId() {
        return this.id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
