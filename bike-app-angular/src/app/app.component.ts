import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {log} from 'util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bike-app-angular';
  constructor(private htpp: HttpClient) {
  }
  onClickBtn() {
    this.htpp.get('localhost:3000/test').subscribe({
      next: value => console.log(value),
      error: err => console.log(err),
      complete:  () => console.log('complete')
    });
  }
}
