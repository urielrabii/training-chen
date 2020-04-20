package team1.spring.training.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="files")
@Getter
@Setter
@NoArgsConstructor
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty
    @Setter(AccessLevel.PROTECTED)
    @Column
    private String id;
    @JsonProperty
    @Column
    private String name;
    @JsonProperty
    @Column
    private String location;
    @JsonProperty
    @Column
    private String time;
    @JsonProperty
    @Column
    private Long size;

 public File(String name, String location, String time, Long size) {
        this.name = name;
        this.location = location;
        this.time = time;
        this.size = size;
    }
}
