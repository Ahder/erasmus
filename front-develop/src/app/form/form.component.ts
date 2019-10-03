import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Form} from './form.model';
import {FormService} from './form.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  show = false;
  formForm: FormGroup;
  form: Form;
  signatureStudentUrl: string;
  signatureHomeUrl: string;
  signatureHostUrl: string;
  create: boolean;
  constructor(private formBuilder: FormBuilder, private  router: Router, public formService: FormService, private snackbar: MatSnackBar) { }

  ngOnInit() {
    this.show = this.router.url.split('/')[2] === undefined && this.router.url.split('/')[1] !== 'login';
    this.formService.currentForm.subscribe(form => this.form = form);
    this.create = this.form.id == null;

    this.formForm = this.formBuilder.group({
      schoolYearStart: [{value: this.form.schoolYearStart, disabled: this.show}, Validators.required],
      schoolYearEnd: [{value: this.form.schoolYearEnd, disabled: this.show}, Validators.required],
      studyDomain: [{value: this.form.studyDomain, disabled: this.show}, Validators.required],
      semester: [{value: this.form.semester, disabled: this.show}, Validators.required],
      studentLastname: [{value: this.form.student.lastname, disabled: this.show}, Validators.required],
      studentFirstname: [{value: this.form.student.firstname, disabled: this.show}, Validators.required],
      hostEstablishment: [{value: this.form.hostEstablishment, disabled: this.show}, Validators.required],
      country: [{value: this.form.country, disabled: this.show}, Validators.required],
      courses: this.formBuilder.array([this.createCourse(null,null,null)]),
      signatureStudent: [{value: this.form.signatureStudent, disabled: this.show}, Validators.required],
      signatureDateStudent: [{value: new Date(this.form.signatureDateStudent), disabled: this.show}, Validators.required],
      signatureRpiHome: [{value: this.form.signatureRpiHome, disabled: this.show}, Validators.required],
      signatureDateRpiHome: [{value: new Date(this.form.signatureDateRpiHome), disabled: this.show}, Validators.required],
      signatureRpiHost: [{value: this.form.signatureRpiHost, disabled: this.show}, Validators.required],
      signatureDateRpiHost: [{value: new Date(this.form.signatureDateRpiHost), disabled: this.show}, Validators.required]
    });

    if (this.form.courses != null && this.form.courses.length > 1) {
      this.courses.controls.pop();
      this.form.courses.forEach(element => {
        this.courses.push(this.createCourse(element.titleCourse, element.nbrCredits, element.nbrEcts));
      });
    }

    if (this.show) {
      this.courses.controls.forEach((group: FormGroup) => {
        group.disable();
      });
    }

    if (this.form.signatureStudent) {
      this.signatureStudentUrl = this.form.signatureStudent;
    }

    if (this.form.signatureRpiHome) {
      this.signatureHomeUrl = this.form.signatureRpiHome;
    }

    if (this.form.signatureRpiHost) {
      this.signatureHostUrl = this.form.signatureRpiHost;
    }
  }

  onSubmit() {
    if (!this.show) {
      this.router.navigateByUrl('/formulaire');
    }
    if (this.create) {
      this.formService.createForm(this.formForm.value, this.signatureStudentUrl, this.signatureHomeUrl, this.signatureHostUrl).subscribe(res => console.log(res), err => console.log(err));
      this.snackbar.open('Formulaire créé avec succès', 'OK', {
        duration: 5000
      })
      this.router.navigateByUrl('/formulaire/list');
    } else {
      this.formService.updateForm( this.form.id, this.formForm.value, this.signatureStudentUrl, this.signatureHomeUrl, this.signatureHostUrl).subscribe( res => console.log(res), err => console.log(err));
      this.snackbar.open('Formulaire modifié avec succès', 'OK', {
        duration: 5000
      })
      this.router.navigateByUrl('/formulaire/list');
    }
    }

  deleteForm() {
    this.formService.deleteForm(this.form.id).subscribe(res =>
    {
      console.log(res);
      this.snackbar.open('Formulaire supprimé avec succès', 'OK', {
        duration: 5000
      })
      this.router.navigateByUrl('/formulaire/list');
    }, err => console.log(err));
  }


  get courses() {
    return this.formForm.get('courses') as FormArray;
  }

  createCourse(title: string, nbrCredits: number, nbrEcts: number): FormGroup {
    return this.formBuilder.group({
      titleCourse: title,
      nbrCredits: nbrCredits,
      nbrEcts: nbrEcts
    });
  }

  addCourse() {
    this.courses.push(this.createCourse(null,null,null));
  }

  removeCourse(index: number) {
    this.courses.removeAt(index);
  }

  getStudentSignature(event: string): void {
    this.signatureStudentUrl=event;
  }

  getOriginSignature(event: string): void {
    this.signatureHomeUrl=event;
  }

  getHostSignature(event: string): void {
    this.signatureHostUrl=event;
  }
}
