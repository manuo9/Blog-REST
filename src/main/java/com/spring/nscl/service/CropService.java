package com.spring.nscl.service;

import com.spring.nscl.dto.CropDto;
import com.spring.nscl.entity.ResponseModel;

public interface CropService {
      ResponseModel createCrop(CropDto dto);

      ResponseModel findById(Long id);

      ResponseModel findByFilter(CropDto filter);
      ResponseModel findByNameAndStatus(CropDto filter);

      ResponseModel dropdown();

      ResponseModel updateCrop(CropDto dto);

      ResponseModel softDelete(Long id);
}
