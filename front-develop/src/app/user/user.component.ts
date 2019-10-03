import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {User} from './user.model';
import {UserService} from './user.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User;
  userForm: FormGroup;
  show = false;
  register = false;

  constructor(private formBuilder: FormBuilder, private router: Router, public userService: UserService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.show = this.router.url.split('/')[2] === undefined && this.router.url.split('/')[1] !== 'login';
    this.register = this.router.url.split('/')[1] === 'login';

    this.userService.currentUser.subscribe(user => this.user = user);

    this.userForm = this.formBuilder.group({
      lastname: [{
        value: this.user.lastname,
        disabled: this.show
      }, Validators.required],
      firstname: [{
        value: this.user.firstname,
        disabled: this.show
      }, Validators.required],
      birthdate: [{
        value: this.user.birthdate,
        disabled: this.show
      }, Validators.required],
      email: [{
        value: this.user.email,
        disabled: this.show
      }, [Validators.required, Validators.email]],
      spinneret: this.formBuilder.group({
        id: [this.user.spinneret.id], // c degeu un spinneret comme ça juste pour un string
        label: [{
          value: this.user.spinneret.label,
          disabled: this.show
        }, Validators.required],
        level: [{
          value: this.user.spinneret.level,
          disabled: this.show
        }, Validators.required]
      }),
        password: [{
          value: this.user.password,
          disabled: this.show
        }, [(this.user.id) ? Validators.nullValidator : Validators.required]],
        confirmPassword: [{
          value: this.user.password,
          disabled: this.show
        }, [(this.user.id) ? Validators.nullValidator : Validators.required]],
      role: [{
        value: this.user.role,
        disabled: this.show
      }, (this.user.id) ? Validators.nullValidator : Validators.required]
      }, {
      validator: this.MatchPassword
    })
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

  onSubmit() {
    if (!this.show) {
      this.router.navigateByUrl('/utilisateur');
    }
    this.user = Object.assign(this.user, this.userForm.value);
    if (this.user.id && !this.register) {
      this.userService.updateUser(this.user).subscribe( res => console.log(res), err => console.log(err));
    } else {
      this.userService.createUser(this.user).subscribe( res => {
        this.router.navigateByUrl('/');
      }, err => console.log(err));
    }

  }

  deleteUser() {
    this.userService.deleteUser(this.user.id).subscribe(res =>
    {
      console.log(res);
      this.snackbar.open('Utilisateur supprimé avec succès', 'OK', {
        duration: 5000
      })
      this.router.navigateByUrl('/utilisateur/list')
    }, err => console.log(err));
  }

}
