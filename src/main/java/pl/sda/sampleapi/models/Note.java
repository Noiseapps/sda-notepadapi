package pl.sda.sampleapi.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.StringJoiner;


@Entity
public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String content;
    private String creator;
    private Long createdDate;

    public Note() {
    }

    public Note(String title, String content, String creator) {
        this.title = title;
        this.content = content;
        this.creator = creator;
        this.createdDate = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) { return false; }
        if (title != null ? !title.equals(note.title) : note.title != null) { return false; }
        if (content != null ? !content.equals(note.content) : note.content != null) { return false; }
        if (creator != null ? !creator.equals(note.creator) : note.creator != null) { return false; }
        return createdDate != null ? createdDate.equals(note.createdDate) : note.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Note.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("content='" + content + "'")
                .add("creator='" + creator + "'")
                .add("createdDate=" + createdDate)
                .toString();
    }
}
