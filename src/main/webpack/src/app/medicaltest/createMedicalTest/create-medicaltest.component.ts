import {Component} from "@angular/core";
import {MedicalTestService} from "../medicaltest.service";
import {MedicalTestResource} from "../medicaltest.model";

@Component({
    selector: 'app-create-medical-test',
    templateUrl: './create-medicaltest.component.html',
    styleUrls: ['./create-medicaltest.component.css']
})
export class CreateMedicalTestComponent {

    newMedicalTestResult: number = -1;

    constructor(private medicalTestService : MedicalTestService) {
    }

    onSubmitPost(formData: { name: string; phoneNumber: string; email: string; age: number; dnaCode: string, symptoms: string}) {

        const patientDto = {name: formData.name, phoneNumber: formData.phoneNumber,
            email: formData.email, age: formData.age, dnaCode: formData.dnaCode};
        const medicalDto = {patientDto: patientDto, symptoms: formData.symptoms};

        this.medicalTestService.getNewTestResult(medicalDto).then((value => this.newMedicalTestResult = value));
    }

    getResult() : MedicalTestResource[] {
        return this.medicalTestService.getMedicalTestInfo();
    }
}
