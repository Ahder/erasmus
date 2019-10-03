import { Component, OnInit } from '@angular/core';
import {User} from '../user/user.model';
import {LoginService} from '../login/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  loggedUser: User = this.loginService.getLoggedUser;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
    console.log(this.loggedUser);
  }

}
