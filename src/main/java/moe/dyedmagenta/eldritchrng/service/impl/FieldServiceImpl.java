package moe.dyedmagenta.eldritchrng.service.impl;

import lombok.RequiredArgsConstructor;
import moe.dyedmagenta.eldritchrng.dto.FieldType;
import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO;
import moe.dyedmagenta.eldritchrng.error.NotFoundException;
import moe.dyedmagenta.eldritchrng.error.ValidationException;
import moe.dyedmagenta.eldritchrng.model.Field;
import moe.dyedmagenta.eldritchrng.model.FieldImage;
import moe.dyedmagenta.eldritchrng.repository.FieldImageRepository;
import moe.dyedmagenta.eldritchrng.repository.FieldRepository;
import moe.dyedmagenta.eldritchrng.service.FieldService;
import moe.dyedmagenta.eldritchrng.validation.FieldValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository repository;
    private final FieldImageRepository imageRepository;
    private final FieldValidator fieldValidator;

    @Override
    public GenericFieldDTO createField(GenericFieldDTO dto) {
        fieldValidator.validateFieldCreate(dto);
        var field = new Field();
        updateField(field, dto.getName(), dto.getFieldType(), dto.getIconImageId(), dto.getCityImageId());
        field = repository.save(field);
        return new GenericFieldDTO(field);
    }

    private void updateField(Field field, String name, FieldType fieldType, UUID iconImageId, UUID cityImageId) {
        field.setName(name);
        field.setType(fieldType);
        field.setIconImage(getImage(iconImageId));
        field.setNamedCityImage(getImage(cityImageId));
    }

    private FieldImage getImage(UUID imageId) {
        return imageId != null ? imageRepository.getById(imageId) : null;
    }

    @Override
    public Page<GenericFieldDTO> getFields(Pageable pageable) {
        return repository.findAll(pageable).map(GenericFieldDTO::new);
    }

    @Override
    public Page<GenericFieldDTO> findFieldsByName(Pageable pageable, String name) {
        return repository.findAllByNameContainingIgnoreCase(pageable, name).map(GenericFieldDTO::new);
    }

    @Override
    public void updateField(GenericFieldDTO dto) {
        fieldValidator.validateFieldUpdate(dto);
        var field = repository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Field with id{%s} not found", dto.getId())));
        updateField(field, dto.getName(), dto.getFieldType(), dto.getIconImageId(), dto.getCityImageId());
    }

    @Override
    public void deleteField(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public GenericFieldDTO getRandomField() {
        long fieldsCount = repository.count();
        int idx = (int)(Math.random() * fieldsCount);

        var fieldPage = repository.findAll(PageRequest.of(idx, 1)).map(GenericFieldDTO::new);
        if (fieldPage.hasContent()) {
            return fieldPage.getContent().get(0);
        }
        throw new RuntimeException("Couldn't find random field");
    }
}
