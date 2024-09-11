import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {JsonPipe, NgIf} from "@angular/common";
import {AuthService} from "../auth.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    JsonPipe,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  isLoading:boolean =false;
  apiError:string="";
  apiMessage:string=""

  registerForm : FormGroup = new FormGroup({
    email: new FormControl(null,[Validators.required,Validators.email]),
    password: new FormControl(null,[Validators.required,Validators.pattern(/^[A-Z][a-z0-9]{5,10}$/)]),
  });

  constructor(private _AuthService: AuthService, private _Router: Router) {
    if(localStorage.getItem("userToken") !== null)this._Router.navigate(["/home"]);
  }

  handleRegister(registerForm: FormGroup){
    this.isLoading=true;
    console.log(registerForm);

    if (registerForm.valid) {
      this._AuthService.register(registerForm.value).subscribe({
        next: (response) => {
          this.apiError = "";
          this.apiMessage = response.message;
          if (response.message === "account created successfully") {
            this.isLoading = false;
            setTimeout(() => {
              this._Router.navigate(["/login"]);
            }, 2000);
          }
        },
        error: (errorResponse) => {
          if (errorResponse.status === 409) {
            this.apiError = 'User with this email already exists.';
          } else {
            this.apiError = 'An error occurred during registration. Please try again.';
          }
          this.isLoading = false;
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }

  }
}


