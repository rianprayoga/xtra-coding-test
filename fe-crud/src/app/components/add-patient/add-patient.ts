import { Component } from '@angular/core';
import {PatientService} from '../../patient.service';
import {Router, RouterLink} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-patient',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-patient.html',
  styleUrl: './add-patient.css',
})
export class AddPatient {

  request: any

  constructor(private patientService : PatientService, private router : Router) {
  }

  form = new FormGroup({
    firstName: new FormControl<string>(''),
    lastName: new FormControl<string>(''),
    dateOfBirth: new FormControl<string>(''),
    gender: new FormControl<string>(''),
    phone: new FormControl<string>(''),
    address:new FormControl<string>(''),
    suburb: new FormControl<string>(''),
    state: new FormControl<string>(''),
    postcode: new FormControl<string>(''),
  })

  addPatient():void {

    this.request = this.form.value

    this.patientService.addPatient(this.request!).subscribe(data => {
      this.router.navigate(['/']);
    })
  }

}
