package moe.dyedmagenta.eldritchrng.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "field_image")
@NoArgsConstructor
public class FieldImage {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    //TODO - Switch to S3 or some kind of file repository
}
