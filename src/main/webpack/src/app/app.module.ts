import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllMedicalTestComponent } from './medicaltest/allMedicalTests/all-medicaltest.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { MatGridListModule } from "@angular/material/grid-list";
import { RouterModule, Routes } from "@angular/router";
import { CreateMedicalTestComponent} from "./medicaltest/createMedicalTest/create-medicaltest.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {RedoMedicalTestComponent} from "./medicaltest/redoMedicalTest/redo-medicaltest.component";

const appRoutes: Routes = [
    {path: '', component: AllMedicalTestComponent},
    {path: 'create-new-test', component: CreateMedicalTestComponent}
    ];

@NgModule({
  declarations: [
    AppComponent,
    AllMedicalTestComponent,
    HeaderComponent,
    CreateMedicalTestComponent,
    RedoMedicalTestComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatButtonModule,
        HttpClientModule,
        MatGridListModule,
        RouterModule.forRoot(appRoutes),
        FormsModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
