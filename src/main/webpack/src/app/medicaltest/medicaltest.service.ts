import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { CollectModel } from 'src/app/collectmodel.model';
import { MedicalTestResource } from "../medicaltest/medicaltest.model";
import {Injectable} from "@angular/core";
import {PagedCollectModel} from "../paged-collect-model";
import {observable, Observable} from "rxjs";
import {subscribeToPromise} from "rxjs/internal-compatibility";
import {tap} from "rxjs/operators";

@Injectable({
    providedIn: 'root'
})
export class MedicalTestService {
    private result: MedicalTestResource[] = [];

    constructor(private http: HttpClient) { }

    getAllMedicalTests(): MedicalTestResource[] {
        this.result = [];

        this.http.get<CollectModel>('http://localhost:8080/medical-test')
            .subscribe((responseData)=> {
                for (const element in responseData.content) {
                    this.result.push(responseData.content[element]);
                }
            });

        return this.result;
    }



    searchByUsernameOrPhoneNumber(searchCriteria : string): MedicalTestResource[] {
        this.result = [];

        let phoneNumber = parseInt(searchCriteria);

        if (isNaN(phoneNumber)) {
            this.result = this.getMedicalTestByPatientName(searchCriteria);
        } else {
            this.result = this.getMedicalTestByPhoneNumber(searchCriteria);
        }

        return this.result;
    }

    buildDateString(dateString: string) : string {
        return new Date(dateString).toLocaleString();
    }

    getMedicalTestInfo(): MedicalTestResource[] {
        return this.result;
    }

    // async putTest(medicalTestDto:any, correlationId: string): Promise<number> {
    //     let resultData = await this.getPutData(medicalTestDto, correlationId);
    //     return resultData;
    // }

    async getNewTestResult(medicalTestDto: any): Promise<number> {
        let resultData = await this.getDataPost(medicalTestDto);
        return resultData;
    }

    async getDataPost(medicalTestDto: any) : Promise<number> {
        return this.http.post<number>('http://localhost:8080/medical-test/create', medicalTestDto).toPromise();
    }

    // async getPutData(medicalTestDto: any, correlationId: string) : Promise<number> {
    //     return this.http.put<number>(`http://localhost:8080/medical-test/${correlationId}/redo`, medicalTestDto).toPromise();
    // }

     private getMedicalTestByPhoneNumber(phoneNumber : string): MedicalTestResource[] {
        this.result = [];

        this.http.get<PagedCollectModel>(`http://localhost:8080/medical-test/${phoneNumber}/get-by-number`,
            {params: new HttpParams().set('unpaged', String(true))})
            .subscribe(responseData => {
                for (const element in responseData.content) {
                    this.result.push(responseData.content[element]);
                }
            });

        return this.result;
    }

    private getMedicalTestByPatientName(patientName : string): MedicalTestResource[] {
        this.result = [];

        this.http.get<PagedCollectModel>(`http://localhost:8080/medical-test/${patientName}/get-by-name`,
            {params: new HttpParams().set('unpaged', String(true))})
            .subscribe(responseData => {
                for (const element in responseData.content) {
                    this.result.push(responseData.content[element]);
                }
            });

        return this.result;
    }
}
