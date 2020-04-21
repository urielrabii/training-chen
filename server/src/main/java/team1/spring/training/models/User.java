package team1.spring.training.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.String;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="files")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty
    @Setter(AccessLevel.PROTECTED)
    @Column
    private String id;
    @JsonProperty
    @Column
    private String firstName;
    @JsonProperty
    @Column
    private String lastName;
    @JsonProperty
    @Column
    private String userName;

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
}

