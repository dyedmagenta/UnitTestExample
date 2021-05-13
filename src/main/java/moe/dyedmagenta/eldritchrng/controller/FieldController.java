package moe.dyedmagenta.eldritchrng.controller;

import lombok.RequiredArgsConstructor;
import moe.dyedmagenta.eldritchrng.dto.GenericFieldDTO;
import moe.dyedmagenta.eldritchrng.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/fields", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FieldController {


    private final FieldService service;

    @PostMapping
    public ResponseEntity<GenericFieldDTO> createField(@RequestBody GenericFieldDTO dto) {
        return ResponseEntity.ok(service.createField(dto));
    }

    @GetMapping
    public Page<GenericFieldDTO> getFields(Pageable pageable) {
        return service.getFields(pageable);
    }

    @GetMapping("/name")
    public Page<GenericFieldDTO> findFieldsByName(Pageable pageable, @RequestParam String name) {
        return service.findFieldsByName(pageable, name);
    }

    @PutMapping
    public ResponseEntity<Void> updateField(GenericFieldDTO dto) {
        service.updateField(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteField(UUID id) {
        service.deleteField(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/random")
    public ResponseEntity<GenericFieldDTO> getRandomField() {
        return ResponseEntity.ok(service.getRandomField());
    }

}
