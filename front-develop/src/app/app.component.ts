import {Component, OnInit} from '@angular/core';
import {User} from './user/user.model';
import {ActivatedRoute, Router} from '@angular/router';
import {FormService} from './form/form.service';
import {LoginService} from './login/login.service';
import {UserService} from './user/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  loggedUser: User;
  constructor (private router: Router, public formService: FormService, public loginService: LoginService, public userService: UserService) {}

  ngOnInit(): void {
    this.router.events.subscribe(value => {
        this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
    });
  }

}
