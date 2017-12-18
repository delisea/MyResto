import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-inscription-connexion',
  templateUrl: './inscription-connexion.component.html',
  styleUrls: ['./inscription-connexion.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class InscriptionConnexionComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  hide = true;

  email = new FormControl('', [Validators.required, Validators.email]);

  phone = new FormControl('', [Validators.required, Validators.max(10)]);

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  getErrorMessagePhone() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('phone') ? 'Not a valid phone number' :
        '';
  }
}
