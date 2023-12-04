package map.project.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    private Long id;
    private String stars;
    private String comment;
    private String date;

    public Review(Long id,String stars, String comment, String date) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Articles article;

    protected Review() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", stars='" + stars + '\'' +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
