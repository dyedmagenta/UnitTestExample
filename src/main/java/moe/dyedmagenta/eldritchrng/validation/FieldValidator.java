package moe.dyedmagenta.eldritchrng.validation;

import lombok.RequiredArgsConstructor;
import moe.dyedmagenta.eldritchrng.dto.FieldType;
import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO;
import moe.dyedmagenta.eldritchrng.error.NotFoundException;
import moe.dyedmagenta.eldritchrng.error.ValidationException;
import moe.dyedmagenta.eldritchrng.repository.FieldImageRepository;
import moe.dyedmagenta.eldritchrng.repository.FieldRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FieldValidator {

    private final FieldRepository fieldRepository;
    private final FieldImageRepository imageRepository;

    public void validateFieldCreate(GenericFieldDTO fieldDTO) {
        if (fieldDTO.getId() != null) {
            throw new ValidationException("Cannot create new Field with given id");
        }
        validateField(fieldDTO);
    }

    public void validateFieldUpdate(GenericFieldDTO fieldDTO) {
        if (fieldDTO.getId() != null && fieldRepository.existsById(fieldDTO.getId())) {
            throw new NotFoundException(String.format("Field with id{%s} not found", fieldDTO.getId()));
        }
        validateField(fieldDTO);
    }

    private void validateField(GenericFieldDTO fieldDTO) {
        if (StringUtils.isEmpty(fieldDTO.getName())) {
            throw new ValidationException("Field name is required");
        }
        var fieldType = fieldDTO.getFieldType();
        if (fieldType == null) {
            throw new ValidationException("Field type is required");
        }
        if (isNotNamedCity(fieldDTO) && fieldDTO.getCityImageId() != null) {
            throw new ValidationException("Regular Fields cannot have city image");
        }

        var iconImageId = fieldDTO.getIconImageId();
        validateImageExists(iconImageId);

        var cityImageId = fieldDTO.getIconImageId();
        validateImageExists(cityImageId);
    }

    private void validateImageExists(UUID imageId) {
        if (imageId != null && imageRepository.existsById(imageId)) {
            throw new NotFoundException(String.format("Image with id{%s} not found", imageId));
        }
    }

    private boolean isNotNamedCity(GenericFieldDTO fieldDTO) {
        return !FieldType.NAMED_CITY.equals(fieldDTO.getFieldType());
    }
}
