import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Period } from 'src/app/model/period';
import { PeriodService } from 'src/app/service/period-service.service';

@Component({
  selector: 'app-period-details',
  templateUrl: './period-details.component.html',
  styleUrls: ['./period-details.component.css']
})
export class PeriodDetailsComponent implements OnInit {
  id: number = -1;
  period: Period = new Period();

  constructor(private route: ActivatedRoute, private periodService: PeriodService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.periodService.getPeriod(this.id).subscribe(data =>{
      this.period = data;
      this.periodService.getHttp().get('http://localhost:8080/periodList/' + this.period.id + "/beginDate", {responseType: 'text'})
              .subscribe(data=>{
                this.period.beginDatePeriod = data;
              });
      this.periodService.getHttp().get('http://localhost:8080/periodList/' + this.period.id + "/endDate", {responseType: 'text'})
              .subscribe(data =>{
                this.period.endDatePeriod = data;
              });
    });
  }

}
