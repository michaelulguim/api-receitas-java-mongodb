package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepositorySearchImpl implements CustomRepositorySearch {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Recipe> searchByTitleAndDescriptionContainsIgnoreCase(String search) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("title").regex(search, "i"),
                Criteria.where("description").regex(search,"i")
        );
        query.addCriteria(criteria).with(Sort.by("title").ascending());
        return template.find(query, Recipe.class);
    }
}
