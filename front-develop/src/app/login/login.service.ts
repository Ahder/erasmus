import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {User} from '../user/user.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router) { }

  public get getLoggedUser() {
    return JSON.parse(localStorage.getItem('loggedUser'));
  }
  
  isAuthenticated(): boolean {
    return this.getLoggedUser !== null;
  }

  async connection(email: string, password: string) {

    const body = new HttpParams()
      .set('username', email)
      .set('password', password);
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        observe: 'response'
      }),
      withCredentials: true
    };

    try {
      const user = await this.http.post(environment.apiUrl + '/api/login', body.toString(), options).toPromise();
      localStorage.setItem('loggedUser', JSON.stringify(user));
      return true;
    } catch (e) {
      console.log(e);
      return e;
    }
  }

    resetPassword(mail: string): Observable<User> {
      return this.http.post<User>(environment.apiUrl + '/api/user/passwordForgot', {email: mail})
    }

  deconnection(): void {

    this.http.get(environment.apiUrl + '/api/logout').subscribe( res => {
      console.log(res);
      localStorage.setItem('loggedUser', null);
      this.router.navigateByUrl('login');
    }, err => console.log(err));

  }
}
