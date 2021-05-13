package moe.dyedmagenta.eldritchrng.repository;

import moe.dyedmagenta.eldritchrng.model.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {

    Page<Field> findAllByNameContainingIgnoreCase(Pageable pageable, String name);

}
