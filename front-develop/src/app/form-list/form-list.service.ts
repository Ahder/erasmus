import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormListService {

  constructor(private http: HttpClient) { }

  findAllForms(): Observable<object> {
    return this.http.get('https://learning-agreement-api.herokuapp.com/api/form ', { withCredentials: true });
  }
}
