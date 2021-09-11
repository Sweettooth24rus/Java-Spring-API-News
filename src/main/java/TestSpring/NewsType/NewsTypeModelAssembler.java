package TestSpring.NewsType;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class NewsTypeModelAssembler implements RepresentationModelAssembler<NewsType, EntityModel<NewsType>> {

    @Override
    public EntityModel<NewsType> toModel(NewsType newsType) {

        return EntityModel.of(newsType, //
                linkTo(methodOn(NewsTypeController.class).one(newsType.getIdType())).withSelfRel(),
                linkTo(methodOn(NewsTypeController.class).all()).withRel("newsType"));
    }
}