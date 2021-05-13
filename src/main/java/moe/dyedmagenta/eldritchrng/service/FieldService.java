package moe.dyedmagenta.eldritchrng.service;

import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FieldService {

    GenericFieldDTO createField(GenericFieldDTO dto);

    Page<GenericFieldDTO> getFields(Pageable pageable);

    Page<GenericFieldDTO> findFieldsByName(Pageable pageable, String name);

    void updateField(GenericFieldDTO dto);

    void deleteField(UUID id);

    GenericFieldDTO getRandomField();
}
