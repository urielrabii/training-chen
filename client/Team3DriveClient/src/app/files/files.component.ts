import { Component, OnInit } from '@angular/core';
import { File } from '../file';
import { FileService } from '../file.service';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {
  files: File[];
  constructor(private fileService: FileService) { }

  ngOnInit(){
    this.getFiles();
  }
  getFiles(): void {
    this.fileService.getFiles()
    .subscribe(files => this.files = files);
  }
}
