import { Component, OnInit } from '@angular/core';
import { MedicalTestResource } from "../medicaltest.model";
import { MedicalTestService } from "../medicaltest.service";
import {MatButton} from "@angular/material/button";

@Component({
    selector: 'app-all-medical-test',
    templateUrl: '../list-medicaltest.component.html',
    styleUrls: ['./all-medicaltest.component.css']
})
export class AllMedicalTestComponent implements OnInit {
    constructor(protected medicalTestService: MedicalTestService) { }
    private seeMore = "See more";
    private seeLess = "See less";

    ngOnInit(): void {
        this.medicalTestService.getAllMedicalTests();
    }

    buildDateString(s : string): string {
        return this.medicalTestService.buildDateString(s);
    }

    getResult(): MedicalTestResource[] {
        return this.medicalTestService.getMedicalTestInfo();
    }

    infoToggle(hiddenDiv: any, component: MatButton) {
        if (component._elementRef.nativeElement.firstChild.innerHTML === this.seeMore) {
            hiddenDiv.hidden = false;
            component._elementRef.nativeElement.firstChild.innerHTML = this.seeLess;
        } else {
            hiddenDiv.hidden = true;
            component._elementRef.nativeElement.firstChild.innerHTML = this.seeMore;
        }
    }
}
