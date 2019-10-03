import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from './login.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  forgotMail: FormGroup;
  mailIsForgotten: boolean;

  constructor(private router: Router, private formBuilder: FormBuilder, private loginService: LoginService, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    console.log(this.loginService.getLoggedUser);

    if (this.loginService.getLoggedUser) {
      console.log('User logged');
      this.router.navigateByUrl('');
      return;
    }

    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.forgotMail = this.formBuilder.group({
      mail: ['', [Validators.required, Validators.email]],
    });
  }

  async onSubmit(): Promise<void> {

    try {
      const conn = await this.loginService.connection(this.loginForm.value.email, this.loginForm.value.password);
      if (conn !== true) {
        console.log('try : ', conn);
        this.snackBar.open('Erreur n°' + conn.status + ' - Veuillez contacter l\'administrateur', 'Ok', {
          duration: 8000
        });
        return
      }
      this.router.navigateByUrl('');
    }
    catch (e) {
      console.log(e);
    }
  }

  async submitMail(): Promise<void> {

    this.loginService.resetPassword(this.forgotMail.value.mail).subscribe( res => {
      console.log(res);
    }, err => console.log(err))
  }

}
