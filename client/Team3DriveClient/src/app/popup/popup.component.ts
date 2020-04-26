import { Component, OnInit, Input  } from '@angular/core';
import { File }         from '../file';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {
@Input()
 image: File;

  constructor() {}

  ngOnInit(): void {
  }

  getImgSource(): string {
  const directory = "assets\\img\\"
  return directory + this.image.location.substr(this.image.location.lastIndexOf('\\') + 1);
  }

}








 