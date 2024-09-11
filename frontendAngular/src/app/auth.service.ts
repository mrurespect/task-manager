import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit{
  isLogged=new BehaviorSubject<boolean>(false);
  constructor(private _HttpClient:HttpClient,private _Router:Router) {
    if (localStorage.getItem("userToken")){
      this.isLogged.next(true);
    }
  }
  ngOnInit(): void {

  }


  login(userData:Object):Observable<any>{
    return this._HttpClient.post(
      "http://localhost:8080/login",userData
    )
  }
  logout(){
    localStorage.removeItem("userToken");
    this.isLogged.next(false);
    this._Router.navigate(["/login"]);
  }

  register(userData:Object):Observable<any>{
    return this._HttpClient.post(
      "http://localhost:8080/register",userData
    );
  }


}
