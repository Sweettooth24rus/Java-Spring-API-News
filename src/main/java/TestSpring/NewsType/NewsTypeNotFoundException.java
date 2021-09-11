package TestSpring.NewsType;

class NewsTypeNotFoundException extends RuntimeException {

    NewsTypeNotFoundException(Long id) {
        super("Could not find types" + id);
    }
}