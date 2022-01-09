package com.gestion.exams.DTO;

import com.gestion.exams.entity.GradePK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GradeDTO {

    /*
    @NotNull
    int idExam;

    @NotNull
    int idStudent;
    */

    GradePK gradePK;

    double value;
}
