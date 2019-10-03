import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {User} from '../user/user.model';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  user: User;
  passForm: FormGroup;

  constructor(private activatedRouter: ActivatedRoute, private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() {

    this.passForm = this.formBuilder.group({
      password: [{
        value: null
      }, [Validators.required]],
      confirmPassword: [{
        value: null
      }, [Validators.required]],
    }, {
      validator: this.MatchPassword
    })

    console.log('token : ', this.activatedRouter.snapshot.paramMap.get('token'));
    this.userService.getUserReset(this.activatedRouter.snapshot.paramMap.get('token')).subscribe( user => console.log(user), res => console.log(res));
  }

  MatchPassword(control: AbstractControl) {
    const password = control.get('password').value;

    const confirmPassword = control.get('confirmPassword').value;

    if(password !== confirmPassword) {
      control.get('confirmPassword').setErrors( {ConfirmPassword: true} );
    } else {
      return null;
    }
  }

  submit() {
      this.user.password = this.passForm.value.password;
      console.log(this.user);
  }

}
