package moe.dyedmagenta.eldritchrng.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import moe.dyedmagenta.eldritchrng.model.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
public class GenericFieldDTO {
    private UUID id;
    private String name;
    private FieldType fieldType;
    private UUID iconImageId;
    private UUID cityImageId;

    public GenericFieldDTO(Field entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.fieldType = entity.getType();
        this.iconImageId = entity.getIconImage() != null ? entity.getIconImage().getId() : null;
        this.cityImageId = entity.getNamedCityImage() != null ? entity.getNamedCityImage().getId() : null;
    }
}
