import { Patient } from 'src/app/patient/patient.model';

export interface MedicalTestResource {
    patientResource: Patient;
    correlationId : string;
    testDate: string;
    testResult: number;
    symptoms: string;
    links: [];
}
