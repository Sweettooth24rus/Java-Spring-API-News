package TestSpring.NewsType;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class NewsTypeController {

    public static NewsTypeRepository repository;

    private final NewsTypeModelAssembler assembler;

    NewsTypeController(NewsTypeRepository repository, NewsTypeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/newsType")
    CollectionModel<EntityModel<NewsType>> all() {

        List<EntityModel<NewsType>> newsType = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(newsType, linkTo(methodOn(NewsTypeController.class).all()).withSelfRel());
    }

    @PostMapping("/newsType")
    NewsType newNewsType(@RequestBody NewsType newNewsType) {
        return repository.save(newNewsType);
    }

    @GetMapping("/newsType/{id}")
    EntityModel<NewsType> one(@PathVariable Long id) {

        NewsType newsType = repository.findById(id) //
                .orElseThrow(() -> new NewsTypeNotFoundException(id));

        return assembler.toModel(newsType);
    }

    @PutMapping("/newsType/{id}")
    NewsType replaceNewsType(@RequestBody NewsType newNewsType, @PathVariable Long id) {

        return repository.findById(id)
                .map(newsType -> {
                    newsType.setName(newNewsType.getName());
                    newsType.setColor(newNewsType.getColor());
                    return repository.save(newsType);
                })
                .orElseGet(() -> {
                    newNewsType.setIdType(id);
                    return repository.save(newNewsType);
                });
    }

    @DeleteMapping("/newsType/{id}")
    void deleteNewsType(@PathVariable Long id) {
        repository.deleteById(id);
    }
}