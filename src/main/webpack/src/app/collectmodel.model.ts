import {MedicalTestResource} from "./medicaltest/medicaltest.model";

export interface CollectModel {
    links: object;
    content: MedicalTestResource[];
}
