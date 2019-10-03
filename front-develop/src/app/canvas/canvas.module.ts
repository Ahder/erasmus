import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CanvasComponent } from './canvas.component';
import {MatButtonModule} from '@angular/material';

@NgModule({
  declarations: [CanvasComponent],
  imports: [
    CommonModule,
    MatButtonModule
  ],
  exports: [CanvasComponent]
})
export class CanvasModule { }
