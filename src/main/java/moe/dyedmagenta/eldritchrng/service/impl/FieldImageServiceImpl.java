package moe.dyedmagenta.eldritchrng.service.impl;

import lombok.RequiredArgsConstructor;
import moe.dyedmagenta.eldritchrng.dto.FieldImageDTO;
import moe.dyedmagenta.eldritchrng.error.NotFoundException;
import moe.dyedmagenta.eldritchrng.error.ValidationException;
import moe.dyedmagenta.eldritchrng.model.FieldImage;
import moe.dyedmagenta.eldritchrng.repository.FieldImageRepository;
import moe.dyedmagenta.eldritchrng.service.FieldImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldImageServiceImpl implements FieldImageService {

    private final FieldImageRepository repository;

    @Override
    public Page<FieldImageDTO> findAllImages(Pageable pageable) {
        return repository.findAll(pageable).map(FieldImageDTO::new);
    }

    @Override
    public FieldImageDTO findImageById(UUID id) {
        return repository.findById(id)
                .map(FieldImageDTO::new)
                .orElseThrow(() -> new NotFoundException(String.format("Cannot find image with id %s", id)));
    }

    @Override
    public FieldImageDTO addNewImage(FieldImageDTO dto) {
        var image = new FieldImage();
        image.setName(dto.getName());
        image = repository.save(image);
        return new FieldImageDTO(image);
    }

    @Override
    public void updateImage(FieldImageDTO dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new ValidationException("FieldImage name is required");
        }
        var image = repository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Cannot find image with id %s", dto.getId())));
        image.setName(dto.getName());
    }

    @Override
    public void deleteImage(UUID id) {
        repository.deleteById(id);
    }
}
