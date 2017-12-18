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

  email2 = new FormControl('', [Validators.required, Validators.email]);


  required = new FormControl('', [Validators.required]);

  required2 = new FormControl('', [Validators.required]);

  required3 = new FormControl('', [Validators.required]);

  required4 = new FormControl('', [Validators.required]);

  required5 = new FormControl('', [Validators.required]);

  required6 = new FormControl('', [Validators.required]);


  getErrorMessageEmail() {
    console.log(this.email);
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  getErrorMessageEmail2() {
    return this.email2.hasError('required') ? 'You must enter a value' :
      this.email2.hasError('email') ? 'Not a valid email' :
        '';
  }

  getErrorMessageRequired() {
    return this.required.hasError('required') ? 'You must enter a value' :
        '';
  }

  getErrorMessageRequired2() {
    return this.required2.hasError('required') ? 'You must enter a value' :
      '';
  }

  getErrorMessageRequired3() {
    return this.required3.hasError('required') ? 'You must enter a value' :
      '';
  }


  getErrorMessageRequired4() {
    return this.required4.hasError('required') ? 'You must enter a value' :
      '';
  }

  getErrorMessageRequired5() {
    return this.required5.hasError('required') ? 'You must enter a value' :
      '';
  }

  getErrorMessageRequired6() {
    return this.required6.hasError('required') ? 'You must enter a value' :
      '';
  }
}
