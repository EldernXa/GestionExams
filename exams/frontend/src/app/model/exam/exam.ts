import {Ue} from "../ue/ue";

export class Exam {

        idExam: number = -1;
        idPeriod: number = -1;
        session?: number;
        //ue: string = "";
        ue : Ue = new Ue();
        supervisors: string = "";
        year : number = -1;
        nameRoom: string = "";
        beginDateExam: string = "";
        endDateExam: string = "";
        isFinish = false;


}
