import {Grade} from "../grade/grade";
import {Inscription} from "../inscription/inscription";

export class Student {

  /*
  idStudent: number = -1;
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  grade?: number;
   */

  idStudent: number = -1;
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  grades: Grade[] = [];
  inscriptions: Inscription[] = [];
  //grade?: number;
}
