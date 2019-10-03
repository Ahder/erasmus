import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Form} from './form.model';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FormService {
  public form = new BehaviorSubject<Form>({
    id: null,
    schoolYearStart: null,
    schoolYearEnd: null,
    studyDomain: null,
    semester: null,
    student: {
      birthdate: null,
      email: null,
      firstname: null,
      lastname: null,
      id: null,
      password: null,
      role: null,
      spinneret: null,
      token: null,
      urlSignature: null,
      enable: null,
      resetToken: null
    },
    spinneret: null,
    hostEstablishment: null,
    country: null,
    courses: null,
    signatureStudent: null,
    signatureDateStudent: null,
    signatureRpiHome: null,
    signatureDateRpiHome: null,
    signatureRpiHost: null,
    signatureDateRpiHost: null,
    closed: false,
    createdAt: null,
    lastModified: null
  });
  public currentForm = this.form.asObservable();

  constructor(private http: HttpClient) {
  }

  public setForm(form: Form) {
    this.form.next(form);
  }

  public resetForm() {
      console.dir('resetForm')
      const tmpForm: Form = {
        id: null,
      schoolYearStart: null,
      schoolYearEnd: null,
      studyDomain: null,
      semester: null,
      student: {
        birthdate: null,
        email: null,
        firstname: null,
        lastname: null,
        id: null,
        password: null,
        role: null,
        spinneret: null,
        token: null,
        urlSignature: null,
        enable: null,
        resetToken: null
      },
      spinneret: null,
      hostEstablishment: null,
      country: null,
      courses: null,
        signatureStudent: null,
        signatureDateStudent: null,
        signatureRpiHome: null,
        signatureDateRpiHome: null,
        signatureRpiHost: null,
        signatureDateRpiHost: null,
        closed: null,
        createdAt: null,
        lastModified: null
    };
    console.dir(tmpForm);
    this.form.next(tmpForm);
  }

  createForm(form: Form, signatureStudent: string, signatureOrigin: string, signatureHost: string): Observable<object> {
    form.signatureStudent = signatureStudent;
    form.signatureRpiHome = signatureOrigin;
    form.signatureRpiHost = signatureHost;
    form.student =  JSON.parse(localStorage.getItem('loggedUser'));
    form.closed = false;
    form.createdAt = new Date();
    form.lastModified = new Date();
    console.log('form dans service pour le post: ');
    console.dir(form);
    return this.http.post(environment.apiUrl + '/api/form', form);
  }

  updateForm(id: number, form: Form, signatureStudent: string, signatureOrigin: string, signatureHost: string): Observable<object> {
    form.signatureStudent = signatureStudent;
    form.signatureRpiHome = signatureOrigin;
    form.signatureRpiHost = signatureHost;
    form.student =  JSON.parse(localStorage.getItem('loggedUser'));
    form.closed = false;
    form.createdAt = new Date();
    form.lastModified = new Date();
    console.log('form dans service pour le post: ');
    console.dir(form);
    return this.http.put(environment.apiUrl + '/api/form/' + id, form);
  }

  deleteForm(id: number) {
    console.log('delete id : ', id);
    return this.http.delete(environment.apiUrl + '/api/form/' + id);
  }

}
