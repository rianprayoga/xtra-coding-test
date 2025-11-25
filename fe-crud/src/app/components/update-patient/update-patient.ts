import { Component } from '@angular/core';
import {PatientService} from '../../patient.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-update-patient',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './update-patient.html',
  styleUrl: './update-patient.css',
})
export class UpdatePatient {

  patient: any
  newValue: any

  constructor(
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router) { }

  form = new FormGroup({
    firstName: new FormControl<string|undefined>(undefined),
    lastName: new FormControl<string|undefined>(undefined),
    dateOfBirth: new FormControl<string|undefined>(undefined),
    gender: new FormControl<string|undefined>(undefined),
    phone: new FormControl<string|undefined>(undefined),
    address:new FormControl<string|undefined>(undefined),
    suburb:new FormControl<string|undefined>(undefined),
    state: new FormControl<string|undefined>(undefined),
    postcode: new FormControl<string|undefined>(undefined),
  })

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.patientService.getPatient(id).subscribe(data => {
      this.patient = data
    })
  }

  submit(){
    this.newValue = this.form.value

    if (this.newValue.firstName != undefined){
      this.patient.firstName = this.newValue.firstName
    }

    if (this.newValue.lastName != undefined){
      this.patient.lastName = this.newValue.lastName
    }

    if (this.newValue.dateOfBirth != undefined){
      this.patient.dateOfBirth = this.newValue.dateOfBirth
    }

    if (this.newValue.gender != undefined){
      this.patient.gender = this.newValue.gender
    }

    if (this.newValue.phone != undefined){
      this.patient.phone = this.newValue.phone
    }

    if (this.newValue.address != undefined){
      this.patient.address = this.newValue.address
    }

    if (this.newValue.suburb != undefined){
      this.patient.suburb = this.newValue.suburb
    }

    if (this.newValue.state != undefined){
      this.patient.state = this.newValue.state
    }

    if (this.newValue.postcode != undefined){
      this.patient.postcode = this.newValue.postcode
    }

    const id = this.route.snapshot.params['id'];
    this.patientService.updatePatient(id, this.patient).subscribe(data => {
      this.router.navigate(['/']);
    })

  }



}
