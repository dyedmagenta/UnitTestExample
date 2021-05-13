package moe.dyedmagenta.eldritchrng.service;

import moe.dyedmagenta.eldritchrng.dto.FieldImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FieldImageService {

    Page<FieldImageDTO> findAllImages(Pageable pageable);

    FieldImageDTO findImageById(UUID id);

    FieldImageDTO addNewImage(FieldImageDTO dto);

    void updateImage(FieldImageDTO dto);

    void deleteImage(UUID id);

}
