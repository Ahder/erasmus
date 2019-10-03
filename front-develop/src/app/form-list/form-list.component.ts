import { Component, OnInit } from '@angular/core';
import {Form} from '../form/form.model';
import {FormService} from '../form/form.service';
import {Router} from '@angular/router';
import {FormListService} from './form-list.service';

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit {

  displayedColumns: string[] = ['Nom', 'Prénom', 'Années d\'études', 'Semestre', 'Classe', 'Etablissement d\'accueil', 'Pays'];
  dataSource: Form[];

  constructor(public formService: FormService, private router: Router, private formListService: FormListService) { }

  ngOnInit() {
    this.formListService.findAllForms().subscribe((res: Form[]) => {
      console.log(res);
      this.dataSource = res;
    });
  }

  showForm(element: Form) {
    this.formService.setForm(element);
    this.router.navigateByUrl('/formulaire');
  }

}
