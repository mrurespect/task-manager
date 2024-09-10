import {Component, OnInit} from '@angular/core';
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  isLogin=false
  constructor(private _AuthService:AuthService) {
    this._AuthService.isLogged.subscribe({
      next:()=>this.isLogin=this._AuthService.isLogged.getValue()
    })
  }
  logout(){
    this._AuthService.logout();
  }

  ngOnInit(): void {
    this._AuthService.isLogged.subscribe({
      next:()=>this.isLogin=this._AuthService.isLogged.getValue()
    })
  }
}
