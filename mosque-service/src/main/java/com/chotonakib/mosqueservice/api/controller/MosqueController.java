package com.chotonakib.mosqueservice.api.controller;

import com.chotonakib.mosqueservice.api.dto.mapper.MosqueDetailsEntityDtoMapper;
import com.chotonakib.mosqueservice.api.dto.request.AddMosqueRequestDTo;
import com.chotonakib.mosqueservice.api.dto.request.GetMosquesRequest;
import com.chotonakib.mosqueservice.api.dto.request.UpdateMosqueRequestDto;
import com.chotonakib.mosqueservice.api.dto.response.MosqueDetailsEntityDto;
import com.chotonakib.mosqueservice.exception.AlreadyMosqueExistsException;
import com.chotonakib.mosqueservice.exception.MosqueNotFoundException;
import com.chotonakib.mosqueservice.exception.UserDoesNotExistException;
import com.chotonakib.mosqueservice.models.entity.MosqueDetailsEntity;
import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import com.chotonakib.mosqueservice.services.MosqueService;
import com.chotonakib.mosqueservice.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mosque")
@RequiredArgsConstructor
public class MosqueController {
    private final MosqueService mosqueService;
    private final MosqueDetailsEntityDtoMapper mosqueDetailsEntityDtoMapper;
    private final UserService userService;

    @PostMapping("/allMosques")
    @ResponseStatus(HttpStatus.OK)
    public List<MosqueDetailsEntityDto> findAllMosquesWithInRange(@RequestBody @Valid GetMosquesRequest request) {
        List<MosqueDetailsEntity> availableMosques = mosqueService.getAllMosques(request.getLatitude(), request.getLongitude(), request.getRadius());
        return availableMosques.stream().map(mosqueDetailsEntityDtoMapper::toDto).toList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findSingleMosque(@PathVariable @NotNull String name) {
        MosqueDetailsEntity mosque;
        try {
            mosque = mosqueService.getSingleMosque(name);
        } catch (MosqueNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body(mosqueDetailsEntityDtoMapper.toDto(mosque));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addMosque(@RequestBody @Valid AddMosqueRequestDTo addMosqueRequestDTo) throws UserDoesNotExistException, AlreadyMosqueExistsException {
        UserInfoEntity userInfoEntity = userService.findUser(addMosqueRequestDTo.getContributorEmail());
        MosqueDetailsEntity mosqueDetails = mosqueDetailsEntityDtoMapper.toEntity(addMosqueRequestDTo, userInfoEntity);
        // TODO: if we get range error then we need to give the facility to force add the mosque
        mosqueService.save(mosqueDetails);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateMosque(@RequestBody @Valid UpdateMosqueRequestDto updateMosqueRequestDto) throws MosqueNotFoundException {
        mosqueService.updateMosque(updateMosqueRequestDto);
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMosque(@PathVariable @NotNull String name) throws MosqueNotFoundException {
        mosqueService.deleteMosque(name);
    }
}
