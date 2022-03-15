import {Student} from "../student/student";
import {Exam} from "../exam/exam";
import {Ue} from "../ue/ue";

export class Grade {

  //exam: Exam = new Exam();
    idExam: number = -1;
    year: number = -1;
    session: number = -1;
    ue_name: string = "";
  //student: Student = new Student();
    idStudent: number = -1;
    firstName: string = "";
    lastName: string = "";
  value: number = -1;

}
