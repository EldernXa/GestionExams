package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.GradeDTO;
import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.Grade;
import com.gestion.exams.entity.UE;
import org.modelmapper.ModelMapper;

public class UeMapper {

    private static final ModelMapper modelMapper = new ModelMapper() ;

    public static UE UeDTOToUe(UeDTO ueDTO) {
        UE ue = modelMapper.map(ueDTO, UE.class);
        System.out.println("mapper :" + ue.toString());
        return ue;
    }

    public static UeDTO ueToUeDTO(UE ue){
        UeDTO ueDTO = modelMapper.map(ue, UeDTO.class);
        return ueDTO;
    }
}
