import {Student} from "../student/student";
import {Exam} from "../exam/exam";
import {Ue} from "../ue/ue";

export class Grade {

  exam: Exam = new Exam();
  student: Student = new Student();
  value: number = 0;

}
