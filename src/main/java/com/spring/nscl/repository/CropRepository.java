package com.spring.nscl.repository;

import com.spring.nscl.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByStatusIgnoreCase(String status);

    List<Crop> findBySeedCropNameContainingIgnoreCaseAndStatusNot(String seedCropName, String status);
}


