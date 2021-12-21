export class Period {

    id: number;
    name: string;
    beginDatePeriod: string;
    endDatePeriod: string;

    constructor(id:number, name: string,
        beginDatePeriod:string, endDatePeriod:string){
        this.id = id;
        this.name = name;
        this.beginDatePeriod = beginDatePeriod;
        this.endDatePeriod = endDatePeriod;
    }

}
