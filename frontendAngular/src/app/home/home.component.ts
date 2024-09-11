import {Component, OnInit} from '@angular/core';
import {TaskService} from "../task.service";
import {JsonPipe, NgForOf, NgIf, NgStyle} from "@angular/common";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    JsonPipe,
    NgStyle,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  tasks:any[]=[]
  updateIndex:number=-1;
  isUpdate:boolean=false;

  updatedTask: any = {};
  newTask: any = { title: '', description: '' };
  constructor(private _TaskService :TaskService, private route: ActivatedRoute) {
    this.ngOnInit()
  }


  ngOnInit(): void {
    this.fetchTasks();
    console.log(32237)
  }

  fetchTasks(): void {
    this._TaskService.getTasks().subscribe({
      next: (data) => this.tasks = data,
      error: (err) => console.log(err)
    });
  }


  delete(id:number){
    this._TaskService.deleteTask(id).subscribe(()=>{
      this.ngOnInit();
    })
  }
  toggle(index:number){
    this.isUpdate=!this.isUpdate;
    this.updateIndex=index;
    if (this.isUpdate) {
      this.updatedTask = { ...this.tasks[index] };
    }
  }

  onTaskTitleChange(event: any, task: any): void {
    if (this.updateIndex !== -1 && this.isUpdate) {
      this.updatedTask.title = event.target.value;
    }
  }

  onTaskDescriptionChange(event: any, task: any): void {
    if (this.updateIndex !== -1 && this.isUpdate) {
      this.updatedTask.description = event.target.value;
    }
    const textarea = event.target as HTMLTextAreaElement;
      task.description = textarea.value;
      textarea.style.height = 'auto';
      textarea.style.height = `${textarea.scrollHeight}px`;
  }
  update(): void {
    if (this.updateIndex !== -1 && this.isUpdate) {
      console.log('Updated Task:', this.updatedTask);
      this._TaskService.updateTask(this.updatedTask).subscribe((w)=>{
        console.log(w)
      });
      this.isUpdate = false;
      this.updateIndex = -1;
      this.tasks[this.updateIndex] = this.updatedTask;
      this.updatedTask = {};
    }
  }
  addTask(): void {
    if (this.newTask.title && this.newTask.description) {
      const newTask = { ...this.newTask};
      console.log('Adding Task:', newTask);
      this._TaskService.addTask(newTask).subscribe()
      this.tasks.push(newTask);
      this.newTask = { title: '', description: '' };
    }
  }
}
