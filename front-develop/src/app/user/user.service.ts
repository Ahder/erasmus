import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user = new BehaviorSubject<User>({
    lastname: null,
    firstname: null,
    birthdate: null,
    email: null,
    role: null,
    password: null,
    id: null,
    spinneret: {
      id: null,
      label: null,
      level: null
    },
    token: null,
    urlSignature: null,
    enable: true,
    resetToken: null,
  });
  public currentUser = this.user.asObservable();

  constructor(private http: HttpClient) {
  }

  public setUser(user: User) {
    this.user.next(user);
  }

  public resetUser() {
    this.user.next({
      lastname: null,
      firstname: null,
      birthdate: null,
      email: null,
      role: null,
      password: null,
      id: null,
      spinneret: {
        id: null,
        label: null,
        level: null
      },
      token: null,
      urlSignature: null,
      enable: true,
      resetToken: null,
    });
  }

  createUser(user: User): Observable<object> {
    console.log('user dans service pour le post: ', user);
    return this.http.post(environment.apiUrl + '/api/user', user);
  }

  updateUser(user: User): Observable<object> {
    console.log('user dans service pour le put: ', user);
    return this.http.put(environment.apiUrl + '/api/user/' + user.id, user);
  }

  getUserReset(token: string) {
    console.log('url : ', environment.apiUrl + '/api/user/' + token);
    return this.http.get<User>(environment.apiUrl + '/api/user/' + token)
  }

  deleteUser(id: number) {
    console.log('delete id : ', id);
    return this.http.delete(environment.apiUrl + '/api/user/' + id);
  }
}
