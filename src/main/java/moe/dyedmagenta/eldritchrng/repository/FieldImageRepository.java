package moe.dyedmagenta.eldritchrng.repository;

import moe.dyedmagenta.eldritchrng.model.FieldImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FieldImageRepository extends JpaRepository<FieldImage, UUID> {

}
