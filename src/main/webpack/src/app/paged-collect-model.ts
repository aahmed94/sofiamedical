import {MedicalTestResource} from "./medicaltest/medicaltest.model";

export interface PagedCollectModel {
    links: object;
    content: MedicalTestResource[];
    page: object;
}
