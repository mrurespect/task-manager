import { Component } from '@angular/core';
import {NgIf} from "@angular/common";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {TaskService} from "../task.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  apiError="";
  isLoading=false;

  loginForm:FormGroup=new FormGroup({
    email:new FormControl(null),
    password:new FormControl(null),
  })


  constructor(private _AuthService:AuthService,private _TaskService:TaskService,private _Router:Router) {

  }

   handleLogin(loginForm: FormGroup){
    this.isLoading=true;
    this._AuthService.login(loginForm.value).subscribe({
      next:async (responce) => {
        await localStorage.setItem("userToken", responce?.accessToken)
        this._TaskService.getTasks().subscribe()
        this._Router.navigate(['/home']);
        this._AuthService.isLogged.next(true)
      },
      error:(err)=>{
        console.log(err?.error?.message)
        this.apiError= err?.error?.message;
        this.isLoading=false;
      },
      complete:()=>{
          this.isLoading=false;
      }
    })
  }

}
