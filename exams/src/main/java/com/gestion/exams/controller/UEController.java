package com.gestion.exams.controller;

import com.gestion.exams.DTO.UeDTO;
import com.gestion.exams.entity.UE;
import com.gestion.exams.services.UEService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ue")
@CrossOrigin(origins = "http://localhost:4200")
public class UEController {

    @Autowired
    UEService ueService;

    @GetMapping("/allUE")
    public List<UeDTO> getAllUE(){
        ModelMapper modelMapper = new ModelMapper();
        List<UE> listUe = ueService.getAllUE();
        return listUe.stream().map(ue -> modelMapper.map(ue, UeDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public UeDTO getUeByName(@PathVariable String name){
        ModelMapper modelMapper = new ModelMapper();
        UE ue = ueService.getUeByName(name);
        return modelMapper.map(ue,UeDTO.class);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteUe(@PathVariable String name){
        ueService.deleteUE(name);
    }

    @PostMapping("/add")
    public UeDTO addNewUe(@RequestBody UE ue){
        ModelMapper modelMapper = new ModelMapper();
        ueService.createUE(ue);
        return modelMapper.map(ue, UeDTO.class);
    }

    @Transactional
    @PutMapping("/update/{name}")
    public UeDTO updateUe(@PathVariable String name, @RequestBody UE ue){
        ModelMapper modelMapper = new ModelMapper();
        UE ueToBeUpdated = ueService.getUeByName(name);
        ueService.updateUE(ue, name);
        return modelMapper.map(ue,UeDTO.class);
    }
}
