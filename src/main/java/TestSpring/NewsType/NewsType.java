package TestSpring.NewsType;

import TestSpring.News.News;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public
class NewsType {

    private @Id @GeneratedValue Long idType;
    private String name;
    private Integer color;

    @OneToMany(mappedBy = "newsType")
    @JsonIgnoreProperties("newsType")
    private Set<News> news;

    public NewsType() {}

    public NewsType(String name, Integer color) {

        this.name = name;
        this.color = color;

    }

    public Long getIdType() {
        return this.idType;
    }

    public String getName() {
        return this.name;
    }

    public Integer getColor() {
        return this.color;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof NewsType))
            return false;
        NewsType newsType = (NewsType) o;
        return Objects.equals(this.idType, newsType.idType) && Objects.equals(this.name, newsType.name)
                && Objects.equals(this.color, newsType.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idType, this.name, this.color);
    }

    @Override
    public String toString() {
        return "NewsType{" + "id = " + this.idType + ", name = '" + this.name + '\''
                + ", color = '" + this.color + '\'' + '}';
    }
}