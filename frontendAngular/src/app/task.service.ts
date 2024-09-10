import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  token:any=null;
  headers :any=null;
  constructor(private _HttpClient:HttpClient) {

    this.token = localStorage.getItem('userToken');
    this.headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
    });
  }

  getTasks():Observable<any>{
    return this._HttpClient.get(
      "http://localhost:8080/tasks",
      {
        headers:this.headers
      }
    );
  }

  deleteTask(id: number): Observable<any> {
    return this._HttpClient.delete(
      `http://localhost:8080/task/${id}`,
      {
        headers: this.headers,
        responseType: 'json'
      }
    );
  }
  updateTask(task:any): Observable<any> {
    return this._HttpClient.put(
      `http://localhost:8080/task`,
      task,
      {
        headers: this.headers,
        responseType: 'json'
      }
    );
  }
  addTask(task:any):Observable<any>{
    return this._HttpClient.put(
      `http://localhost:8080/task`,
      task,
      {
        headers: this.headers,
        responseType: 'json'
      }
    );
  }

}
