package models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllTodo",
        query = "SELECT m FROM Todo AS m ORDER BY m.id DESC"
    ),
    @NamedQuery(
            name = "getTodoCount",
            query = "SELECT COUNT(m) FROM Todo AS m"
            )
})
@Table(name = "todos")
public class Todo {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "content",length = 255 ,nullable = false)
    private String content;
    
    @Column(name = "created_at")
    private Timestamp created_at;
    
    @Column(name = "done")
    private boolean done;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    } 
}
