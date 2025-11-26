import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {PatientService} from '../../patient.service';
import {PatientPartialResponse, Page} from '../../patient';

@Component({
  selector: 'app-view-patient',
  imports: [
    RouterLink,
  ],
  templateUrl: './view-patient.html',
  styleUrl: './view-patient.css',
})
export class ViewPatient {

  data: PatientPartialResponse[] = [];
  total: number = 0;

  constructor(private patientService: PatientService) { }

  ngOnInit() :void{
    this.patientService.getPatients().subscribe((response) =>{
      this.data = response.data;
      this.total = response.total;
    })
  }

  deletePatient(patientId:string):void {
    this.patientService.deletePatient(patientId)
      .pipe().subscribe(
        () => {
          this.ngOnInit()
        }
    )

  }
}
