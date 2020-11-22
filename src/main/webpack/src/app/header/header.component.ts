import { Component } from '@angular/core';
import { MedicalTestService } from "../medicaltest/medicaltest.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
    constructor(private medicalTestService : MedicalTestService) { }

    searchByUsernameOrPhoneNumber(searchCriteria : string) {
        this.medicalTestService.searchByUsernameOrPhoneNumber(searchCriteria);
    }
}
