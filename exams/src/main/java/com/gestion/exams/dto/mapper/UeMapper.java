package com.gestion.exams.dto.mapper;

import com.gestion.exams.dto.UeDTO;
import com.gestion.exams.entity.UE;
import org.modelmapper.ModelMapper;

public class UeMapper {

    private static final ModelMapper modelMapper = new ModelMapper() ;

    private UeMapper(){}

    public static UE ueDTOToUe(UeDTO ueDTO) {
        return modelMapper.map(ueDTO, UE.class);
    }

    public static UeDTO ueToUeDTO(UE ue){
        return modelMapper.map(ue, UeDTO.class);
    }
}
