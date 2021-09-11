package TestSpring.News;

class NewsNotFoundException extends RuntimeException {

    NewsNotFoundException(Long id) {
        super("Could not find news" + id);
    }
}