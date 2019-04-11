package challenge;

import java.util.List;

public interface CustomRepositorySearch {
    List<Recipe> searchByTitleAndDescriptionContainsIgnoreCase(String search);
}
