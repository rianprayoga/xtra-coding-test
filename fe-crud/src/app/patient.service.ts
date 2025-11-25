import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CreatePatient, Page, PatientPartialResponse, PatientResponse, UpdatePatient} from './patient';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PatientService {

  private baseUrl: string = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  addPatient(patient:CreatePatient) :Observable<PatientResponse> {
    return this.http.post<PatientResponse>(`${this.baseUrl}/v1/patients`, patient)
  }

  getPatient(id:string): Observable<PatientResponse> {
    return this.http.get<PatientResponse>(`${this.baseUrl}/v1/patients/${id}`)
  }

  updatePatient(id:string, patient:UpdatePatient): Observable<PatientResponse>{
    return this.http.put<PatientResponse>(`${this.baseUrl}/v1/patients/${id}`, patient)
  }

  deletePatient(id:string): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/v1/patients/${id}`)
  }

  getPatients(
    params?: URLSearchParams) :Observable<Page<PatientPartialResponse>> {

    let queryParams: string|undefined
    if (params != null) {
      queryParams = params.toString();
    }
    return this.http.get<Page<PatientPartialResponse>>(`${this.baseUrl}/v1/patients?${queryParams}`)
  }
}
