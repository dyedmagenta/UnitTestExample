package moe.dyedmagenta.eldritchrng.controller;

import lombok.RequiredArgsConstructor;
import moe.dyedmagenta.eldritchrng.dto.FieldImageDTO;
import moe.dyedmagenta.eldritchrng.service.FieldImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/images", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImageController {

    private final FieldImageService service;

    @PostMapping
    public ResponseEntity<FieldImageDTO> addNewImage(FieldImageDTO dto) {
        return ResponseEntity.ok(service.addNewImage(dto));
    }

    @GetMapping
    public Page<FieldImageDTO> findAllImages(Pageable pageable) {
        return service.findAllImages(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldImageDTO> findImageById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findImageById(id));
    }

    @PutMapping
    public ResponseEntity<Void> updateImage(FieldImageDTO dto) {
        service.updateImage(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteField(UUID id) {
        service.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
