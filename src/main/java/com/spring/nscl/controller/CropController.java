package com.spring.nscl.controller;

import com.spring.nscl.dto.CropDto;
import com.spring.nscl.entity.ResponseModel;
import com.spring.nscl.service.CropService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CROP_MASTER")
public class CropController {

    private final CropService cropService;
    private static final Logger log = LoggerFactory.getLogger(CropController.class);

    public CropController(CropService cropService){
        this.cropService =  cropService;
    }

    // ✅ 1. Create Crop
    // I want to test the git functionality
    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createCrop(@RequestBody CropDto dto) {
        try {
            ResponseModel response = cropService.createCrop(dto);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error creating crop: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    // ✅ 2. Find by ID
    @PostMapping("/findById")
    public ResponseEntity<ResponseModel> findById(@RequestParam Long id) {
        try {
            ResponseModel response = cropService.findById(id);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error finding crop: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    // ✅ 3. Find by Filter
    @PostMapping("/findByFilter")
    public ResponseEntity<ResponseModel> findByFilter(@RequestBody CropDto filter) {
        try {
            ResponseModel response = cropService.findByFilter(filter);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error filtering crops: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    @PostMapping("/filterByNameAndStatus")
    public  ResponseEntity <ResponseModel> findByNameAndStatus(@RequestBody CropDto filter ) {

        try {
            ResponseModel res = cropService.findByNameAndStatus(filter);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            log.error("Error filtering crops: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    // ✅ 4. Dropdown (active crops only)
    @PostMapping("/dd")
    public ResponseEntity<ResponseModel> dropdown() {
        try {
            ResponseModel response = cropService.dropdown();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error fetching dropdown list: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    // ✅ 5. Update Crop
    @PostMapping("/update")
    public ResponseEntity<ResponseModel> updateCrop(@RequestBody CropDto dto) {
        try {
            ResponseModel response = cropService.updateCrop(dto);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error updating crop: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }

    // ✅ 6. Soft Delete Crop
    @PostMapping("/delete")
    public ResponseEntity<ResponseModel> softDelete(@RequestParam Long id) {
        try {
            ResponseModel response = cropService.softDelete(id);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error deleting crop: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseModel.error(ex.getMessage()));
        }
    }
    }

