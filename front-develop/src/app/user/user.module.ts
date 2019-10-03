import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  MatInputModule,
  MatFormFieldModule,
  MatCardModule,
  MatButtonModule,
  MatGridListModule,
  MatDividerModule,
  MatSelectModule
} from '@angular/material';
import {RouterModule} from '@angular/router';
import {UserService} from './user.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [UserComponent],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    RouterModule,
    MatGridListModule,
    MatDividerModule,
    MatSelectModule,
    HttpClientModule
  ],
  providers: [UserService],
  exports: [UserComponent]
})
export class UserModule { }
