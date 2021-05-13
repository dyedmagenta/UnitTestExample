package moe.dyedmagenta.eldritchrng.model;

import lombok.Getter;
import lombok.Setter;
import moe.dyedmagenta.eldritchrng.dto.FieldType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "field")
public class Field {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "field_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldType type;

    @Column(name = "is_named_city", nullable = false)
    private boolean namedCity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_image_id", referencedColumnName = "id")
    private FieldImage iconImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "named_city_image_id", referencedColumnName = "id")
    private FieldImage namedCityImage;
}
