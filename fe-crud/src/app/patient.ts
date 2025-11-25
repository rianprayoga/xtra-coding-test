export interface CreatePatient {
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  gender: string;
  phone: string;
  address: string;
  suburb: string;
  state: string;
  postcode: string;
}

export interface UpdatePatient {
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  gender: string;
  phone: string;
  address: string;
  suburb: string;
  state: string;
  postcode: string;
  version: number;
}

export interface PatientPartialResponse {
  pid:string;
  firstName: string;
  lastName: string;
  createdAt: string;
  updatedAt: string;
}

export interface PatientResponse {
  pid:string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  gender: string;
  phone: string;
  address: string;
  suburb: string;
  state: string;
  postcode: string;
  version: number;
  createdAt: string;
  updatedAt: string;
}

export interface Page<T> {
  data: T[];
  total: number
}
