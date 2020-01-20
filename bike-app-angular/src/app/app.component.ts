import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {log} from 'util';
import {BugService} from './shared/bug.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bike-app-angular';
  constructor(private bugService: BugService) {
  }
  onClickBtn() {
    this.bugService.getData().subscribe({
      next: value => console.log(value),
      error: err => console.log(err),
      complete:  () => console.log('complete')
    });
  }
}
