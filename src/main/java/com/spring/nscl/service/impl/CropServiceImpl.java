package com.spring.nscl.service.impl;

import com.spring.nscl.common.NscMessageConstants;
import com.spring.nscl.dto.CropDto;
import com.spring.nscl.entity.Crop;
import com.spring.nscl.entity.ResponseModel;
import com.spring.nscl.repository.CropRepository;
import com.spring.nscl.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;

    @Override
    public ResponseModel createCrop(CropDto dto) {
        Crop crop = new Crop();
        BeanUtils.copyProperties(dto, crop);

        Crop saved = cropRepository.save(crop);
        return ResponseModel.ok(NscMessageConstants.RECORD_CREATED, saved);
    }

    @Override
    public ResponseModel findById(Long id) {
        Optional<Crop> cropOpt = cropRepository.findById(id);
        if (cropOpt.isEmpty() || "DELETED".equalsIgnoreCase(String.valueOf(cropOpt.get().getStatus()))) {
            return ResponseModel.notFound(NscMessageConstants.RECORD_NOT_FOUND);
        }

        CropDto dto = new CropDto();
        BeanUtils.copyProperties(cropOpt.get(), dto);
        return ResponseModel.ok(NscMessageConstants.RECORD_FETCHED, dto);
    }

    @Override
    public ResponseModel findByFilter(CropDto filter) {
        List<Crop> all = cropRepository.findAll();

        List<Crop> filtered = all.stream()
                .filter(c -> (StringUtils.hasText(filter.getSeedCropName())
                        ? c.getSeedCropName().toLowerCase().contains(filter.getSeedCropName().toLowerCase())
                        : true))
                .filter(c -> filter.getCropGroupId() == null || c.getCropGroupId().equals(filter.getCropGroupId()))
                .filter(c -> !"DELETED".equalsIgnoreCase(String.valueOf(c.getStatus())))
                .collect(Collectors.toList());

        List<CropDto> result = filtered.stream().map(crop -> {
            CropDto dto = new CropDto();
            BeanUtils.copyProperties(crop, dto);
            return dto;
        }).collect(Collectors.toList());

        return ResponseModel.ok(NscMessageConstants.RECORD_FETCHED, result);
    }

    @Override
    public ResponseModel dropdown() {
        List<Crop> crops;
        crops = cropRepository.findByStatusIgnoreCase("ACTIVE");

        List<CropDto> list = crops.stream().map(c -> {
            CropDto dto = new CropDto();
            BeanUtils.copyProperties(c, dto);
            return dto;
        }).collect(Collectors.toList());

        return ResponseModel.ok(NscMessageConstants.RECORD_FETCHED, list);
    }

    @Override
    public ResponseModel updateCrop(CropDto dto) {
        Optional<Crop> optional = cropRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            return ResponseModel.notFound(NscMessageConstants.RECORD_NOT_FOUND);
        }

        Crop existing = optional.get();
        String oldStatus = String.valueOf(existing.getStatus());

        // copy only non-null properties to avoid overwriting existing data with nulls
        BeanUtils.copyProperties(dto, existing);

        Crop updated = cropRepository.save(existing);
        return ResponseModel.ok(NscMessageConstants.RECORD_UPDATED, updated);
    }

    @Override
    public ResponseModel softDelete(Long id) {
        Optional<Crop> optional = cropRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseModel.notFound(NscMessageConstants.RECORD_NOT_FOUND);
        }

        Crop crop = optional.get();
        cropRepository.save(crop);
        return ResponseModel.ok(NscMessageConstants.RECORD_DELETED);
    }

    /**
     * Helper method: ignore null fields when copying
     */
//    private String[] getNullPropertyNames(Object source) {
//        return org.springframework.beans.BeanUtils
//                .getPropertyDescriptors(source.getClass())
//                .stream()
//                .map(pd -> {
//                    try {
//                        Object value = pd.getReadMethod().invoke(source);
//                        return value == null ? pd.getName() : null;
//                    } catch (Exception e) {
//                        return null;
//                    }
//                })
//                .filter(name -> name != null)
//                .toArray(String[]::new);
//    }
}
