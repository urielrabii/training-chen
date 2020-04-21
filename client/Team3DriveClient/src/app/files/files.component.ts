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
  filteredFiles: File[];
  private _searchTerm: string;

  constructor(private fileService: FileService) {
   }

  ngOnInit(){
    this._searchTerm = "";
    this.getFiles();
     this.filteredFiles = this.files;
  }

  getFiles(): void {
    this.fileService.getFiles()
    .subscribe(files => this.files = files);
  }

  get searchTerm():string{  
    this.filteredFiles = this.filterFiles(this._searchTerm);
    return this._searchTerm;
  }
  set searchTerm(value: string){
    this._searchTerm = value;
    this.filteredFiles = this.filterFiles(this._searchTerm);
  }
  
  filterFiles(searchString: string){
    return this.files.filter(file=>file.name.toLocaleLowerCase().indexOf(searchString.toLocaleLowerCase()) !== -1);
  }
}
