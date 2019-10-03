import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormModule} from './form/form.module';
import {UserModule} from './user/user.module';
import {MatIconModule, MatToolbarModule} from '@angular/material';
import {UserListModule} from './user-list/user-list.module';
import {MainModule} from './main/main.module';
import {LoginModule} from './login/login.module';
import {AuthGuardService} from './login/auth-guard.service';
import {LoginService} from './login/login.service';
import {FormListModule} from './form-list/form-list.module';
import {ResetPasswordModule} from './reset-password/reset-password.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormModule,
    UserModule,
    MatToolbarModule,
    MatIconModule,
    UserListModule,
    MainModule,
    LoginModule,
    FormListModule,
    ResetPasswordModule
  ],
  providers: [AuthGuardService, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
