package TestSpring.News;

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
class NewsController {

    private final NewsRepository repository;

    private final NewsModelAssembler assembler;

    NewsController(NewsRepository repository, NewsModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/news")
    CollectionModel<EntityModel<News>> all() {

        List<EntityModel<News>> news = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(news, linkTo(methodOn(NewsController.class).all()).withSelfRel());
    }

    @GetMapping("/news/type/{typeID}")
    CollectionModel<EntityModel<News>> all(@PathVariable Long typeID) {

        List<News> tmp = repository.findAll();
        tmp.removeIf(news -> !news.getNewsType().getIdType().equals(typeID));
        List<EntityModel<News>> news = tmp.stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(news, linkTo(methodOn(NewsController.class).all()).withSelfRel());
    }

    @PostMapping("/news")
    News newNews(@RequestBody News newNews) {
        return repository.save(newNews);
    }

    @GetMapping("/news/{id}")
    EntityModel<News> one(@PathVariable Long id) {

        News news = repository.findById(id) //
                .orElseThrow(() -> new NewsNotFoundException(id));

        return assembler.toModel(news);
    }

    @PutMapping("/news/{id}")
    News replaceNews(@RequestBody News newNews, @PathVariable Long id) {

        return repository.findById(id)
                .map(news -> {
                    news.setName(newNews.getName());
                    news.setDescriptionShort(newNews.getDescriptionShort());
                    news.setDescriptionFull(newNews.getDescriptionFull());
                    news.setNewsType(newNews.getNewsType());
                    return repository.save(news);
                })
                .orElseGet(() -> {
                    newNews.setId(id);
                    return repository.save(newNews);
                });
    }

    @DeleteMapping("/news/{id}")
    void deleteNews(@PathVariable Long id) {
        repository.deleteById(id);
    }
}