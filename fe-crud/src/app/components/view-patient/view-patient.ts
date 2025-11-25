import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {PatientService} from '../../patient.service';
import {Observable} from 'rxjs';
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
  page: Page<PatientPartialResponse> | undefined;

  constructor(private patientService: PatientService) { }

  ngOnInit() :void{
    this.patientService.getPatients().subscribe((data) =>{
        this.page = data
    })
  }

  deletePatient(patientId:string):void {
    this.patientService.deletePatient(patientId)
      .pipe().subscribe(
        result => {
          this.ngOnInit()
        }
    )

  }
}
