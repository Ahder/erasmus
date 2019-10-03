import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {UserComponent} from './user/user.component';
import {FormComponent} from './form/form.component';
import {UserListComponent} from './user-list/user-list.component';
import {MainComponent} from './main/main.component';
import {LoginComponent} from './login/login.component';
import {AuthGuardService} from './login/auth-guard.service';
import {FormListComponent} from './form-list/form-list.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '', component: MainComponent, canActivate: [AuthGuardService]},
  { path: 'utilisateur', component: UserComponent, canActivate: [AuthGuardService]},
  { path: 'utilisateur/cu', component: UserComponent, canActivate: [AuthGuardService]},
  { path: 'utilisateur/list', component: UserListComponent, canActivate: [AuthGuardService]},
  { path: 'login', component: LoginComponent},
  {path: 'formulaire', component: FormComponent, canActivate: [AuthGuardService]},
  { path: 'formulaire/cu', component: FormComponent, canActivate: [AuthGuardService] },
  { path: 'formulaire/list', component: FormListComponent, canActivate: [AuthGuardService] },
  { path: 'resetPassword/:token', component: ResetPasswordComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
