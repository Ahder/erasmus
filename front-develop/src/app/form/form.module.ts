import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormComponent } from './form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatCardModule,
  MatDividerModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule
} from '@angular/material';
import {RouterModule} from '@angular/router';
import {CanvasModule} from '../canvas/canvas.module';

@NgModule({
  declarations: [FormComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    RouterModule,
    CanvasModule,
    MatIconModule,
    MatDividerModule,
    MatGridListModule
  ]
})
export class FormModule { }
