package moe.dyedmagenta.eldritchrng.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import moe.dyedmagenta.eldritchrng.model.FieldImage;

import java.util.UUID;

@Data
@NoArgsConstructor
public class FieldImageDTO {

    private UUID id;
    private String name;

    public FieldImageDTO(FieldImage fieldImage) {
        this.id = fieldImage.getId();
        this.name = fieldImage.getName();
    }
}
