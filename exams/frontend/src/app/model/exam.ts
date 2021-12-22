import { Period } from "./period";

export class Exam {

        idExam: number = -1;
        period: Period = new Period();
        session: number = 0;
        ue: string = "";
        supervisors: string[] = [];
        year: number = 0;

}
