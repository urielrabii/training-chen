import { Component, OnInit } from '@angular/core';
import { File } from '../file';
import { FILES } from '../mock-files';
@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {
  files = FILES;
  constructor() { }

  ngOnInit(): void {
  }

}
