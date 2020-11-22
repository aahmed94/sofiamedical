import {Component} from "@angular/core";
import {MedicalTestService} from "../medicaltest.service";
import {MedicalTestResource} from "../medicaltest.model";

@Component({
    selector: 'app-redo-medical-test',
    templateUrl: './redo-medicaltest.component.html',
    styleUrls: ['./redo-medicaltest.component.css']
})
export class RedoMedicalTestComponent {

    newMedicalTestResult:number = -1;

    constructor(private medicalTestService : MedicalTestService) {
    }

    getMedicalTestToUpdate() {

    }

    onSubmitPut(formData: { name: string; phoneNumber: string; email: string; age: number; dnaCode: string, symptoms: string}) {

        const patientDto = {name: formData.name, phoneNumber: formData.phoneNumber,
            email: formData.email, age: formData.age, dnaCode: formData.dnaCode};
        const medicalDto = {patientDto: patientDto, symptoms: formData.symptoms};

        //cthis.medicalTestService.putTest(medicalDto, this.currentMedicalTest.correlationId).then((value => this.newMedicalTestResult = value));
    }

    getResult() : MedicalTestResource[] {
        return this.medicalTestService.getMedicalTestInfo();
    }
}
