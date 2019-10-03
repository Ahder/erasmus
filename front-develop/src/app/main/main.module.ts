import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import {MatCardModule, MatGridListModule, MatIconModule} from '@angular/material';
import {LoginService} from '../login/login.service';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [MainComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatGridListModule,
    RouterModule
    ],
  providers: [LoginService]
})
export class MainModule { }
