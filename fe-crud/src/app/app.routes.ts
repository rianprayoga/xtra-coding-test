import { Routes } from '@angular/router';
import {ViewPatient} from './components/view-patient/view-patient';
import {AddPatient} from './components/add-patient/add-patient';
import {UpdatePatient} from './components/update-patient/update-patient';

export const routes: Routes = [
  { path: '', component: ViewPatient },
  { path: 'add', component: AddPatient },
  { path: 'update/:id', component: UpdatePatient },
];
