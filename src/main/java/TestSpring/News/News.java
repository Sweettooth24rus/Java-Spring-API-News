package TestSpring.News;

import TestSpring.NewsType.NewsType;
import TestSpring.NewsType.NewsTypeController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class News {

    private @Id @GeneratedValue Long id;
    private String name;
    private String descriptionShort;
    private String descriptionFull;

    @ManyToOne()
    @JoinColumn(name="idType", nullable = false)
    @JsonIgnoreProperties("news")
    private NewsType newsType;

    News() {}

    public News(String name, String descriptionShort, String descriptionFull, Long typeID) {

        this.name = name;
        this.descriptionShort = descriptionShort;
        this.descriptionFull = descriptionFull;
        this.newsType = new NewsType();
        this.newsType.setIdType(typeID);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptionShort() {
        return this.descriptionShort;
    }

    public String getDescriptionFull() {
        return this.descriptionFull;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof News))
            return false;
        News news = (News) o;
        return Objects.equals(this.id, news.id) && Objects.equals(this.name, news.name)
                && Objects.equals(this.descriptionShort, news.descriptionShort)
                && Objects.equals(this.descriptionFull, news.descriptionFull)
                && Objects.equals(this.newsType, news.newsType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.descriptionShort, this.descriptionFull, this.newsType);
    }

    @Override
    public String toString() {
        return "News{" + "id = " + this.id + ", name = '" + this.name + '\''
                + ", descriptionShort = '" + this.descriptionShort + '\''
                + ", descriptionFull = '" + this.descriptionFull + '\''
                + ", typeID = '" + this.newsType.toString() + '\'' + '}';
    }
}