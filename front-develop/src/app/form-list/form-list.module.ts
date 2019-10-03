import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormListComponent } from './form-list.component';
import {MatTableModule} from '@angular/material';

@NgModule({
  declarations: [FormListComponent],
  imports: [
    CommonModule,
    MatTableModule
  ]
})
export class FormListModule { }
