import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserListService {

  constructor(private http: HttpClient) {}

  findAllUsers(): Observable<object> {
    return this.http.get(environment.apiUrl + '/api/user', { withCredentials: true });
  }
}
